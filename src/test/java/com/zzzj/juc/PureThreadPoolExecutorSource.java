/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


package com.zzzj.juc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PureThreadPoolExecutorSource extends AbstractExecutorService {

    public static void main(String[] args) {
    }

    /**
     * 高3位:运行状态
     * 低29位:worker数量
     * 000 00000000000000000000000000000
     * <p>
     * 运行状态:
     * Running:     111
     * Shutdown:    000
     * Stop:        001
     * Tidying:     010
     * Terminated:  011
     */
    private final AtomicInteger ctl = new AtomicInteger(calculateCtl(RUNNING, 0));
    private static final int COUNT_MASK = 0b00011111111111111111111111111111;
    private static final int RUNNING = 0b111 << 29;
    private static final int SHUTDOWN = 0; // 0b000 << 29
    private static final int STOP = 0b001 << 29;
    private static final int TIDYING = 0b010 << 29;
    private static final int TERMINATED = 0b011 << 29;

    /**
     * 获取工作线程数
     */
    private static int workerCountOf(int c) {
        return c & COUNT_MASK;
    }

    private static int calculateCtl(int runningStatus, int workerCount) {
        return runningStatus | workerCount;
    }

    private static boolean runStateLT(int curStatus, int compareStatus) {
        return curStatus < compareStatus;
    }

    private static boolean runStateGTE(int curStatus, int compareStatus) {
        return curStatus >= curStatus;
    }

    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

    private boolean casIncrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect + 1);
    }

    private boolean casDecrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect - 1);
    }

    private void decrementWorkerCount() {
        ctl.addAndGet(-1);
    }

    private final BlockingQueue<Runnable> workQueue;

    private final ReentrantLock mainLock = new ReentrantLock();

    private final HashSet<Worker> workers = new HashSet<>();

    /**
     * 用于阻塞/唤醒
     * 调用了awaitTerminated()方法的线程
     */
    private final Condition termination = mainLock.newCondition();

    private volatile ThreadFactory threadFactory;

    private volatile RejectedExecutionHandler handler;

    private volatile long keepAliveTime;

    private volatile boolean allowCoreThreadTimeOut;

    private volatile int corePoolSize;

    private volatile int maximumPoolSize;

    private static final RejectedExecutionHandler defaultHandler =
            new AbortPolicy();

    private final class Worker
            extends AbstractQueuedSynchronizer
            implements Runnable {

        /**
         * 真正运行任务的线程对象,由ThreadFactory创建
         */
        final Thread thread;

        /**
         * Worker被创建出来后,要执行的第一个任务
         */
        Runnable firstTask;

        Worker(Runnable firstTask) {
            // 为了在线程真正开始运行任务之前抑制中断，我们将锁定状态初始化为负值，并在启动时将其清除（在 runWorker 中）
            setState(-1);
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }

        public void run() {
            runWorker(this);
        }

        /**
         * 0 = 未锁
         * 1 = 已锁
         */
        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock() {
            acquire(1);
        }

        public boolean tryLock() {
            return tryAcquire(1);
        }

        public void unlock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    }

    private void casUpdateRunState(int targetState) {
        // assert targetState == SHUTDOWN || targetState == STOP;
        for (; ; ) {
            int c = ctl.get();
            if (runStateGTE(c, targetState) ||
                    ctl.compareAndSet(c, calculateCtl(targetState, workerCountOf(c))))
                break;
        }
    }

    /**
     * 每个worker在被关闭前,尝试调用terminated回调函数,并且把状态从 TIDYING -> TERMINATED
     * <p>
     * 前提是当前线程池处于 Shutdown状态或者Stop状态, 并且workQueue没有任务
     */
    final void tryTerminate() {
        for (; ; ) {
            int c = ctl.get();

            if (isRunning(c) ||
                    runStateGTE(c, TIDYING) ||
                    (runStateLT(c, STOP) && !workQueue.isEmpty()))
                return;

            if (workerCountOf(c) != 0) { // Eligible to terminate
                interruptIdleWorkers(true);
                return;
            }

            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                if (ctl.compareAndSet(c, calculateCtl(TIDYING, 0))) {
                    try {
                        terminated();
                    } finally {
                        ctl.set(calculateCtl(TERMINATED, 0));
                        termination.signalAll();
                    }
                    return;
                }
            } finally {
                mainLock.unlock();
            }
            // else retry on failed CAS
        }
    }

    /**
     * 打断所有的线程,无论是在等待任务的,或者是正在运行任务的线程
     */
    private void interruptWorkers() {
        // assert mainLock.isHeldByCurrentThread();
        for (Worker w : workers)
            w.interruptIfStarted();
    }

    /**
     * 中断所有正在等待任务的线程
     *
     * @param onlyOne 是否只中断一个, 当线程池任需要至少一个线程去执行terminated()钩子函数时, 该参数被置为true
     */
    private void interruptIdleWorkers(boolean onlyOne) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (Worker w : workers) {
                Thread t = w.thread;
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        t.interrupt();
                    } catch (SecurityException ignore) {
                    } finally {
                        w.unlock();
                    }
                }
                if (onlyOne)
                    break;
            }
        } finally {
            mainLock.unlock();
        }
    }


    final void reject(Runnable command) {
        // handler.rejectedExecution(command, this);
    }

    void onShutdown() {
    }

    private List<Runnable> drainQueue() {
        BlockingQueue<Runnable> q = workQueue;
        ArrayList<Runnable> taskList = new ArrayList<>();
        q.drainTo(taskList);

        // 为何还要用循坏去转移一次?
        // 因为某些Queue可能不支持drainTo,例如SchedulePool的DelayQueue
        if (!q.isEmpty()) {
            for (Runnable r : q.toArray(new Runnable[0])) {
                if (q.remove(r))
                    taskList.add(r);
            }
        }
        return taskList;
    }


    private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for (int c = ctl.get(); ; ) {

            if (runStateGTE(c, SHUTDOWN)
                    && (runStateGTE(c, STOP)
                    || firstTask != null
                    || workQueue.isEmpty()))
                return false;

            for (; ; ) {
                if (workerCountOf(c)
                        >= ((core ? corePoolSize : maximumPoolSize) & COUNT_MASK))
                    return false;
                if (casIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                if (runStateGTE(c, SHUTDOWN))
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int c = ctl.get();

                    if (isRunning(c) ||
                            (runStateLT(c, STOP) && firstTask == null)) {
                        if (t.getState() != Thread.State.NEW)
                            throw new IllegalThreadStateException();
                        workers.add(w);
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!workerStarted)
                addWorkerFailed(w);
        }
        return workerStarted;
    }

    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null)
                workers.remove(w);
            decrementWorkerCount();
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    /**
     * Performs cleanup and bookkeeping for a dying worker. Called
     * only from worker threads. Unless completedAbruptly is set,
     * assumes that workerCount has already been adjusted to account
     * for exit.  This method removes thread from worker set, and
     * possibly terminates the pool or replaces the worker if either
     * it exited due to user task exception or if fewer than
     * corePoolSize workers are running or queue is non-empty but
     * there are no workers.
     *
     * @param w                 the worker
     * @param completedAbruptly if the worker died due to user exception
     */
    private void processWorkerExit(Worker w, boolean completedAbruptly) {
        if (completedAbruptly) // If abrupt, then workerCount wasn't adjusted
            decrementWorkerCount();

        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            workers.remove(w);
        } finally {
            mainLock.unlock();
        }

        tryTerminate();

        int c = ctl.get();
        if (runStateLT(c, STOP)) {
            if (!completedAbruptly) {
                int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
                if (min == 0 && !workQueue.isEmpty())
                    min = 1;
                if (workerCountOf(c) >= min)
                    return; // replacement not needed
            }
            addWorker(null, false);
        }
    }

    /**
     * Performs blocking or timed wait for a task, depending on
     * current configuration settings, or returns null if this worker
     * must exit because of any of:
     * 1. There are more than maximumPoolSize workers (due to
     * a call to setMaximumPoolSize).
     * 2. The pool is stopped.
     * 3. The pool is shutdown and the queue is empty.
     * 4. This worker timed out waiting for a task, and timed-out
     * workers are subject to termination (that is,
     * {@code allowCoreThreadTimeOut || workerCount > corePoolSize})
     * both before and after the timed wait, and if the queue is
     * non-empty, this worker is not the last thread in the pool.
     *
     * @return task, or null if the worker must exit, in which case
     * workerCount is decremented
     */
    private Runnable getTask() {
        boolean timedOut = false; // Did the last poll() time out?

        for (; ; ) {
            int c = ctl.get();

            // Check if queue empty only if necessary.
            if (runStateGTE(c, SHUTDOWN)
                    && (runStateGTE(c, STOP) || workQueue.isEmpty())) {
                decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);

            // Are workers subject to culling?
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

            if ((wc > maximumPoolSize || (timed && timedOut))
                    && (wc > 1 || workQueue.isEmpty())) {
                if (casDecrementWorkerCount(c))
                    return null;
                continue;
            }

            try {
                Runnable r = timed ?
                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                        workQueue.take();
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }

    /**
     * Main worker run loop.  Repeatedly gets tasks from queue and
     * executes them, while coping with a number of issues:
     * <p>
     * 1. We may start out with an initial task, in which case we
     * don't need to get the first one. Otherwise, as long as pool is
     * running, we get tasks from getTask. If it returns null then the
     * worker exits due to changed pool state or configuration
     * parameters.  Other exits result from exception throws in
     * external code, in which case completedAbruptly holds, which
     * usually leads processWorkerExit to replace this thread.
     * <p>
     * 2. Before running any task, the lock is acquired to prevent
     * other pool interrupts while the task is executing, and then we
     * ensure that unless pool is stopping, this thread does not have
     * its interrupt set.
     * <p>
     * 3. Each task run is preceded by a call to beforeExecute, which
     * might throw an exception, in which case we cause thread to die
     * (breaking loop with completedAbruptly true) without processing
     * the task.
     * <p>
     * 4. Assuming beforeExecute completes normally, we run the task,
     * gathering any of its thrown exceptions to send to afterExecute.
     * We separately handle RuntimeException, Error (both of which the
     * specs guarantee that we trap) and arbitrary Throwables.
     * Because we cannot rethrow Throwables within Runnable.run, we
     * wrap them within Errors on the way out (to the thread's
     * UncaughtExceptionHandler).  Any thrown exception also
     * conservatively causes thread to die.
     * <p>
     * 5. After task.run completes, we call afterExecute, which may
     * also throw an exception, which will also cause thread to
     * die. According to JLS Sec 14.20, this exception is the one that
     * will be in effect even if task.run throws.
     * <p>
     * The net effect of the exception mechanics is that afterExecute
     * and the thread's UncaughtExceptionHandler have as accurate
     * information as we can provide about any problems encountered by
     * user code.
     *
     * @param w the worker
     */
    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateGTE(ctl.get(), STOP) ||
                        (Thread.interrupted() &&
                                runStateGTE(ctl.get(), STOP))) &&
                        !wt.isInterrupted())
                    wt.interrupt();
                try {
                    beforeExecute(wt, task);
                    try {
                        task.run();
                        afterExecute(task, null);
                    } catch (Throwable ex) {
                        afterExecute(task, ex);
                        throw ex;
                    }
                } finally {
                    task = null;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(w, completedAbruptly);
        }
    }

    public PureThreadPoolExecutorSource(int corePoolSize,
                                        int maximumPoolSize,
                                        long keepAliveTime,
                                        TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), defaultHandler);
    }

    public PureThreadPoolExecutorSource(int corePoolSize,
                                        int maximumPoolSize,
                                        long keepAliveTime,
                                        TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue,
                                        ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory, defaultHandler);
    }

    public PureThreadPoolExecutorSource(int corePoolSize,
                                        int maximumPoolSize,
                                        long keepAliveTime,
                                        TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue,
                                        RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), handler);
    }

    public PureThreadPoolExecutorSource(int corePoolSize,
                                        int maximumPoolSize,
                                        long keepAliveTime,
                                        TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue,
                                        ThreadFactory threadFactory,
                                        RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    public void execute(Runnable command) {
        int curStatus = ctl.get();

        // 1. 当前线程数小于核心线程数
        if (workerCountOf(curStatus) < corePoolSize) {

            // 添加核心线程成功,直接return
            if (addWorker(command, true)) {
                return;
            }
            curStatus = ctl.get();
        }

        // 2. 还在运行状态,并且成功添加到了工作队列中
        if (isRunning(curStatus) && workQueue.offer(command)) {

            int recheck = ctl.get();

            // 发生这种情况只有一个原因：把任务放入workQueue的过程中,有别的线程shutdown了线程池
            if (!isRunning(recheck) && remove(command)) {
                reject(command);
            } else if (workerCountOf(recheck) == 0) {   // 线程数 == 0, 只有在运行核心线程超市时才会发生这种情况
                // 想想此时任务已经放到工作队列了,但是没有线程去执行? 那不只能创建一个线程去执行吗
                addWorker(null, false);
            }

        } else if (!addWorker(command, false)) {  // 3. 尝试添加非核心线程

            // 4. 还是失败了,执行拒绝策略
            reject(command);
        }
    }

    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            casUpdateRunState(SHUTDOWN);
            interruptIdleWorkers(false);
            onShutdown(); // hook for ScheduledThreadPoolExecutor
        } finally {
            mainLock.unlock();
        }
        tryTerminate();
    }

    public List<Runnable> shutdownNow() {
        List<Runnable> tasks;
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            casUpdateRunState(STOP);
            interruptWorkers();
            tasks = drainQueue();
        } finally {
            mainLock.unlock();
        }
        tryTerminate();
        return tasks;
    }

    public boolean isShutdown() {
        return runStateGTE(ctl.get(), SHUTDOWN);
    }

    public boolean isTerminated() {
        return runStateGTE(ctl.get(), TERMINATED);
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            while (runStateLT(ctl.get(), TERMINATED)) {
                if (nanos <= 0L)
                    return false;
                nanos = termination.awaitNanos(nanos);
            }
            return true;
        } finally {
            mainLock.unlock();
        }
    }


    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return handler;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }


    public boolean allowsCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    public void allowCoreThreadTimeOut(boolean value) {
        if (value && keepAliveTime <= 0)
            throw new IllegalArgumentException("Core threads must have nonzero keep alive times");
        if (value != allowCoreThreadTimeOut) {
            allowCoreThreadTimeOut = value;
            if (value)
                interruptIdleWorkers(false);
        }
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }


    public long getKeepAliveTime(TimeUnit unit) {
        return unit.convert(keepAliveTime, TimeUnit.NANOSECONDS);
    }


    public BlockingQueue<Runnable> getQueue() {
        return workQueue;
    }

    public boolean remove(Runnable task) {
        boolean removed = workQueue.remove(task);
        tryTerminate(); // In case SHUTDOWN and now empty
        return removed;
    }


    protected void beforeExecute(Thread t, Runnable r) {
    }

    protected void afterExecute(Runnable r, Throwable t) {
    }

    protected void terminated() {
    }


    public static class AbortPolicy implements RejectedExecutionHandler {
        public AbortPolicy() {
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    e.toString());
        }
    }

}
