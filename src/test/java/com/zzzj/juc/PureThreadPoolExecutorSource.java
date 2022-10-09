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

    // 初始化为Running状态,并且workerCount为0
    private final AtomicInteger ctl = new AtomicInteger(calculateCtl(RUNNING, 0));
    private static final int COUNT_MASK = 0b00011111111111111111111111111111;
    private static final int RUNNING = 0b111 << 29;
    private static final int SHUTDOWN = 0; // 0b000 << 29
    private static final int STOP = 0b001 << 29;
    private static final int TIDYING = 0b010 << 29;
    private static final int TERMINATED = 0b011 << 29;

    /**
     * 分支1. 如果线程数小于 < corePoolSize , 那么addWorker(core = true)
     * 分支2. 把task放到workQueue中
     * 分支3. 创建非核心线程
     * 分支4. 拒绝任务
     */
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
            } else if (workerCountOf(recheck) == 0) {   // 线程数 == 0, 只有在运行核心线程超(allowCoreThreadTimeout)时才会发生这种情况
                // 想想此时任务已经放到工作队列了,但是没有线程去执行? 那不只能创建一个线程去执行吗
                addWorker(null, false);
            }

        } else if (!addWorker(command, false)) {  // 3. 尝试添加非核心线程
            // 4. 还是失败了,执行拒绝策略
            reject(command);
        }
    }

    /**
     * 1. 判断线程池状态
     * 2. cas添加workerCount
     * 3. new Worker() , 然后启动线程运行runWorker()方法
     */
    private boolean addWorker(Runnable firstTask, boolean core) {
        /**
         * 外层for循环用于判断线程池状态
         * 当线程池运行状态 >= Shutdown时,就不允许提交任务了
         */
        outer:
        for (int currentCtl = ctl.get(); ; ) {

            int runningStatus = getRunningStatus(currentCtl);

            // 当前线程池池处于 Stop | Tidying | Terminated , 这些状态都不允许添加worker了
            if (runningStatus == STOP || runningStatus == TIDYING || runningStatus == TERMINATED) {
                return false;
            }

            if (runningStatus == SHUTDOWN) {
                // firstTask == null : 只是为了新增一个空闲线程
                if (firstTask != null) {
                    return false;
                }
                // 工作队列都空了,还要空闲线程干什么呢
                if (workQueue.isEmpty()) {
                    return false;
                }
            }

            /**
             * 这个for循环用于cas修改workCount,以及检测workerCount是否大于了corePoolSize | maxPoolSize
             */
            for (; ; ) {
                int workerCount = workerCountOf(currentCtl);
                // 添加核心线程
                if (core) {
                    if (workerCount > (corePoolSize & COUNT_MASK)) {
                        return false;
                    }
                    // 非核心线程
                } else {
                    if (workerCount > (maxPoolSize & COUNT_MASK)) {
                        return false;
                    }
                }

                // CAS修改ctl,使worker + 1
                if (ctl.compareAndSet(currentCtl, currentCtl + 1)) {
                    break outer;
                }

                // CAS修改失败了,重新读下ctl的值
                currentCtl = ctl.get();  // Re-read ctl

                // 非Running状态,添加失败
                if (!isRunning(currentCtl)) {
                    return false;
                }

                // 此时的RunningStatus一定是Running
                // 那么继续自旋修改workerCount
            }
        }

        boolean workerAdded = false;

        Worker worker = null;

        try {
            // 创建worker,并且使用线程池工厂创建一个Thread对象,赋值给worker的成员属性
            worker = new Worker(firstTask);

            Thread thread = worker.thread;

            final ReentrantLock mainLock = this.mainLock;

            mainLock.lock();

            try {
                int currentCtl = ctl.get();

                int runningStatus = getRunningStatus(currentCtl);

                // 获取锁之后Recheck线程池状态
                // Running || (Shutdown && 只是为了创建一个空闲线程时) -> 才允许添加worker
                if (runningStatus == RUNNING || (runningStatus == SHUTDOWN && firstTask == null)) {
                    // 添加到worker集合中
                    workers.add(worker);
                    workerAdded = true;
                }

            } finally {
                mainLock.unlock();
            }

            // 启动worker
            // 具体掉用了 runWorker()
            if (workerAdded) {
                thread.start();
            }
        } finally {
            if (!workerAdded) {
                addWorkerFailed(worker);
            }
        }
        return workerAdded;
    }

    final void runWorker(Worker w) {

        Thread wt = Thread.currentThread();

        Runnable task = w.firstTask;

        w.firstTask = null;

        w.unlock(); // allow interrupts

        boolean completedAbruptly = true;

        try {
            while (task != null || (task = getTask()) != null) {
                // 如果state == -1, 那么标识这个worker才被初始化完,还没有开始,那么无需打断
                // 如果state == 1 , 那么标识这个worker正在运行任务 : shutdownNow可以打断这个worker
                // 如果state == 0 , 那么标识这个worker正在等待任务 : shutdown和shutdownNow可以打断这个worker

                // 拿到任务后 , 设置worker的state == 1 , 那么在interruptIdleWorkers()方法中,该线程将不会被打断
                w.lock();
                // 如果池正在停止，请确保线程已中断；
                // 如果没有，请确保线程没有中断。这
                // 在第二种情况下需要重新检查才能处理
                // 清除中断时的关机竞赛
                if ((runStateGTE(ctl.get(), STOP) ||
                        (Thread.interrupted() &&
                                runStateGTE(ctl.get(), STOP))) &&
                        !wt.isInterrupted())
                    wt.interrupt();
                try {
                    // 钩子函数
                    beforeExecute(wt, task);
                    try {
                        task.run();
                        // 运行成功执行钩子函数
                        afterExecute(task, null);
                    } catch (Throwable ex) {
                        // 运行失败也执行钩子函数,如果想全局捕捉线程池任务运行的异常,可以覆写钩子函数在这里拿到异常对象
                        afterExecute(task, ex);

                        // 异常再次被抛出去,走到下面的两个finally, completedAbruptly = false 这个语句不会被执行
                        throw ex;
                    }
                } finally {
                    task = null;
                    // 设置worker的state = 0
                    w.unlock();
                }
            }

            // 不是因为异常退出的,而是因为getTask()返回了Null才退出的,属于正常退出
            completedAbruptly = false;
        } finally {
            // 当 beforeExecute || task.run || afterExecute这三个方法执行时发生了异常,completedAbruptly == true
            processWorkerExit(w, completedAbruptly);
        }
    }

    /**
     * 分支1. 判断线程池状态: 处于Shutdown , 但是工作队列没有任务了 || 处于Stop  ----> return null : 使该线程在runWorker()方法中退出循坏
     * 分支2. 如果允许超时(allowCoreThreadTimeout & 非核心线程)  && 已经超时   ----> return null
     * 分支3. 从workQueue获取任务
     */
    private Runnable getTask() {
        boolean timedOut = false; // Did the last poll() time out?

        for (; ; ) {
            int c = ctl.get();

            int runningStatus = getRunningStatus(c);

            // 1. 处于Shutdown , 但是工作队列没有任务了 ---> Shutdown导致      , 即使调用了Shutdown方法,但如果workQueue中还有任务,那么Worker还是要继续执行的
            // 2. 处于Stop                           ---> ShutdownNow导致
            if ((runningStatus == SHUTDOWN && workQueue.isEmpty()) || runStateGTE(c, STOP)) {
                // 减一个工作线程数
                casDecrementWorkerCount(c);
                return null;
            }

            int workerCount = workerCountOf(c);

            // 允许核心线程超时 || 线程数 > corePoolSize
            boolean allowTimeout = allowCoreThreadTimeOut || workerCount > corePoolSize;

            // 1. 允许超时 && 已经超时
            if (allowTimeout && timedOut) {

                // 2. 如果workQueue为空,并且有其他空闲线程
                if (workQueue.isEmpty() && workerCount > 1) {
                    // 那么当前线程直接退出即可,留着最后一个线程来收尾即可
                    if (casDecrementWorkerCount(c)) {
                        return null;
                    }
                    // CAS修改失败了
                    continue;
                }
            }

            try {
                Runnable task = allowTimeout ?
                        // 超时拿
                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                        // 无限阻塞拿
                        workQueue.take();
                if (task != null) {
                    return task;
                }
                // 超时了,因为 task == null
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }

    private void processWorkerExit(Worker w, boolean completedAbruptly) {

        // 如果是因为异常停止了,那么之前一定没有执行减少工作线程的动作(getTask()方法内),那么在这里减少一个工作线程
        if (completedAbruptly) {
            decrementWorkerCount();
        }

        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // 从集合中移除worker
            workers.remove(w);
        } finally {
            mainLock.unlock();
        }

        tryTerminate();

        int c = ctl.get();

        // ===================================================
        // 下面的代码只有开启了allowCoreThreadTimeout(true)才会触发

        // 如果线程池处于Running 或者 Shutdown
        if (runStateLT(c, STOP)) {

            // 正常退出的Worker,不是因为发生了异常退出的
            if (!completedAbruptly) {
                int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
                if (min == 0 && !workQueue.isEmpty()) {
                    min = 1;
                }
                if (workerCountOf(c) >= min) {
                    return; // replacement not needed
                }
            }

            // ====================
            // 能走到这里只说明一种情况
            // allowCoreThreadTimeout(true) && workerCount == 0 && !workQueue.isEmpty()

            // 那么开启一个不带初始任务的非核心线程执行workQueue中的任务
            addWorker(null, false);
        }
    }


    /**
     * 1. 只有线程池处于Shutdown && workQueue.isEmpty() 才会执行步骤2
     * 2. 修改线程池状态为TIDYING,执行terminated()钩子函数, 修改线程池状态为Terminated, 唤醒调用awaitTerminated()方法的线程
     */
    final void tryTerminate() {
        for (; ; ) {
            int c = ctl.get();

            // 正在运行,无需Try
            if (isRunning(c)) {
                return;
            }

            // 已经Stop了,也无需Try
            if (getRunningStatus(c) == STOP || getRunningStatus(c) == TIDYING) {
                return;
            }

            // 处于Shutdown但是还有任务没有完成,无需Try
            if (getRunningStatus(c) == SHUTDOWN && !workQueue.isEmpty()) {
                return;
            }


            // ===============================================================
            // 能走到这里说明此时线程池的状态一定是Shutdown , 且workQueue一定是Empty的

            // 如果还有别的worker线程,留给它去收尾,当前线程不负责收尾
            if (workerCountOf(c) != 0) {
                interruptIdleWorkers(true);
                return;
            }

            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                // CAS修改线程池状态为TIDYING
                if (ctl.compareAndSet(c, calculateCtl(TIDYING, 0))) {
                    try {
                        // 执行terminated钩子函数
                        terminated();
                    } finally {
                        // 直接修改线程池状态为TERMINATED
                        ctl.set(calculateCtl(TERMINATED, 0));

                        // 唤醒所有调用了awaitTerminated()方法睡眠的线程
                        termination.signalAll();
                    }
                    return;
                }
            } finally {
                mainLock.unlock();
            }
        }
    }

    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // for循环自旋修改状态为Shutdown
            casUpdateRunState(SHUTDOWN);

            // 打断空闲的workers ---> 正在getTask()方法中阻塞的worker , 正在执行task的worker不会被打断
            interruptIdleWorkers(false);

            // 钩子回调方法
            onShutdown();
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
            // 1. 通过Cas修改线程池状态为Stop
            casUpdateRunState(STOP);
            // 2. 打断所有线程,哪怕是正在执行任务的线程
            for (Worker w : workers) {
                w.interruptIfStarted();
                // 1. getState >= 0 只要不是-1, 代表线程已经启动
                // 2. 线程没有处于中断状态

                // if (getState() >= 0 && !thread.isInterrupted()) {
                //    // 3. 打断线程
                //     thread.interrupt();
                // }
            }
            // 3. 清空workQueue
            tasks = drainQueue();
        } finally {
            mainLock.unlock();
        }
        // 4. 尝试终结(Terminated)线程池
        tryTerminate();

        // 5. 返回workQueue未完成的任务
        return tasks;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // 如果线程池状态 < TERMINATED , 那么线程进入阻塞状态
            while (runStateLT(ctl.get(), TERMINATED)) {
                if (nanos <= 0L) {
                    return false;
                }
                nanos = termination.awaitNanos(nanos);
            }
            return true;
        } finally {
            mainLock.unlock();
        }
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

                // 两个条件
                // - 1. 线程没有中断
                // - 2. 当前worker的state == 0 , 并且cas修改成了1
                // 具体见runWorker方法, worker执行完task后, unLock, 将state置为0
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        t.interrupt();
                    } catch (SecurityException ignore) {
                    } finally {
                        w.unlock();
                    }
                }
                if (onlyOne) { // 只打断一个线程
                    break;
                }
            }
        } finally {
            mainLock.unlock();
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


    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null) {
                workers.remove(w);
            }
            decrementWorkerCount();
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }


    public PureThreadPoolExecutorSource(int corePoolSize,
                                        int maxPoolSize,
                                        long keepAliveTime,
                                        TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue,
                                        ThreadFactory threadFactory,
                                        RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maxPoolSize <= 0 ||
                maxPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }


    public boolean isShutdown() {
        return runStateGTE(ctl.get(), SHUTDOWN);
    }

    public boolean isTerminated() {
        return runStateGTE(ctl.get(), TERMINATED);
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

    public int getMaxPoolSize() {
        return maxPoolSize;
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

    private static int getRunningStatus(int ctl) {
        return ctl & 0b11100000000000000000000000000000;
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

    private volatile int maxPoolSize;

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
            if (getState() >= 0 && !thread.isInterrupted()) {
                thread.interrupt();
            }
        }
    }

}
