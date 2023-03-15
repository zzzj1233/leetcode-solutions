///*
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */
//
///*
// *
// *
// *
// *
// *
// * Written by Doug Lea with assistance from members of JCP JSR-166
// * Expert Group and released to the public domain, as explained at
// * http://creativecommons.org/publicdomain/zero/1.0/
// */
//
//
//package java.util.concurrent;
//
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//import java.util.concurrent.locks.LockSupport;
//import java.util.function.*;
//
//public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
//
//    volatile Object result;       // Either the result or boxed AltResult
//    volatile Completion stack;    // Top of Treiber stack of dependent actions
//
//    /**
//     * @param completion 把completion.next指向栈顶, 通过CAS把栈顶指向completion
//     * @return 是否CAS成功
//     */
//    final boolean tryPushStack(Completion completion) {
//
//        Completion h = stack;
//
//        // 给c赋值next = h : completion.next = h
//        NEXT.set(completion, h);
//
//        // CAS设置 { stack } 指针为c : stack = completion
//        return STACK.compareAndSet(this, h, completion);
//    }
//
//    final boolean internalComplete(Object r) { // CAS from null to r
//        return RESULT.compareAndSet(this, null, r);
//    }
//
//    final void pushStack(Completion c) {
//        do {
//        } while (!tryPushStack(c));
//    }
//
//    /* ------------- Encoding and decoding outcomes -------------- */
//
//    static final class AltResult { // See above
//        final Throwable ex;        // null only for NIL
//
//        AltResult(Throwable x) {
//            this.ex = x;
//        }
//    }
//
//    /**
//     * The encoding of the null value.
//     */
//    static final AltResult NIL = new AltResult(null);
//
//    /**
//     * Completes with the null value, unless already completed.
//     */
//    final boolean completeNull() {
//        return RESULT.compareAndSet(this, null, NIL);
//    }
//
//    /**
//     * Returns the encoding of the given non-exceptional value.
//     */
//    final Object encodeValue(T t) {
//        return (t == null) ? NIL : t;
//    }
//
//    /**
//     * Completes with a non-exceptional result, unless already completed.
//     */
//    final boolean completeValue(T t) {
//        return RESULT.compareAndSet(this, null, (t == null) ? NIL : t);
//    }
//
//    /**
//     * Returns the encoding of the given (non-null) exception as a
//     * wrapped CompletionException unless it is one already.
//     */
//    static AltResult encodeThrowable(Throwable x) {
//        return new AltResult((x instanceof CompletionException) ? x : new CompletionException(x));
//    }
//
//    /**
//     * Completes with an exceptional result, unless already completed.
//     */
//    final boolean completeThrowable(Throwable x) {
//        return RESULT.compareAndSet(this, null, encodeThrowable(x));
//    }
//
//    /**
//     * Returns the encoding of the given (non-null) exception as a
//     * wrapped CompletionException unless it is one already.  May
//     * return the given Object r (which must have been the result of a
//     * source future) if it is equivalent, i.e. if this is a simple
//     * relay of an existing CompletionException.
//     */
//    static Object encodeThrowable(Throwable x, Object r) {
//        if (!(x instanceof CompletionException)) x = new CompletionException(x);
//        else if (r instanceof AltResult && x == ((AltResult) r).ex) return r;
//        return new AltResult(x);
//    }
//
//    /**
//     * Completes with the given (non-null) exceptional result as a
//     * wrapped CompletionException unless it is one already, unless
//     * already completed.  May complete with the given Object r
//     * (which must have been the result of a source future) if it is
//     * equivalent, i.e. if this is a simple propagation of an
//     * existing CompletionException.
//     */
//    final boolean completeThrowable(Throwable x, Object r) {
//        return RESULT.compareAndSet(this, null, encodeThrowable(x, r));
//    }
//
//    /**
//     * Returns the encoding of the given arguments: if the exception
//     * is non-null, encodes as AltResult.  Otherwise uses the given
//     * value, boxed as NIL if null.
//     */
//    Object encodeOutcome(T t, Throwable x) {
//        return (x == null) ? (t == null) ? NIL : t : encodeThrowable(x);
//    }
//
//    /**
//     * Returns the encoding of a copied outcome; if exceptional,
//     * rewraps as a CompletionException, else returns argument.
//     */
//    static Object encodeRelay(Object r) {
//        Throwable x;
//        if (r instanceof AltResult && (x = ((AltResult) r).ex) != null && !(x instanceof CompletionException))
//            r = new AltResult(new CompletionException(x));
//        return r;
//    }
//
//    /**
//     * Completes with r or a copy of r, unless already completed.
//     * If exceptional, r is first coerced to a CompletionException.
//     */
//    final boolean completeRelay(Object r) {
//        return RESULT.compareAndSet(this, null, encodeRelay(r));
//    }
//
//    /**
//     * Reports result using Future.get conventions.
//     */
//    private static Object reportGet(Object r) throws InterruptedException, ExecutionException {
//        if (r == null) // by convention below, null means interrupted
//            throw new InterruptedException();
//        if (r instanceof AltResult) {
//            Throwable x, cause;
//            if ((x = ((AltResult) r).ex) == null) return null;
//            if (x instanceof CancellationException) throw (CancellationException) x;
//            if ((x instanceof CompletionException) && (cause = x.getCause()) != null) x = cause;
//            throw new ExecutionException(x);
//        }
//        return r;
//    }
//
//    /**
//     * Decodes outcome to return result or throw unchecked exception.
//     */
//    private static Object reportJoin(Object r) {
//        if (r instanceof AltResult) {
//            Throwable x;
//            if ((x = ((AltResult) r).ex) == null) return null;
//            if (x instanceof CancellationException) throw (CancellationException) x;
//            if (x instanceof CompletionException) throw (CompletionException) x;
//            throw new CompletionException(x);
//        }
//        return r;
//    }
//
//    /* ------------- Async task preliminaries -------------- */
//
//    /**
//     * A marker interface identifying asynchronous tasks produced by
//     * {@code async} methods. This may be useful for monitoring,
//     * debugging, and tracking asynchronous activities.
//     *
//     * @since 1.8
//     */
//    public static interface AsynchronousCompletionTask {
//    }
//
//    private static final boolean USE_COMMON_POOL = (ForkJoinPool.getCommonPoolParallelism() > 1);
//
//    /**
//     * Default executor -- ForkJoinPool.commonPool() unless it cannot
//     * support parallelism.
//     */
//    private static final Executor ASYNC_POOL = USE_COMMON_POOL ? ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
//
//    /**
//     * Fallback if ForkJoinPool.commonPool() cannot support parallelism
//     */
//    static final class ThreadPerTaskExecutor implements Executor {
//        public void execute(Runnable r) {
//            new Thread(r).start();
//        }
//    }
//
//    /**
//     * Null-checks user executor argument, and translates uses of
//     * commonPool to ASYNC_POOL in case parallelism disabled.
//     */
//    static Executor screenExecutor(Executor e) {
//        if (!USE_COMMON_POOL && e == ForkJoinPool.commonPool()) return ASYNC_POOL;
//        if (e == null) throw new NullPointerException();
//        return e;
//    }
//
//    // Modes for Completion.tryFire. Signedness matters.
//    static final int SYNC = 0;
//    static final int ASYNC = 1;
//    static final int NESTED = -1;
//
//    /* ------------- Base Completion classes and operations -------------- */
//
//    @SuppressWarnings("serial")
//    abstract static class Completion extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
//        volatile Completion next;      // Treiber stack link
//
//        /**
//         * Performs completion action if triggered, returning a
//         * dependent that may need propagation, if one exists.
//         *
//         * @param mode SYNC, ASYNC, or NESTED
//         */
//        abstract CompletableFuture<?> tryFire(int mode);
//
//        /**
//         * Returns true if possibly still triggerable. Used by cleanStack.
//         */
//        abstract boolean isLive();
//
//        public final void run() {
//            tryFire(ASYNC);
//        }
//
//        public final boolean exec() {
//            tryFire(ASYNC);
//            return false;
//        }
//
//        public final Void getRawResult() {
//            return null;
//        }
//
//        public final void setRawResult(Void v) {
//        }
//    }
//
//    /**
//     * 弹出 { stack } 中的所有 { Completion } , 并且执行
//     */
//    final void postComplete() {
//
//        CompletableFuture<?> future = this;
//
//        Completion completion;
//
//        // 1. stack != null
//        // 或者
//        // 2. future不是 { this } && { this } 的栈不为空
//        while ((completion = future.stack) != null || (future != this && (completion = (future = this).stack) != null)) {
//
//            Completion t;
//
//            // CAS设置Stack指针 : stack = stack.next
//            if (STACK.compareAndSet(future, completion, t = completion.next)) {
//
//                if (t != null) {
//
//                    if (future != this) {
//                        // 因为future != this , 所以completion一定是另一个future的栈顶
//                        // pushStack: 把completion压入当前栈顶 , 相当于把另一个future的栈顶移到了当前future的栈顶
//                        pushStack(completion);
//                        continue;
//                    }
//
//                    // 把next指针置为空 : completion.next = null
//                    NEXT.compareAndSet(completion, t, null); // try to detach
//                }
//
//                // !!! 调用 { completion#tryFire }
//                CompletableFuture<?> fireResult = completion.tryFire(NESTED);
//
//                future = fireResult == null ? this : fireResult;
//            }
//        }
//    }
//
//    /**
//     * Traverses stack and unlinks one or more dead Completions, if found.
//     */
//    final void cleanStack() {
//        Completion p = stack;
//        // ensure head of stack live
//        for (boolean unlinked = false; ; ) {
//            if (p == null) return;
//            else if (p.isLive()) {
//                if (unlinked) return;
//                else break;
//            } else if (STACK.weakCompareAndSet(this, p, (p = p.next))) unlinked = true;
//            else p = stack;
//        }
//        // try to unlink first non-live
//        for (Completion q = p.next; q != null; ) {
//            Completion s = q.next;
//            if (q.isLive()) {
//                p = q;
//                q = s;
//            } else if (NEXT.weakCompareAndSet(p, q, s)) break;
//            else q = p.next;
//        }
//    }
//
//    /* ------------- One-input Completions -------------- */
//
//    /**
//     * A Completion with a source, dependent, and executor.
//     */
//    @SuppressWarnings("serial")
//    abstract static class UniCompletion<T, V> extends Completion {
//        Executor executor;                 // executor to use (null if none)
//        CompletableFuture<V> dep;          // the dependent to complete
//        CompletableFuture<T> src;          // source for action
//
//        UniCompletion(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src) {
//            this.executor = executor;
//            this.dep = dep;
//            this.src = src;
//        }
//
//        /**
//         * @return false : 当前线程不执行该completion#tryFire , true :  当前线程执行
//         */
//        final boolean claim() {
//            Executor e = executor;
//
//            // 1. Completion继承了ForkJoinTask类, 该类有一个status变量, 通过CAS把status设置为1, 以保证这个Completion只能执行一次
//            if (compareAndSetForkJoinTaskTag((short) 0, (short) 1)) {
//                // 2. 如果创建Completion时没有传递线程池 ( 通过非Async方法, 例如: thenCombine() )
//                // 那么直接返回true, 由当前线程来执行completion
//                if (e == null) {
//                    return true;
//                }
//
//                executor = null; // disable
//
//                // 3. 否则通过线程池来执行completion
//                e.execute(this);
//            }
//
//            // 两种情况
//            // 1. CAS失败, 那么return false, 当前线程池无法执行 { tryFire(int mode) }
//            // 2. CAS成功, 把当前completion提交个线程池, 那么由线程池的线程来执行 { tryFire(int mode) }
//            return false;
//        }
//
//        final boolean isLive() {
//            return dep != null;
//        }
//    }
//
//    /**
//     * 如果入栈前或者入栈后此时已经 { 完成 } 了, 那么直接执行 { completion }
//     * 否则将completion入栈, 等待被回调
//     */
//    final void unipush(Completion completion) {
//        if (completion != null) {
//
//            // tryPushStack: CAS修改栈指针, true = 修改成功 , false = 修改失败
//            while (!tryPushStack(completion)) {
//                /*
//                    修改失败, 进入while代码块
//                 */
//
//                if (result != null) {
//                    // 如果已经有结果了, 直接把 { completion#next } 指针置为null ( helpGC )
//                    NEXT.set(completion, null);
//
//                    // 跳出循环, 直接执行completion
//                    break;
//                }
//
//                // 没有结果, 继续CAS入栈
//            }
//
//            // 两种情况可以走到这里
//            // 1. 已经入栈成功了
//            // 2. 入栈失败, 但是已经有结果了
//            if (result != null)
//                // 直接执行completion
//                completion.tryFire(SYNC);
//        }
//    }
//
//    /**
//     * Post-processing by dependent after successful UniCompletion tryFire.
//     * Tries to clean stack of source src, and then either runs postComplete
//     * or returns this to caller, depending on mode.
//     */
//    final CompletableFuture<T> postFire(CompletableFuture<?> src, int mode) {
//        // 1. src.postComplete
//        if (src != null && src.stack != null) {
//            Object r;
//            if ((r = src.result) == null) src.cleanStack();
//            if (mode >= 0 && (r != null || src.result != null)) src.postComplete();
//        }
//        // 2. this.postComplete
//        if (result != null && stack != null) {
//            if (mode < 0) // nested
//                return this;
//            else postComplete();
//        }
//        return null;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniApply<T, V> extends UniCompletion<T, V> {
//        Function<? super T, ? extends V> fn;
//
//        UniApply(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, Function<? super T, ? extends V> fn) {
//            super(executor, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<V> tryFire(int mode) {
//            CompletableFuture<V> d;
//            CompletableFuture<T> a;
//            Object r;
//            Throwable x;
//            Function<? super T, ? extends V> f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null) return null;
//            tryComplete:
//            if (d.result == null) {
//                if (r instanceof AltResult) {
//                    if ((x = ((AltResult) r).ex) != null) {
//                        d.completeThrowable(x, r);
//                        break tryComplete;
//                    }
//                    r = null;
//                }
//                try {
//                    if (mode <= 0 && !claim()) return null;
//                    else {
//                        @SuppressWarnings("unchecked") T t = (T) r;
//                        d.completeValue(f.apply(t));
//                    }
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            dep = null;
//            src = null;
//            fn = null;
//            return d.postFire(a, mode);
//        }
//    }
//
//    private <V> CompletableFuture<V> uniApplyStage(Executor e, Function<? super T, ? extends V> f) {
//        if (f == null) throw new NullPointerException();
//        Object r;
//        if ((r = result) != null) return uniApplyNow(r, e, f);
//        CompletableFuture<V> d = newIncompleteFuture();
//        unipush(new UniApply<T, V>(e, d, this, f));
//        return d;
//    }
//
//    private <V> CompletableFuture<V> uniApplyNow(Object r, Executor e, Function<? super T, ? extends V> f) {
//        Throwable x;
//        CompletableFuture<V> d = newIncompleteFuture();
//        if (r instanceof AltResult) {
//            if ((x = ((AltResult) r).ex) != null) {
//                d.result = encodeThrowable(x, r);
//                return d;
//            }
//            r = null;
//        }
//        try {
//            if (e != null) {
//                e.execute(new UniApply<T, V>(null, d, this, f));
//            } else {
//                @SuppressWarnings("unchecked") T t = (T) r;
//                d.result = d.encodeValue(f.apply(t));
//            }
//        } catch (Throwable ex) {
//            d.result = encodeThrowable(ex);
//        }
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniAccept<T> extends UniCompletion<T, Void> {
//        Consumer<? super T> fn;
//
//        UniAccept(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, Consumer<? super T> fn) {
//            super(executor, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            Object r;
//            Throwable x;
//            Consumer<? super T> f;
//
//            // 1. 判空
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null) return null;
//
//            tryComplete:
//
//            // 2. 如果dep还没有结果
//            if (d.result == null) {
//
//                if (r instanceof AltResult) {
//
//                    if ((x = ((AltResult) r).ex) != null) {
//                        // 2.1 给 { dep } 赋值异常结果, 不执行 { consumer }
//                        d.completeThrowable(x, r);
//                        break tryComplete;
//                    }
//                    r = null;
//                }
//                try {
//                    if (mode <= 0 && !claim()) return null;
//                    else {
//                        // 2.2 正常结果, 执行 { consumer }
//                        f.accept((T) r);
//
//                        // 2.3 给dep赋值Nil结果, 因为 { consumer }是没有返回值的
//                        d.completeNull();
//                    }
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            dep = null;
//            src = null;
//            fn = null;
//
//            // 3. 此时dep已经有结果了, 并且是通过异步执行到了该方法, dep的stack可能有未完成的Completion
//            // 所以向后传播
//            return d.postFire(a, mode);
//        }
//    }
//
//    private CompletableFuture<Void> uniAcceptStage(Executor e, Consumer<? super T> f) {
//        if (f == null) throw new NullPointerException();
//        Object r;
//        if ((r = result) != null) return uniAcceptNow(r, e, f);
//        CompletableFuture<Void> d = newIncompleteFuture();
//        unipush(new UniAccept<T>(e, d, this, f));
//        return d;
//    }
//
//    private CompletableFuture<Void> uniAcceptNow(Object result, Executor executor, Consumer<? super T> consumerFunction) {
//        Throwable x;
//
//        CompletableFuture<Void> dep = newIncompleteFuture();
//
//        if (result instanceof AltResult) {
//            // 如果是异常结果
//            if ((x = ((AltResult) result).ex) != null) {
//                // 直接把结果赋值给dep, 然后直接返回, consumer也就不会被执行了
//                dep.result = encodeThrowable(x, result);
//                return dep;
//            }
//            result = null;
//        }
//
//        try {
//            if (executor != null) {
//                // 通过执行器执行consumer
//                executor.execute(new UniAccept<T>(null, dep, this, consumerFunction));
//            } else {
//                @SuppressWarnings("unchecked") T t = (T) result;
//                // 通过当前线程执行consumer
//                consumerFunction.accept(t);
//
//                // 执行成功, 给dep赋值空结果
//                dep.result = NIL;
//            }
//        } catch (Throwable ex) {
//            // 执行失败, 给dep赋值异常结果
//            dep.result = encodeThrowable(ex);
//        }
//
//        return dep;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniRun<T> extends UniCompletion<T, Void> {
//        Runnable fn;
//
//        UniRun(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, Runnable fn) {
//            super(executor, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            Object r;
//            Throwable x;
//            Runnable f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null) return null;
//            if (d.result == null) {
//                if (r instanceof AltResult && (x = ((AltResult) r).ex) != null) d.completeThrowable(x, r);
//                else try {
//                    if (mode <= 0 && !claim()) return null;
//                    else {
//                        f.run();
//                        d.completeNull();
//                    }
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            dep = null;
//            src = null;
//            fn = null;
//            return d.postFire(a, mode);
//        }
//    }
//
//    private CompletableFuture<Void> uniRunStage(Executor e, Runnable f) {
//        if (f == null) throw new NullPointerException();
//        Object r;
//        if ((r = result) != null) return uniRunNow(r, e, f);
//        CompletableFuture<Void> d = newIncompleteFuture();
//        unipush(new UniRun<T>(e, d, this, f));
//        return d;
//    }
//
//    private CompletableFuture<Void> uniRunNow(Object r, Executor e, Runnable f) {
//        Throwable x;
//        CompletableFuture<Void> d = newIncompleteFuture();
//        if (r instanceof AltResult && (x = ((AltResult) r).ex) != null) d.result = encodeThrowable(x, r);
//        else try {
//            if (e != null) {
//                e.execute(new UniRun<T>(null, d, this, f));
//            } else {
//                f.run();
//                d.result = NIL;
//            }
//        } catch (Throwable ex) {
//            d.result = encodeThrowable(ex);
//        }
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniWhenComplete<T> extends UniCompletion<T, T> {
//        BiConsumer<? super T, ? super Throwable> fn;
//
//        UniWhenComplete(Executor executor, CompletableFuture<T> dep, CompletableFuture<T> src, BiConsumer<? super T, ? super Throwable> fn) {
//            super(executor, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<T> tryFire(int mode) {
//            CompletableFuture<T> d;
//            CompletableFuture<T> a;
//            Object r;
//            BiConsumer<? super T, ? super Throwable> f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null || !d.uniWhenComplete(r, f, mode > 0 ? null : this))
//                return null;
//            dep = null;
//            src = null;
//            fn = null;
//            return d.postFire(a, mode);
//        }
//    }
//
//    final boolean uniWhenComplete(Object r, BiConsumer<? super T, ? super Throwable> f, UniWhenComplete<T> c) {
//        T t;
//        Throwable x = null;
//        if (result == null) {
//            try {
//                if (c != null && !c.claim()) return false;
//                if (r instanceof AltResult) {
//                    x = ((AltResult) r).ex;
//                    t = null;
//                } else {
//                    @SuppressWarnings("unchecked") T tr = (T) r;
//                    t = tr;
//                }
//                f.accept(t, x);
//                if (x == null) {
//                    internalComplete(r);
//                    return true;
//                }
//            } catch (Throwable ex) {
//                if (x == null) x = ex;
//                else if (x != ex) x.addSuppressed(ex);
//            }
//            completeThrowable(x, r);
//        }
//        return true;
//    }
//
//    private CompletableFuture<T> uniWhenCompleteStage(Executor e, BiConsumer<? super T, ? super Throwable> f) {
//        if (f == null) throw new NullPointerException();
//        CompletableFuture<T> d = newIncompleteFuture();
//        Object r;
//        if ((r = result) == null) unipush(new UniWhenComplete<T>(e, d, this, f));
//        else if (e == null) d.uniWhenComplete(r, f, null);
//        else {
//            try {
//                e.execute(new UniWhenComplete<T>(null, d, this, f));
//            } catch (Throwable ex) {
//                d.result = encodeThrowable(ex);
//            }
//        }
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniHandle<T, V> extends UniCompletion<T, V> {
//        BiFunction<? super T, Throwable, ? extends V> fn;
//
//        UniHandle(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, BiFunction<? super T, Throwable, ? extends V> fn) {
//            super(executor, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<V> tryFire(int mode) {
//            CompletableFuture<V> d;
//            CompletableFuture<T> a;
//            Object r;
//            BiFunction<? super T, Throwable, ? extends V> f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null || !d.uniHandle(r, f, mode > 0 ? null : this))
//                return null;
//            dep = null;
//            src = null;
//            fn = null;
//            return d.postFire(a, mode);
//        }
//    }
//
//    final <S> boolean uniHandle(Object r, BiFunction<? super S, Throwable, ? extends T> f, UniHandle<S, T> c) {
//        S s;
//        Throwable x;
//        if (result == null) {
//            try {
//                if (c != null && !c.claim()) return false;
//                if (r instanceof AltResult) {
//                    x = ((AltResult) r).ex;
//                    s = null;
//                } else {
//                    x = null;
//                    @SuppressWarnings("unchecked") S ss = (S) r;
//                    s = ss;
//                }
//                completeValue(f.apply(s, x));
//            } catch (Throwable ex) {
//                completeThrowable(ex);
//            }
//        }
//        return true;
//    }
//
//    private <V> CompletableFuture<V> uniHandleStage(Executor e, BiFunction<? super T, Throwable, ? extends V> f) {
//        if (f == null) throw new NullPointerException();
//        CompletableFuture<V> d = newIncompleteFuture();
//        Object r;
//        if ((r = result) == null) unipush(new UniHandle<T, V>(e, d, this, f));
//        else if (e == null) d.uniHandle(r, f, null);
//        else {
//            try {
//                e.execute(new UniHandle<T, V>(null, d, this, f));
//            } catch (Throwable ex) {
//                d.result = encodeThrowable(ex);
//            }
//        }
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniExceptionally<T> extends UniCompletion<T, T> {
//        Function<? super Throwable, ? extends T> fn;
//
//        UniExceptionally(CompletableFuture<T> dep, CompletableFuture<T> src, Function<? super Throwable, ? extends T> fn) {
//            super(null, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<T> tryFire(int mode) { // never ASYNC
//            // assert mode != ASYNC;
//            CompletableFuture<T> d;
//            CompletableFuture<T> a;
//            Object r;
//            Function<? super Throwable, ? extends T> f;
//
//            // 什么情况下uniExceptionally会返回false?
//            // 提交给别的线程去执行当前Completion的tryFire()了
//            // 还会再次进入到这个方法体, 并且这次的uniExceptionally()返回的一定是true
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null || !d.uniExceptionally(r, f, this))
//                return null;
//            dep = null;
//            src = null;
//            fn = null;
//
//            // 调用dep的stack
//            return d.postFire(a, mode);
//        }
//    }
//
//    final boolean uniExceptionally(Object r, Function<? super Throwable, ? extends T> f, UniExceptionally<T> c) {
//        Throwable x;
//        if (result == null) {
//            try {
//                // 异常结果
//                if (r instanceof AltResult && (x = ((AltResult) r).ex) != null) {
//                    if (c != null && !c.claim()) {
//                        // 由别的线程去执行completion了, 还是会走下面的
//                        return false;
//                    }
//                    // 回调 { function } , { args } 是异常对象, { return } 作为future的结果
//                    completeValue(f.apply(x));
//                } else {
//                    // 正常结果, 给future赋值结果
//                    internalComplete(r);
//                }
//            } catch (Throwable ex) {
//                // 给future赋值异常结果
//                completeThrowable(ex);
//            }
//        }
//        return true;
//    }
//
//    private CompletableFuture<T> uniExceptionallyStage(Function<Throwable, ? extends T> f) {
//
//        if (f == null) {
//            throw new NullPointerException();
//        }
//
//        // 1. 新创建一个dependency
//        CompletableFuture<T> dep = newIncompleteFuture();
//
//        Object res;
//
//        // 2. future还没有结果
//        if ((res = result) == null) {
//            // 给future推入一个 { UniExceptionally: Completion }
//            unipush(new UniExceptionally<T>(dep, this, f));
//        } else {
//            // 3. 直接执行{ uniExceptionally }, ( UniExceptionally这个Completion最终也会执行这个方法 )
//            dep.uniExceptionally(res, f, null);
//        }
//
//        return dep;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniRelay<U, T extends U> extends UniCompletion<T, U> {
//        UniRelay(CompletableFuture<U> dep, CompletableFuture<T> src) {
//            super(null, dep, src);
//        }
//
//        final CompletableFuture<U> tryFire(int mode) {
//            CompletableFuture<U> d;
//            CompletableFuture<T> a;
//            Object r;
//            if ((d = dep) == null || (a = src) == null || (r = a.result) == null) return null;
//            if (d.result == null) d.completeRelay(r);
//            src = null;
//            dep = null;
//            return d.postFire(a, mode);
//        }
//    }
//
//    private static <U, T extends U> CompletableFuture<U> uniCopyStage(CompletableFuture<T> src) {
//        Object r;
//        CompletableFuture<U> d = src.newIncompleteFuture();
//        if ((r = src.result) != null) d.result = encodeRelay(r);
//        else src.unipush(new UniRelay<U, T>(d, src));
//        return d;
//    }
//
//    private MinimalStage<T> uniAsMinimalStage() {
//        Object r;
//        if ((r = result) != null) return new MinimalStage<T>(encodeRelay(r));
//        MinimalStage<T> d = new MinimalStage<T>();
//        unipush(new UniRelay<T, T>(d, this));
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class UniCompose<T, V> extends UniCompletion<T, V> {
//        Function<? super T, ? extends CompletionStage<V>> fn;
//
//        UniCompose(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, Function<? super T, ? extends CompletionStage<V>> fn) {
//            super(executor, dep, src);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<V> tryFire(int mode) {
//            CompletableFuture<V> d;
//            CompletableFuture<T> a;
//            Function<? super T, ? extends CompletionStage<V>> f;
//            Object r;
//            Throwable x;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null) return null;
//            tryComplete:
//            if (d.result == null) {
//                if (r instanceof AltResult) {
//                    if ((x = ((AltResult) r).ex) != null) {
//                        d.completeThrowable(x, r);
//                        break tryComplete;
//                    }
//                    r = null;
//                }
//                try {
//                    if (mode <= 0 && !claim()) return null;
//                    @SuppressWarnings("unchecked") T t = (T) r;
//                    CompletableFuture<V> g = f.apply(t).toCompletableFuture();
//                    if ((r = g.result) != null) d.completeRelay(r);
//                    else {
//                        g.unipush(new UniRelay<V, V>(d, g));
//                        if (d.result == null) return null;
//                    }
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            dep = null;
//            src = null;
//            fn = null;
//            return d.postFire(a, mode);
//        }
//    }
//
//    private <V> CompletableFuture<V> uniComposeStage(Executor e, Function<? super T, ? extends CompletionStage<V>> f) {
//        if (f == null) throw new NullPointerException();
//        CompletableFuture<V> d = newIncompleteFuture();
//        Object r, s;
//        Throwable x;
//        if ((r = result) == null) unipush(new UniCompose<T, V>(e, d, this, f));
//        else if (e == null) {
//            if (r instanceof AltResult) {
//                if ((x = ((AltResult) r).ex) != null) {
//                    d.result = encodeThrowable(x, r);
//                    return d;
//                }
//                r = null;
//            }
//            try {
//                @SuppressWarnings("unchecked") T t = (T) r;
//                CompletableFuture<V> g = f.apply(t).toCompletableFuture();
//                if ((s = g.result) != null) d.result = encodeRelay(s);
//                else {
//                    g.unipush(new UniRelay<V, V>(d, g));
//                }
//            } catch (Throwable ex) {
//                d.result = encodeThrowable(ex);
//            }
//        } else try {
//            e.execute(new UniCompose<T, V>(null, d, this, f));
//        } catch (Throwable ex) {
//            d.result = encodeThrowable(ex);
//        }
//        return d;
//    }
//
//    /* ------------- Two-input Completions -------------- */
//
//    /**
//     * A Completion for an action with two sources
//     */
//    @SuppressWarnings("serial")
//    abstract static class BiCompletion<T, U, V> extends UniCompletion<T, V> {
//        CompletableFuture<U> snd; // second source for action
//
//        BiCompletion(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, CompletableFuture<U> snd) {
//            super(executor, dep, src);
//            this.snd = snd;
//        }
//    }
//
//    /**
//     * A Completion delegating to a BiCompletion
//     */
//    @SuppressWarnings("serial")
//    static final class CoCompletion extends Completion {
//        BiCompletion<?, ?, ?> base;
//
//        CoCompletion(BiCompletion<?, ?, ?> base) {
//            this.base = base;
//        }
//
//        final CompletableFuture<?> tryFire(int mode) {
//            BiCompletion<?, ?, ?> c;
//            CompletableFuture<?> d;
//            if ((c = base) == null || (d = c.tryFire(mode)) == null) return null;
//            base = null; // detach
//            return d;
//        }
//
//        final boolean isLive() {
//            BiCompletion<?, ?, ?> c;
//            return (c = base) != null
//                    // && c.isLive()
//                    && c.dep != null;
//        }
//    }
//
//    /**
//     * 将Completion置为 { this } 和 { other } 的栈顶,
//     */
//    final void bipush(CompletableFuture<?> other, BiCompletion<?, ?, ?> completion) {
//        // 日常判空: ignore
//        if (completion != null) {
//
//            while (result == null) {
//                // 把completion压入当前栈的栈顶
//                if (tryPushStack(completion)) {
//                    if (other.result == null) {
//                        other.unipush(new CoCompletion(completion));
//                    } else if (result != null) {
//                        // 入栈成功后发现已经有结果了, 直接执行completion
//                        completion.tryFire(SYNC);
//                    }
//                    return;
//                }
//            }
//
//            other.unipush(completion);
//
//        }
//    }
//
//    /**
//     * 1. 执行second的 postComplete()
//     * 2. 执行first的 postComplete()
//     * 3. 执行当前future的postComplete()
//     */
//    final CompletableFuture<T> postFire(CompletableFuture<?> first, CompletableFuture<?> second, int mode) {
//        if (second != null && second.stack != null) { // clean second source
//            Object r;
//            if ((r = second.result) == null) second.cleanStack();
//            if (mode >= 0 && (r != null || second.result != null)) second.postComplete();
//        }
//        return postFire(first, mode);
//    }
//
//    @SuppressWarnings("serial")
//    static final class BiApply<T, U, V> extends BiCompletion<T, U, V> {
//        BiFunction<? super T, ? super U, ? extends V> fn;
//
//        BiApply(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, CompletableFuture<U> snd, BiFunction<? super T, ? super U, ? extends V> fn) {
//            super(executor, dep, src, snd);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<V> tryFire(int mode) {
//            CompletableFuture<V> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r, s;
//            BiFunction<? super T, ? super U, ? extends V> f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null || (b = snd) == null || (s = b.result) == null || !d.biApply(r, s, f, mode > 0 ? null : this))
//                return null;
//            dep = null;
//            src = null;
//            snd = null;
//            fn = null;
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    final <R, S> boolean biApply(Object r, Object s, BiFunction<? super R, ? super S, ? extends T> f, BiApply<R, S, T> c) {
//        Throwable x;
//        tryComplete:
//        if (result == null) {
//            // 1. 两者之一出现了异常
//            if (r instanceof AltResult) {
//                if ((x = ((AltResult) r).ex) != null) {
//                    completeThrowable(x, r);
//                    break tryComplete;
//                }
//                r = null;
//            }
//            if (s instanceof AltResult) {
//                if ((x = ((AltResult) s).ex) != null) {
//                    completeThrowable(x, s);
//                    break tryComplete;
//                }
//                s = null;
//            }
//            // 2. 都没有正常执行结束, 那执行fn
//            try {
//                if (c != null && !c.claim()) return false;
//                @SuppressWarnings("unchecked") R rr = (R) r;
//                @SuppressWarnings("unchecked") S ss = (S) s;
//                completeValue(f.apply(rr, ss));
//            } catch (Throwable ex) {
//
//                // 3. 执行fn时出现了异常, 赋值异常结果
//                completeThrowable(ex);
//            }
//        }
//        return true;
//    }
//
//    private <U, V> CompletableFuture<V> biApplyStage(Executor executor, CompletionStage<U> o, BiFunction<? super T, ? super U, ? extends V> fn) {
//
//        CompletableFuture<U> other;
//
//        Object thisResult, otherResult;
//
//        // 1. 判空
//        if (fn == null || (other = o.toCompletableFuture()) == null) {
//            throw new NullPointerException();
//        }
//
//        CompletableFuture<V> dep = newIncompleteFuture();
//
//        // 2. 两者之一未完成
//        if ((thisResult = result) == null || (otherResult = other.result) == null) {
//            // 推送 { BiApply:Completion } 到 { this & other } 的 { stack }
//            bipush(other, new BiApply<T, U, V>(executor, dep, this, other, fn));
//        } else if (executor == null) {
//            // 3. 没有线程池, 通过当前线程去执行fn
//            dep.biApply(thisResult, otherResult, fn, null);
//        } else {
//            // 4. 提交给线程池去执行fn
//            try {
//                executor.execute(new BiApply<T, U, V>(null, dep, this, other, fn));
//            } catch (Throwable ex) {
//                dep.result = encodeThrowable(ex);
//            }
//        }
//        return dep;
//    }
//
//    @SuppressWarnings("serial")
//    static final class BiAccept<T, U> extends BiCompletion<T, U, Void> {
//        BiConsumer<? super T, ? super U> fn;
//
//        BiAccept(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, BiConsumer<? super T, ? super U> fn) {
//            super(executor, dep, src, snd);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r, s;
//            BiConsumer<? super T, ? super U> f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (r = a.result) == null || (b = snd) == null || (s = b.result) == null || !d.biAccept(r, s, f, mode > 0 ? null : this))
//                return null;
//            dep = null;
//            src = null;
//            snd = null;
//            fn = null;
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    final <R, S> boolean biAccept(Object r, Object s, BiConsumer<? super R, ? super S> f, BiAccept<R, S> c) {
//        Throwable x;
//        tryComplete:
//        if (result == null) {
//            if (r instanceof AltResult) {
//                if ((x = ((AltResult) r).ex) != null) {
//                    completeThrowable(x, r);
//                    break tryComplete;
//                }
//                r = null;
//            }
//            if (s instanceof AltResult) {
//                if ((x = ((AltResult) s).ex) != null) {
//                    completeThrowable(x, s);
//                    break tryComplete;
//                }
//                s = null;
//            }
//            try {
//                if (c != null && !c.claim()) return false;
//                @SuppressWarnings("unchecked") R rr = (R) r;
//                @SuppressWarnings("unchecked") S ss = (S) s;
//                f.accept(rr, ss);
//                completeNull();
//            } catch (Throwable ex) {
//                completeThrowable(ex);
//            }
//        }
//        return true;
//    }
//
//    private <U> CompletableFuture<Void> biAcceptStage(Executor e, CompletionStage<U> o, BiConsumer<? super T, ? super U> f) {
//        CompletableFuture<U> b;
//        Object r, s;
//        if (f == null || (b = o.toCompletableFuture()) == null) throw new NullPointerException();
//        CompletableFuture<Void> d = newIncompleteFuture();
//        if ((r = result) == null || (s = b.result) == null) bipush(b, new BiAccept<T, U>(e, d, this, b, f));
//        else if (e == null) d.biAccept(r, s, f, null);
//        else try {
//                e.execute(new BiAccept<T, U>(null, d, this, b, f));
//            } catch (Throwable ex) {
//                d.result = encodeThrowable(ex);
//            }
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class BiRun<T, U> extends BiCompletion<T, U, Void> {
//        Runnable fn;
//
//        BiRun(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Runnable fn) {
//            super(executor, dep, src, snd);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r, s;
//            Runnable f;
//            // 1. 判空
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (b = snd) == null)
//                return null;
//            // 2. 两个future必须都有结果
//            if ((r = a.result) == null || (s = b.result) == null) {
//                return null;
//            }
//            // 3. !!! 执行 { biRun }, 就算返回false, 这里return了, 还是会交给别的线程去执行 { biRun }
//            if (!d.biRun(r, s, f, mode > 0 ? null : this)) {
//                return null;
//            }
//            dep = null;
//            src = null;
//            snd = null;
//            fn = null;
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    final boolean biRun(Object r, Object s, Runnable f, BiRun<?, ?> c) {
//        Throwable x;
//        Object z;
//        if (result == null) {
//            // 两个结果任一一个出现了异常
//            if ((r instanceof AltResult && (x = ((AltResult) (z = r)).ex) != null) || (s instanceof AltResult && (x = ((AltResult) (z = s)).ex) != null))
//                completeThrowable(x, z);
//            else try {
//                // 尝试交给别的线程去执行
//                if (c != null && !c.claim()) return false;
//
//                // 执行runnable
//                f.run();
//                // 赋值dep { 空 } 结果
//                completeNull();
//            } catch (Throwable ex) {
//                // 赋值dep { 异常 } 结果
//                completeThrowable(ex);
//            }
//        }
//        return true;
//    }
//
//    private CompletableFuture<Void> biRunStage(Executor executor, CompletionStage<?> other, Runnable fn) {
//
//        CompletableFuture<?> b;
//
//        Object r, s;
//
//        if (fn == null || (b = other.toCompletableFuture()) == null) {
//            throw new NullPointerException();
//        }
//
//        CompletableFuture<Void> dep = newIncompleteFuture();
//
//        // case1 : this和other的任一一个未完成
//        if ((r = result) == null || (s = b.result) == null) {
//            // 将 { BiRun:Completion } 推入this和b(other)的 { stack }
//            bipush(b, new BiRun<>(executor, dep, this, b, fn));
//        } else if (executor == null) { // case2: 同步执行runnable
//
//            // biRun核心方法, 3个case都会执行这个方法
//
//            dep.biRun(r, s, fn, null);
//
//        } else { // case3: 异步执行runnable
//            try {
//                // 附, 这里的线程池是null, 上面的线程池是executor
//                // 也就是说, 通过executor.execute提交的 { BiRun } 的claim方法一定返回true, 这个BiRun#tryFire一定由executor执行
//                // 上面的 { new BiRun } 可能由executor线程执行 ( 取决于是否能抢到ForkJoinPool标记位 )
//                executor.execute(new BiRun<>(null, dep, this, b, fn));
//            } catch (Throwable ex) {
//                dep.result = encodeThrowable(ex);
//            }
//        }
//        return dep;
//    }
//
//    @SuppressWarnings("serial")
//    static final class BiRelay<T, U> extends BiCompletion<T, U, Void> { // for And
//        BiRelay(CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd) {
//            super(null, dep, src, snd);
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r, s, z;
//            Throwable x;
//            if ((d = dep) == null
//                    // 可能a.result == null || b.result == null
//                    // 但是不可能  a.result && b.result == null
//
//                    // 假设a已经完成, 执行这个Completion的tryFire, 但是b没有完成, 所以提前return了
//                    // 后续b执行完成, 也执行这个Completion的tryFire
//                    // 因为最开始使用的就是biPush方法, 将当前这个Completion推到了a和b的栈顶
//                    || (a = src) == null || (r = a.result) == null || (b = snd) == null || (s = b.result) == null)
//                return null;
//            if (d.result == null) {
//                if ((r instanceof AltResult && (x = ((AltResult) (z = r)).ex) != null) || (s instanceof AltResult && (x = ((AltResult) (z = s)).ex) != null))
//                    // 2. a或者b是异常结束的
//                    d.completeThrowable(x, z);
//                else
//                    // 2.1 正常结束的
//                    d.completeNull();
//            }
//            src = null;
//            snd = null;
//            dep = null;
//
//            // postFire: 执行 a, b, this 的 postComplete
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    /**
//     * {@code CompletableFuture.allOf(a, b, c).join()}
//     *
//     * <pre>
//     * 3个future : [a, b, c]
//     *
//     * a 和 b push { BiRelay1 } 到栈顶
//     *
//     * 返回 { dep }
//     *
//     * { dep } 和 { c } push { BiRelay2 } 到栈顶
//     *
//     * 返回 { d } , 主线程调用 { d.join() }
//     *
//     * { a }和{ b }都执行完成后, 回调{ BiRelay1 }
//     *
//     * { BiRelay1 }给{ dep }赋值结果, 此时{ dep }已完成
//     *
//     * 当{ dep }和{ c }都完成后, 回调 { BiRelay2 }
//     *
//     * { join() }方法解除阻塞
//     * </pre>
//     */
//    static CompletableFuture<Void> andTree(CompletableFuture<?>[] cfs, int low, int high) {
//        CompletableFuture<Void> d = new CompletableFuture<Void>();
//
//        // 边界判断
//        if (low > high) // empty
//            d.result = NIL;
//        else {
//
//            CompletableFuture<?> a, b;
//
//            Object r, s, z;
//
//            Throwable x;
//
//            int mid = (low + high) >>> 1;
//
//            // 1. 如果low == mid , 那么直接取出
//            // 2. 继续递归, 二分直到只有1个元素或者2个元素
//            if ((a = (low == mid ? cfs[low] : andTree(cfs, low, mid))) == null ||
//
//                    // 1. low == high , 表示只有一个元素, 那么b就是上面的a
//                    // 2. high == mid + 1 , 那么直接取出
//                    // 3. 继续递归, 二分直到只有1个元素或者2个元素
//                    (b = (low == high ? a : (high == mid + 1) ? cfs[high] : andTree(cfs, mid + 1, high))) == null)
//                throw new NullPointerException();
//
//            // 如果 { a } 和 { b } 两个Future有任何一个还没有执行完
//            if ((r = a.result) == null || (s = b.result) == null)
//                // 推送 { new BiRelay<>(d, a, b) } 到a和b的栈顶
//                a.bipush(b, new BiRelay<>(d, a, b));
//            else if ((r instanceof AltResult && (x = ((AltResult) (z = r)).ex) != null) || (s instanceof AltResult && (x = ((AltResult) (z = s)).ex) != null))
//                // 两个Future都执行完了, 但是出现了异常
//                d.result = encodeThrowable(x, z);
//            else
//                // 两个Future都执行完了, 没有出现异常 ( 注意递归 )
//                d.result = NIL;
//        }
//        return d;
//    }
//
//    /* ------------- Projected (Ored) BiCompletions -------------- */
//
//    /**
//     * 如果 this 和 b 两个任一一个有结果, 那么直接执行Completion
//     * 否则将Completion推入到 this 和 b的栈中
//     */
//    final void orpush(CompletableFuture<?> b, BiCompletion<?, ?, ?> c) {
//        // 判空, ignore
//        if (c != null) {
//            // 入栈
//            while (!tryPushStack(c)) {
//
//                // CAS入栈失败, 此时发现已经有结果了
//                if (result != null) {
//                    // 清空completion的next指针, c.next = null
//                    NEXT.set(c, null);
//                    break;
//                }
//            }
//
//            // 已经有结果了, 直接执行completion
//            if (result != null) {
//                // 执行完completion后, c.dep = null , 那么completion.isAlive() 将会返回false
//                c.tryFire(SYNC);
//            } else {
//                // this和b还是没有结果, 那么把completion也推入到b的栈中
//                b.unipush(new CoCompletion(c));
//            }
//        }
//    }
//
//    @SuppressWarnings("serial")
//    static final class OrApply<T, U extends T, V> extends BiCompletion<T, U, V> {
//        Function<? super T, ? extends V> fn;
//
//        OrApply(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Function<? super T, ? extends V> fn) {
//            super(executor, dep, src, snd);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<V> tryFire(int mode) {
//            CompletableFuture<V> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r;
//            Throwable x;
//            Function<? super T, ? extends V> f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (b = snd) == null || ((r = a.result) == null && (r = b.result) == null))
//                return null;
//            tryComplete:
//            if (d.result == null) {
//                try {
//                    if (mode <= 0 && !claim()) return null;
//                    if (r instanceof AltResult) {
//                        if ((x = ((AltResult) r).ex) != null) {
//                            d.completeThrowable(x, r);
//                            break tryComplete;
//                        }
//                        r = null;
//                    }
//                    @SuppressWarnings("unchecked") T t = (T) r;
//                    d.completeValue(f.apply(t));
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            dep = null;
//            src = null;
//            snd = null;
//            fn = null;
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    private <U extends T, V> CompletableFuture<V> orApplyStage(Executor e, CompletionStage<U> o, Function<? super T, ? extends V> f) {
//        CompletableFuture<U> b;
//        if (f == null || (b = o.toCompletableFuture()) == null) throw new NullPointerException();
//
//        Object r;
//        CompletableFuture<? extends T> z;
//        if ((r = (z = this).result) != null || (r = (z = b).result) != null) return z.uniApplyNow(r, e, f);
//
//        CompletableFuture<V> d = newIncompleteFuture();
//        orpush(b, new OrApply<T, U, V>(e, d, this, b, f));
//        return d;
//    }
//
//    @SuppressWarnings("serial")
//    static final class OrAccept<T, U extends T> extends BiCompletion<T, U, Void> {
//        Consumer<? super T> fn;
//
//        OrAccept(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Consumer<? super T> fn) {
//            super(executor, dep, src, snd);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r;
//            Throwable x;
//            Consumer<? super T> f;
//            // 日常判空
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (b = snd) == null || ((r = a.result) == null && (r = b.result) == null))
//                return null;
//            tryComplete:
//            // 日常Completion判断: dep还没有结果
//            if (d.result == null) {
//                try {
//                    if (mode <= 0 && !claim()) return null;
//                    if (r instanceof AltResult) {
//                        if ((x = ((AltResult) r).ex) != null) {
//                            // 错误的结果, 不会执行consumer
//                            // 给dep赋值结果后, 直接执行d.postFire
//                            d.completeThrowable(x, r);
//                            break tryComplete;
//                        }
//                        r = null;
//                    }
//                    // 正常的结果, 执行consumer
//                    @SuppressWarnings("unchecked") T t = (T) r;
//                    f.accept(t);
//                    d.completeNull();
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            // !!! 注意, dep赋值为null后, isAlive()将会返回false
//            dep = null;
//            src = null;
//            snd = null;
//            fn = null;
//
//            // 回调a/b/dep.stack的completion
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    private <U extends T> CompletableFuture<Void> orAcceptStage(Executor executor, CompletionStage<U> other, Consumer<? super T> consumerFunction) {
//        CompletableFuture<U> otherFuture;
//
//        if (consumerFunction == null || (otherFuture = other.toCompletableFuture()) == null)
//            throw new NullPointerException();
//
//        Object result;
//
//        CompletableFuture<? extends T> hasResultFuture;
//
//        // { 当前future } 已经有结果 || { other } 已经有结果
//        if ((result = (hasResultFuture = this).result) != null || (result = (hasResultFuture = otherFuture).result) != null)
//            return hasResultFuture.uniAcceptNow(result, executor, consumerFunction);
//
//        // 此时两个future都没有结果
//        CompletableFuture<Void> dep = newIncompleteFuture();
//
//        orpush(otherFuture, new OrAccept<T, U>(executor, dep, this, otherFuture, consumerFunction));
//
//        return dep;
//    }
//
//    @SuppressWarnings("serial")
//    static final class OrRun<T, U> extends BiCompletion<T, U, Void> {
//        Runnable fn;
//
//        OrRun(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Runnable fn) {
//            super(executor, dep, src, snd);
//            this.fn = fn;
//        }
//
//        final CompletableFuture<Void> tryFire(int mode) {
//            CompletableFuture<Void> d;
//            CompletableFuture<T> a;
//            CompletableFuture<U> b;
//            Object r;
//            Throwable x;
//            Runnable f;
//            if ((d = dep) == null || (f = fn) == null || (a = src) == null || (b = snd) == null || ((r = a.result) == null && (r = b.result) == null))
//                return null;
//            if (d.result == null) {
//                try {
//                    if (mode <= 0 && !claim()) return null;
//                    else if (r instanceof AltResult && (x = ((AltResult) r).ex) != null) d.completeThrowable(x, r);
//                    else {
//                        f.run();
//                        d.completeNull();
//                    }
//                } catch (Throwable ex) {
//                    d.completeThrowable(ex);
//                }
//            }
//            dep = null;
//            src = null;
//            snd = null;
//            fn = null;
//            return d.postFire(a, b, mode);
//        }
//    }
//
//    private CompletableFuture<Void> orRunStage(Executor e, CompletionStage<?> o, Runnable f) {
//        CompletableFuture<?> b;
//        if (f == null || (b = o.toCompletableFuture()) == null) throw new NullPointerException();
//
//        Object r;
//        CompletableFuture<?> z;
//        if ((r = (z = this).result) != null || (r = (z = b).result) != null) return z.uniRunNow(r, e, f);
//
//        CompletableFuture<Void> d = newIncompleteFuture();
//        orpush(b, new OrRun<>(e, d, this, b, f));
//        return d;
//    }
//
//    /**
//     * Completion for an anyOf input future.
//     */
//    @SuppressWarnings("serial")
//    static class AnyOf extends Completion {
//        CompletableFuture<Object> dep;
//        CompletableFuture<?> src;
//        CompletableFuture<?>[] srcs;
//
//        AnyOf(CompletableFuture<Object> dep, CompletableFuture<?> src, CompletableFuture<?>[] srcs) {
//            this.dep = dep;
//            this.src = src;
//            this.srcs = srcs;
//        }
//
//        final CompletableFuture<Object> tryFire(int mode) {
//            // assert mode != ASYNC;
//
//            // 1. 判空
//            if (dep == null || src == null || src.result == null || srcs == null) {
//                return null;
//            }
//
//            // 2. CAS给 { dep } 赋值结果
//            if (dep.completeRelay(src.result)) {
//
//                // 3. 如果当前线程CAS成功了, 那么给其他的futures清栈
//                // ( 清除 isAlive() 返回false的Completion, 注意下面的isAlive() )
//                for (CompletableFuture<?> it : srcs)
//                    if (it != src) it.cleanStack();
//
//                if (mode < 0) { // NESTED
//                    return dep;
//                } else {
//                    // 4. 回调 { dep } 的 { stack }
//                    dep.postComplete();
//                }
//            }
//            return null;
//        }
//
//        /**
//         * 注意isLive
//         *
//         * @return
//         */
//        final boolean isLive() {
//            CompletableFuture<Object> d;
//            // 如果dep还没有结果, 那么视为这个Completion还存活, 就不会被 { cleanStack() }方法移除
//            return (d = dep) != null && d.result == null;
//        }
//    }
//
//    /* ------------- Zero-input Async forms -------------- */
//
//    @SuppressWarnings("serial")
//    static final class AsyncSupply<T> extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
//        CompletableFuture<T> dependency;
//        Supplier<? extends T> supplierFunction;
//
//        AsyncSupply(CompletableFuture<T> dependency, Supplier<? extends T> supplierFunction) {
//            this.dependency = dependency;
//            this.supplierFunction = supplierFunction;
//        }
//
//        public final Void getRawResult() {
//            return null;
//        }
//
//        public final void setRawResult(Void v) {
//        }
//
//        public final boolean exec() {
//            run();
//            return false;
//        }
//
//        public void run() {
//
//            CompletableFuture<T> dep = dependency;
//
//            Supplier<? extends T> supplier = supplierFunction;
//
//            // 日常判空: ignore
//            if ((dep) != null && (supplier) != null) {
//
//                dependency = null;
//
//                supplierFunction = null;    // help gc...
//
//                // dependency还没有结果
//                if (dep.result == null) {
//                    try {
//                        // 执行supplierFunction, 并且把执行结果给dependency
//                        dep.completeValue(supplier.get());
//                    } catch (Throwable ex) {
//                        // 执行时出现了异常, 那么给dependency赋值异常结果
//                        dep.completeThrowable(ex);
//                    }
//                }
//
//                // 调用dep的stack
//                dep.postComplete();
//            }
//        }
//    }
//
//    static <U> CompletableFuture<U> asyncSupplyStage(Executor executor, Supplier<U> supplierFunction) {
//        if (supplierFunction == null) {
//            throw new NullPointerException();
//        }
//
//        // dependency是一个 { Empty } CompletableFuture
//        CompletableFuture<U> dependency = new CompletableFuture<U>();
//
//        executor.execute(new AsyncSupply<U>(dependency, supplierFunction));
//
//        return dependency;
//    }
//
//    @SuppressWarnings("serial")
//    static final class AsyncRun extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
//        CompletableFuture<Void> dep;
//        Runnable fn;
//
//        AsyncRun(CompletableFuture<Void> dep, Runnable fn) {
//            this.dep = dep;
//            this.fn = fn;
//        }
//
//        public final Void getRawResult() {
//            return null;
//        }
//
//        public final void setRawResult(Void v) {
//        }
//
//        public final boolean exec() {
//            run();
//            return false;
//        }
//
//        public void run() {
//            CompletableFuture<Void> d;
//            Runnable f;
//            if ((d = dep) != null && (f = fn) != null) {
//                dep = null;
//                fn = null;
//                if (d.result == null) {
//                    try {
//                        f.run();
//                        d.completeNull();
//                    } catch (Throwable ex) {
//                        d.completeThrowable(ex);
//                    }
//                }
//                d.postComplete();
//            }
//        }
//    }
//
//    static CompletableFuture<Void> asyncRunStage(Executor e, Runnable f) {
//        if (f == null) throw new NullPointerException();
//        CompletableFuture<Void> d = new CompletableFuture<Void>();
//        e.execute(new AsyncRun(d, f));
//        return d;
//    }
//
//    /* ------------- Signallers -------------- */
//
//    /**
//     * Completion for recording and releasing a waiting thread.  This
//     * class implements ManagedBlocker to avoid starvation when
//     * blocking actions pile up in ForkJoinPools.
//     */
//    @SuppressWarnings("serial")
//    static final class Signaller extends Completion implements ForkJoinPool.ManagedBlocker {
//        long nanos;                    // remaining wait time if timed
//        final long deadline;           // non-zero if timed
//        final boolean interruptible;
//        boolean interrupted;
//        volatile Thread thread;
//
//        Signaller(boolean interruptible, long nanos, long deadline) {
//            this.thread = Thread.currentThread();
//            this.interruptible = interruptible;
//            this.nanos = nanos;
//            this.deadline = deadline;
//        }
//
//        final CompletableFuture<?> tryFire(int ignore) {
//            Thread w; // no need to atomically claim
//            if ((w = thread) != null) {
//                thread = null;
//                LockSupport.unpark(w);
//            }
//            return null;
//        }
//
//        /**
//         * @return true/false : 是否可以被释放
//         */
//        public boolean isReleasable() {
//            if (Thread.interrupted()) {
//                interrupted = true;
//            }
//            // 1. 线程被打断过 && 允许被打断
//            if (interrupted && interruptible) {
//                return true;
//            }
//            // 2. 传入了等待超时时间 && 已经超时 ( 适用于 get(ms, timeUnit) )
//            if (deadline != 0L && (nanos <= 0L || (nanos = deadline - System.nanoTime()) <= 0L) {
//                return true;
//            }
//            // 3. 线程为空 , 调用 this#tryFire , thread被赋值为null
//            if (thread == null) {
//                return true;
//            }
//            return false;
//        }
//
//        public boolean block() {
//            while (!isReleasable()) {
//                // 如果没有传入超时时间, 那么一直阻塞直到唤醒
//                if (deadline == 0L) {
//                    LockSupport.park(this);
//                } else {
//                    // 阻塞指定的时间
//                    LockSupport.parkNanos(this, nanos);
//                }
//            }
//            return true;
//        }
//
//        final boolean isLive() {
//            return thread != null;
//        }
//    }
//
//    /**
//     * Returns raw result after waiting, or null if interruptible and
//     * interrupted.
//     */
//    private Object waitingGet(boolean interruptible) {
//        Signaller q = null;
//        boolean queued = false;
//        Object r;
//        while ((r = result) == null) {
//            // 1. 第一次进来, 初始化 { Signaller } , 再次循坏, 减少阻塞
//            if (q == null) {
//                q = new Signaller(interruptible, 0L, 0L);
//                if (Thread.currentThread() instanceof ForkJoinWorkerThread) {
//                    ForkJoinPool.helpAsyncBlocker(defaultExecutor(), q);
//                }
//                // 2. 第二次进来, CAS将 { Signaller } 入栈, 如果失败就循坏重试, 如果成功再次循坏, 减少阻塞
//            } else if (!queued) {
//                queued = tryPushStack(q);
//            } else {
//                try {
//                    // 3. 阻塞Signaller
//                    ForkJoinPool.managedBlock(q);
//                } catch (InterruptedException ie) { // currently cannot happen
//                    q.interrupted = true;
//                }
//                if (q.interrupted && interruptible) break;
//            }
//        }
//        if (q != null && queued) {
//            q.thread = null;
//            if (!interruptible && q.interrupted) {
//                Thread.currentThread().interrupt();
//            }
//            if (r == null) {
//                cleanStack();
//            }
//        }
//        // 4. 阻塞完后有结果了, 调用stack上的completion
//        if (r != null || (r = result) != null) {
//            postComplete();
//        }
//        return r;
//    }
//
//    /**
//     * Returns raw result after waiting, or null if interrupted, or
//     * throws TimeoutException on timeout.
//     */
//    private Object timedGet(long nanos) throws TimeoutException {
//        if (Thread.interrupted()) return null;
//        if (nanos > 0L) {
//            long d = System.nanoTime() + nanos;
//            long deadline = (d == 0L) ? 1L : d; // avoid 0
//            Signaller q = null;
//            boolean queued = false;
//            Object r;
//            while ((r = result) == null) { // similar to untimed
//                if (q == null) {
//                    q = new Signaller(true, nanos, deadline);
//                    if (Thread.currentThread() instanceof ForkJoinWorkerThread)
//                        ForkJoinPool.helpAsyncBlocker(defaultExecutor(), q);
//                } else if (!queued) queued = tryPushStack(q);
//                else if (q.nanos <= 0L) break;
//                else {
//                    try {
//                        ForkJoinPool.managedBlock(q);
//                    } catch (InterruptedException ie) {
//                        q.interrupted = true;
//                    }
//                    if (q.interrupted) break;
//                }
//            }
//            if (q != null && queued) {
//                q.thread = null;
//                if (r == null) cleanStack();
//            }
//            if (r != null || (r = result) != null) postComplete();
//            if (r != null || (q != null && q.interrupted)) return r;
//        }
//        throw new TimeoutException();
//    }
//
//    /* ------------- public methods -------------- */
//
//    // 创建一个不完整的CompletableFuture
//    public CompletableFuture() {
//    }
//
//    // 使用给定的结果创建一个已完成的CompletableFuture
//    CompletableFuture(Object r) {
//        this.result = r;
//    }
//
//    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
//        return asyncSupplyStage(ASYNC_POOL, supplier);
//    }
//
//    /**
//     * Returns a new CompletableFuture that is asynchronously completed
//     * by a task running in the given executor with the value obtained
//     * by calling the given Supplier.
//     *
//     * @param supplier a function returning the value to be used
//     *                 to complete the returned CompletableFuture
//     * @param executor the executor to use for asynchronous execution
//     * @param <U>      the function's return type
//     * @return the new CompletableFuture
//     */
//    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
//        return asyncSupplyStage(screenExecutor(executor), supplier);
//    }
//
//    /**
//     * Returns a new CompletableFuture that is asynchronously completed
//     * by a task running in the {@link ForkJoinPool#commonPool()} after
//     * it runs the given action.
//     *
//     * @param runnable the action to run before completing the
//     *                 returned CompletableFuture
//     * @return the new CompletableFuture
//     */
//    public static CompletableFuture<Void> runAsync(Runnable runnable) {
//        return asyncRunStage(ASYNC_POOL, runnable);
//    }
//
//    /**
//     * Returns a new CompletableFuture that is asynchronously completed
//     * by a task running in the given executor after it runs the given
//     * action.
//     *
//     * @param runnable the action to run before completing the
//     *                 returned CompletableFuture
//     * @param executor the executor to use for asynchronous execution
//     * @return the new CompletableFuture
//     */
//    public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor) {
//        return asyncRunStage(screenExecutor(executor), runnable);
//    }
//
//    /**
//     * Returns a new CompletableFuture that is already completed with
//     * the given value.
//     *
//     * @param value the value
//     * @param <U>   the type of the value
//     * @return the completed CompletableFuture
//     */
//    public static <U> CompletableFuture<U> completedFuture(U value) {
//        return new CompletableFuture<U>((value == null) ? NIL : value);
//    }
//
//    /**
//     * Returns {@code true} if completed in any fashion: normally,
//     * exceptionally, or via cancellation.
//     *
//     * @return {@code true} if completed
//     */
//    public boolean isDone() {
//        return result != null;
//    }
//
//    /**
//     * Waits if necessary for this future to complete, and then
//     * returns its result.
//     *
//     * @return the result value
//     * @throws CancellationException if this future was cancelled
//     * @throws ExecutionException    if this future completed exceptionally
//     * @throws InterruptedException  if the current thread was interrupted
//     *                               while waiting
//     */
//    @SuppressWarnings("unchecked")
//    public T get() throws InterruptedException, ExecutionException {
//        Object r;
//
//        if ((r = result) == null) {
//            r = waitingGet(true);
//        }
//
//        return (T) reportGet(r);
//    }
//
//    /**
//     * Waits if necessary for at most the given time for this future
//     * to complete, and then returns its result, if available.
//     *
//     * @param timeout the maximum time to wait
//     * @param unit    the time unit of the timeout argument
//     * @return the result value
//     * @throws CancellationException if this future was cancelled
//     * @throws ExecutionException    if this future completed exceptionally
//     * @throws InterruptedException  if the current thread was interrupted
//     *                               while waiting
//     * @throws TimeoutException      if the wait timed out
//     */
//    @SuppressWarnings("unchecked")
//    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//        long nanos = unit.toNanos(timeout);
//        Object r;
//        if ((r = result) == null) r = timedGet(nanos);
//        return (T) reportGet(r);
//    }
//
//    /**
//     * Returns the result value when complete, or throws an
//     * (unchecked) exception if completed exceptionally. To better
//     * conform with the use of common functional forms, if a
//     * computation involved in the completion of this
//     * CompletableFuture threw an exception, this method throws an
//     * (unchecked) {@link CompletionException} with the underlying
//     * exception as its cause.
//     *
//     * @return the result value
//     * @throws CancellationException if the computation was cancelled
//     * @throws CompletionException   if this future completed
//     *                               exceptionally or a completion computation threw an exception
//     */
//    @SuppressWarnings("unchecked")
//    public T join() {
//        Object r;
//        if ((r = result) == null) r = waitingGet(false);
//        return (T) reportJoin(r);
//    }
//
//    /**
//     * Returns the result value (or throws any encountered exception)
//     * if completed, else returns the given valueIfAbsent.
//     *
//     * @param valueIfAbsent the value to return if not completed
//     * @return the result value, if completed, else the given valueIfAbsent
//     * @throws CancellationException if the computation was cancelled
//     * @throws CompletionException   if this future completed
//     *                               exceptionally or a completion computation threw an exception
//     */
//    @SuppressWarnings("unchecked")
//    public T getNow(T valueIfAbsent) {
//        Object r;
//        return ((r = result) == null) ? valueIfAbsent : (T) reportJoin(r);
//    }
//
//    /**
//     * 如果this已经执行完成, 那么返回false
//     * 如果未完成, 那么将value作为执行结果, 并且postComplete(), 返回true
//     */
//    public boolean complete(T value) {
//
//        // 1. CAS设置result
//        boolean triggered = completeValue(value);
//
//        // 2. 无论是否成功, 都会调用stack的completion
//        postComplete();
//
//        return triggered;
//    }
//
//    /**
//     * 和 {@link  CompletableFuture#complete(Object)} 基本相似
//     */
//    public boolean completeExceptionally(Throwable ex) {
//        if (ex == null) throw new NullPointerException();
//        boolean triggered = internalComplete(new AltResult(ex));
//        postComplete();
//        return triggered;
//    }
//
//    public <U> CompletableFuture<U> thenApply(Function<? super T, ? extends U> fn) {
//        return uniApplyStage(null, fn);
//    }
//
//    public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
//        return uniApplyStage(defaultExecutor(), fn);
//    }
//
//    public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
//        return uniApplyStage(screenExecutor(executor), fn);
//    }
//
//    public CompletableFuture<Void> thenAccept(Consumer<? super T> action) {
//        return uniAcceptStage(null, action);
//    }
//
//    public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action) {
//        return uniAcceptStage(defaultExecutor(), action);
//    }
//
//    public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
//        return uniAcceptStage(screenExecutor(executor), action);
//    }
//
//    public CompletableFuture<Void> thenRun(Runnable action) {
//        return uniRunStage(null, action);
//    }
//
//    public CompletableFuture<Void> thenRunAsync(Runnable action) {
//        return uniRunStage(defaultExecutor(), action);
//    }
//
//    public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor) {
//        return uniRunStage(screenExecutor(executor), action);
//    }
//
//    /*                          thenCombine (start)                          */
//    public <U, V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
//        return biApplyStage(null, other, fn);
//    }
//
//    public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
//        return biApplyStage(defaultExecutor(), other, fn);
//    }
//
//    public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
//        return biApplyStage(screenExecutor(executor), other, fn);
//    }
//
//    /*                          thenCombine (end)                          */
//
//    public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
//        return biAcceptStage(null, other, action);
//    }
//
//    public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
//        return biAcceptStage(defaultExecutor(), other, action);
//    }
//
//    public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) {
//        return biAcceptStage(screenExecutor(executor), other, action);
//    }
//
//
//    /*                          runAfterBoth (start)                          */
//
//    public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
//        return biRunStage(null, other, action);
//    }
//
//    public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
//        return biRunStage(defaultExecutor(), other, action);
//    }
//
//    public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
//        return biRunStage(screenExecutor(executor), other, action);
//    }
//
//    /*                          runAfterBoth (end)                          */
//
//    /*                          applyToEither (start)                          */
//
//    /**
//     * 两个Future只要有任一一个完成, 并且没有发生异常, 那么执行fn, fn的参数就是执行的结果, dep的结果为fn的返回值
//     */
//    public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
//        return orApplyStage(null, other, fn);
//    }
//
//    public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) {
//        return orApplyStage(defaultExecutor(), other, fn);
//    }
//
//    public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) {
//        return orApplyStage(screenExecutor(executor), other, fn);
//    }
//
//    /*                          applyToEither (end)                          */
//
//    /*                          acceptEither (start)                          */
//
//    /**
//     * 两个Future只要有任一一个完成, 并且没有发生异常, 那么执行action, action的参数就是执行的结果, dep的结果为Nil
//     */
//    public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
//        return orAcceptStage(null, other, action);
//    }
//
//    public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
//        return orAcceptStage(defaultExecutor(), other, action);
//    }
//
//    public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
//        return orAcceptStage(screenExecutor(executor), other, action);
//    }
//
//    /*                          acceptEither (end)                         */
//
//    /*                          runAfterEither (start)                          */
//    public CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
//        return orRunStage(null, other, action);
//    }
//
//    public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
//        return orRunStage(defaultExecutor(), other, action);
//    }
//
//    public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
//        return orRunStage(screenExecutor(executor), other, action);
//    }
//
//    /*                          runAfterEither (end)                          */
//
//    public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {
//        return uniComposeStage(null, fn);
//    }
//
//    public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {
//        return uniComposeStage(defaultExecutor(), fn);
//    }
//
//    public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
//        return uniComposeStage(screenExecutor(executor), fn);
//    }
//
//    public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
//        return uniWhenCompleteStage(null, action);
//    }
//
//    public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
//        return uniWhenCompleteStage(defaultExecutor(), action);
//    }
//
//    public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
//        return uniWhenCompleteStage(screenExecutor(executor), action);
//    }
//
//    public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
//        return uniHandleStage(null, fn);
//    }
//
//    public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
//        return uniHandleStage(defaultExecutor(), fn);
//    }
//
//    public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
//        return uniHandleStage(screenExecutor(executor), fn);
//    }
//
//    /**
//     * Returns this CompletableFuture.
//     *
//     * @return this CompletableFuture
//     */
//    public CompletableFuture<T> toCompletableFuture() {
//        return this;
//    }
//
//    /**
//     * 如果执行future时出现了异常, 那么回调fn, 并且将fn的返回值作为future的结果
//     */
//    public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn) {
//        return uniExceptionallyStage(fn);
//    }
//
//    /* ------------- Arbitrary-arity constructions -------------- */
//
//    public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs) {
//        return andTree(cfs, 0, cfs.length - 1);
//    }
//
//    public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs) {
//        int n;
//        Object r;
//
//        // 1. 0 ~ 1个future时
//        if ((n = cfs.length) <= 1) return (n == 0) ? new CompletableFuture<Object>() : uniCopyStage(cfs[0]);
//
//        // 2. 任一一个future已经有结果了
//        for (CompletableFuture<?> cf : cfs)
//            if ((r = cf.result) != null)
//                // 直接返回一个有结果的future
//                return new CompletableFuture<Object>(encodeRelay(r));
//
//        cfs = cfs.clone();
//
//        CompletableFuture<Object> d = new CompletableFuture<>();
//
//        // 3. 给所有的CompletableFuture添加一个 { AnyOf } 到栈顶
//        for (CompletableFuture<?> cf : cfs)
//            cf.unipush(new AnyOf(d, cf, cfs));
//
//        /*
//            4. 如果在上面的循环后, dep就已经有结果了
//               那么清理堆栈中可能已完成的Completion
//         */
//        if (d.result != null) {
//
//            for (int i = 0, len = cfs.length; i < len; i++) {
//
//                if (cfs[i].result != null) for (i++; i < len; i++) {
//
//                    if (cfs[i].result == null) {
//                        cfs[i].cleanStack();
//                    }
//
//                }
//
//            }
//
//        }
//        return d;
//    }
//
//    /* ------------- Control and status methods -------------- */
//
//    /**
//     * If not already completed, completes this CompletableFuture with
//     * a {@link CancellationException}. Dependent CompletableFutures
//     * that have not already completed will also complete
//     * exceptionally, with a {@link CompletionException} caused by
//     * this {@code CancellationException}.
//     *
//     * @param mayInterruptIfRunning this value has no effect in this
//     *                              implementation because interrupts are not used to control
//     *                              processing.
//     * @return {@code true} if this task is now cancelled
//     */
//    public boolean cancel(boolean mayInterruptIfRunning) {
//        // 1. 确保 result == null && { internalComplete (CAS) } 成功
//        boolean cancelled = (result == null) && internalComplete(new AltResult(new CancellationException()));
//        // 2. 调用stack上的completion
//        postComplete();
//        return cancelled || isCancelled();
//    }
//
//    /**
//     * Returns {@code true} if this CompletableFuture was cancelled
//     * before it completed normally.
//     *
//     * @return {@code true} if this CompletableFuture was cancelled
//     * before it completed normally
//     */
//    public boolean isCancelled() {
//        Object r;
//        return ((r = result) instanceof AltResult) && (((AltResult) r).ex instanceof CancellationException);
//    }
//
//    /**
//     * Returns {@code true} if this CompletableFuture completed
//     * exceptionally, in any way. Possible causes include
//     * cancellation, explicit invocation of {@code
//     * completeExceptionally}, and abrupt termination of a
//     * CompletionStage action.
//     *
//     * @return {@code true} if this CompletableFuture completed
//     * exceptionally
//     */
//    public boolean isCompletedExceptionally() {
//        Object r;
//        return ((r = result) instanceof AltResult) && r != NIL;
//    }
//
//    /**
//     * Forcibly sets or resets the value subsequently returned by
//     * method {@link #get()} and related methods, whether or not
//     * already completed. This method is designed for use only in
//     * error recovery actions, and even in such situations may result
//     * in ongoing dependent completions using established versus
//     * overwritten outcomes.
//     *
//     * @param value the completion value
//     */
//    public void obtrudeValue(T value) {
//        result = (value == null) ? NIL : value;
//        postComplete();
//    }
//
//    /**
//     * Forcibly causes subsequent invocations of method {@link #get()}
//     * and related methods to throw the given exception, whether or
//     * not already completed. This method is designed for use only in
//     * error recovery actions, and even in such situations may result
//     * in ongoing dependent completions using established versus
//     * overwritten outcomes.
//     *
//     * @param ex the exception
//     * @throws NullPointerException if the exception is null
//     */
//    public void obtrudeException(Throwable ex) {
//        if (ex == null) throw new NullPointerException();
//        result = new AltResult(ex);
//        postComplete();
//    }
//
//    /**
//     * 返回其CompletableFutures的估计数量
//     * 正在等待完成此CompletableFuture。
//     * 此方法设计用于监控系统状态，而不是
//     * 用于同步控制。
//     *
//     * @return 依赖CompletableFutures的数量
//     */
//    public int getNumberOfDependents() {
//        int count = 0;
//        for (Completion p = stack; p != null; p = p.next)
//            ++count;
//        return count;
//    }
//
//    /**
//     * Returns a string identifying this CompletableFuture, as well as
//     * its completion state.  The state, in brackets, contains the
//     * String {@code "Completed Normally"} or the String {@code
//     * "Completed Exceptionally"}, or the String {@code "Not
//     * completed"} followed by the number of CompletableFutures
//     * dependent upon its completion, if any.
//     *
//     * @return a string identifying this CompletableFuture, as well as its state
//     */
//    public String toString() {
//        Object r = result;
//        int count = 0; // avoid call to getNumberOfDependents in case disabled
//        for (Completion p = stack; p != null; p = p.next)
//            ++count;
//        return super.toString() + ((r == null) ? ((count == 0) ? "[Not completed]" : "[Not completed, " + count + " dependents]") : (((r instanceof AltResult) && ((AltResult) r).ex != null) ? "[Completed exceptionally: " + ((AltResult) r).ex + "]" : "[Completed normally]"));
//    }
//
//    // jdk9 additions
//
//    /**
//     * Returns a new incomplete CompletableFuture of the type to be
//     * returned by a CompletionStage method. Subclasses should
//     * normally override this method to return an instance of the same
//     * class as this CompletableFuture. The default implementation
//     * returns an instance of class CompletableFuture.
//     *
//     * @param <U> the type of the value
//     * @return a new CompletableFuture
//     * @since 9
//     */
//    public <U> CompletableFuture<U> newIncompleteFuture() {
//        return new CompletableFuture<U>();
//    }
//
//    /**
//     * Returns the default Executor used for async methods that do not
//     * specify an Executor. This class uses the {@link
//     * ForkJoinPool#commonPool()} if it supports more than one
//     * parallel thread, or else an Executor using one thread per async
//     * task.  This method may be overridden in subclasses to return
//     * an Executor that provides at least one independent thread.
//     *
//     * @return the executor
//     * @since 9
//     */
//    public Executor defaultExecutor() {
//        return ASYNC_POOL;
//    }
//
//    /**
//     * Returns a new CompletableFuture that is completed normally with
//     * the same value as this CompletableFuture when it completes
//     * normally. If this CompletableFuture completes exceptionally,
//     * then the returned CompletableFuture completes exceptionally
//     * with a CompletionException with this exception as cause. The
//     * behavior is equivalent to {@code thenApply(x -> x)}. This
//     * method may be useful as a form of "defensive copying", to
//     * prevent clients from completing, while still being able to
//     * arrange dependent actions.
//     *
//     * @return the new CompletableFuture
//     * @since 9
//     */
//    public CompletableFuture<T> copy() {
//        return uniCopyStage(this);
//    }
//
//    /**
//     * Returns a new CompletionStage that is completed normally with
//     * the same value as this CompletableFuture when it completes
//     * normally, and cannot be independently completed or otherwise
//     * used in ways not defined by the methods of interface {@link
//     * CompletionStage}.  If this CompletableFuture completes
//     * exceptionally, then the returned CompletionStage completes
//     * exceptionally with a CompletionException with this exception as
//     * cause.
//     *
//     * <p>Unless overridden by a subclass, a new non-minimal
//     * CompletableFuture with all methods available can be obtained from
//     * a minimal CompletionStage via {@link #toCompletableFuture()}.
//     * For example, completion of a minimal stage can be awaited by
//     *
//     * <pre> {@code minimalStage.toCompletableFuture().join(); }</pre>
//     *
//     * @return the new CompletionStage
//     * @since 9
//     */
//    public CompletionStage<T> minimalCompletionStage() {
//        return uniAsMinimalStage();
//    }
//
//    /**
//     * 再提供一个supplier, 如果该future已经执行完成
//     * 那么supplier将不会被执行, 否则执行supplier, 并且将执行结果赋值给当前future
//     */
//    public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor) {
//        if (supplier == null || executor == null) {
//            throw new NullPointerException();
//        }
//
//        // 和 static CompletableFuture<?> supplyAsync() 一样, 只不过AsyncSupplier的dep是this
//        executor.execute(new AsyncSupply<T>(this, supplier));
//
//        return this;
//    }
//
//    /**
//     * Completes this CompletableFuture with the result of the given
//     * Supplier function invoked from an asynchronous task using the
//     * default executor.
//     *
//     * @param supplier a function returning the value to be used
//     *                 to complete this CompletableFuture
//     * @return this CompletableFuture
//     * @since 9
//     */
//    public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier) {
//        return completeAsync(supplier, defaultExecutor());
//    }
//
//    /**
//     * Exceptionally completes this CompletableFuture with
//     * a {@link TimeoutException} if not otherwise completed
//     * before the given timeout.
//     *
//     * @param timeout how long to wait before completing exceptionally
//     *                with a TimeoutException, in units of {@code unit}
//     * @param unit    a {@code TimeUnit} determining how to interpret the
//     *                {@code timeout} parameter
//     * @return this CompletableFuture
//     * @since 9
//     */
//    public CompletableFuture<T> orTimeout(long timeout, TimeUnit unit) {
//        if (unit == null) throw new NullPointerException();
//        if (result == null) whenComplete(new Canceller(Delayer.delay(new Timeout(this), timeout, unit)));
//        return this;
//    }
//
//    /**
//     * 如果在给定的时间后, future还没完成, 那么使用value作为future的结果
//     */
//    public CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit) {
//        if (unit == null) throw new NullPointerException();
//
//        if (result == null) {
//
//            // 1. DelayedCompleter: Runnable = ()-> { future.complete(value) },
//            DelayedCompleter<T> delayedCompleter = new DelayedCompleter<>(this, value);
//
//            // 2. 使用ScheduleExecutorService, 在指定的timeout后调用 delayedCompleter.run()
//            ScheduledFuture<?> delayScheduleFuture = Delayer.delay(delayedCompleter, timeout, unit);
//
//            // 3. 创建一个Canceller, canceller在(this)执行完成后调用, 无论是成功还是失败都会调用
//            // 第一个参数是执行结果, 第二个参数是异常
//            Canceller canceller = new Canceller(delayScheduleFuture);
//
//            // 4. 将canceller推送到stack, (this)执行完成后回调
//            whenComplete(canceller);
//        }
//        return this;
//    }
//
//    /**
//     * Returns a new Executor that submits a task to the given base
//     * executor after the given delay (or no delay if non-positive).
//     * Each delay commences upon invocation of the returned executor's
//     * {@code execute} method.
//     *
//     * @param delay    how long to delay, in units of {@code unit}
//     * @param unit     a {@code TimeUnit} determining how to interpret the
//     *                 {@code delay} parameter
//     * @param executor the base executor
//     * @return the new delayed executor
//     * @since 9
//     */
//    public static Executor delayedExecutor(long delay, TimeUnit unit, Executor executor) {
//        if (unit == null || executor == null) throw new NullPointerException();
//        return new DelayedExecutor(delay, unit, executor);
//    }
//
//    /**
//     * Returns a new Executor that submits a task to the default
//     * executor after the given delay (or no delay if non-positive).
//     * Each delay commences upon invocation of the returned executor's
//     * {@code execute} method.
//     *
//     * @param delay how long to delay, in units of {@code unit}
//     * @param unit  a {@code TimeUnit} determining how to interpret the
//     *              {@code delay} parameter
//     * @return the new delayed executor
//     * @since 9
//     */
//    public static Executor delayedExecutor(long delay, TimeUnit unit) {
//        if (unit == null) throw new NullPointerException();
//        return new DelayedExecutor(delay, unit, ASYNC_POOL);
//    }
//
//    /**
//     * Returns a new CompletionStage that is already completed with
//     * the given value and supports only those methods in
//     * interface {@link CompletionStage}.
//     *
//     * @param value the value
//     * @param <U>   the type of the value
//     * @return the completed CompletionStage
//     * @since 9
//     */
//    public static <U> CompletionStage<U> completedStage(U value) {
//        return new MinimalStage<U>((value == null) ? NIL : value);
//    }
//
//    /**
//     * Returns a new CompletableFuture that is already completed
//     * exceptionally with the given exception.
//     *
//     * @param ex  the exception
//     * @param <U> the type of the value
//     * @return the exceptionally completed CompletableFuture
//     * @since 9
//     */
//    public static <U> CompletableFuture<U> failedFuture(Throwable ex) {
//        if (ex == null) throw new NullPointerException();
//        return new CompletableFuture<U>(new AltResult(ex));
//    }
//
//    /**
//     * Returns a new CompletionStage that is already completed
//     * exceptionally with the given exception and supports only those
//     * methods in interface {@link CompletionStage}.
//     *
//     * @param ex  the exception
//     * @param <U> the type of the value
//     * @return the exceptionally completed CompletionStage
//     * @since 9
//     */
//    public static <U> CompletionStage<U> failedStage(Throwable ex) {
//        if (ex == null) throw new NullPointerException();
//        return new MinimalStage<U>(new AltResult(ex));
//    }
//
//    /**
//     * Singleton delay scheduler, used only for starting and
//     * cancelling tasks.
//     */
//    static final class Delayer {
//        static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
//            return delayer.schedule(command, delay, unit);
//        }
//
//        static final class DaemonThreadFactory implements ThreadFactory {
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
//                t.setDaemon(true);
//                t.setName("CompletableFutureDelayScheduler");
//                return t;
//            }
//        }
//
//        static final ScheduledThreadPoolExecutor delayer;
//
//        static {
//            (delayer = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory())).setRemoveOnCancelPolicy(true);
//        }
//    }
//
//// Little class-ified lambdas to better support monitoring
//
//    static final class DelayedExecutor implements Executor {
//        final long delay;
//        final TimeUnit unit;
//        final Executor executor;
//
//        DelayedExecutor(long delay, TimeUnit unit, Executor executor) {
//            this.delay = delay;
//            this.unit = unit;
//            this.executor = executor;
//        }
//
//        public void execute(Runnable r) {
//            Delayer.delay(new TaskSubmitter(executor, r), delay, unit);
//        }
//    }
//
//    /**
//     * Action to submit user task
//     */
//    static final class TaskSubmitter implements Runnable {
//        final Executor executor;
//        final Runnable action;
//
//        TaskSubmitter(Executor executor, Runnable action) {
//            this.executor = executor;
//            this.action = action;
//        }
//
//        public void run() {
//            executor.execute(action);
//        }
//    }
//
//    /**
//     * Action to completeExceptionally on timeout
//     */
//    static final class Timeout implements Runnable {
//        final CompletableFuture<?> f;
//
//        Timeout(CompletableFuture<?> f) {
//            this.f = f;
//        }
//
//        public void run() {
//            if (f != null && !f.isDone()) f.completeExceptionally(new TimeoutException());
//        }
//    }
//
//    /**
//     * Action to complete on timeout
//     */
//    static final class DelayedCompleter<U> implements Runnable {
//        final CompletableFuture<U> f;
//        final U u;
//
//        DelayedCompleter(CompletableFuture<U> f, U u) {
//            this.f = f;
//            this.u = u;
//        }
//
//        public void run() {
//            if (f != null) f.complete(u);
//        }
//    }
//
//    /**
//     * Action to cancel unneeded timeouts
//     */
//    static final class Canceller implements BiConsumer<Object, Throwable> {
//        final Future<?> f;
//
//        Canceller(Future<?> f) {
//            this.f = f;
//        }
//
//        public void accept(Object ignore, Throwable ex) {
//            if (ex == null && f != null && !f.isDone()) f.cancel(false);
//        }
//    }
//
//    /**
//     * A subclass that just throws UOE for most non-CompletionStage methods.
//     */
//    static final class MinimalStage<T> extends CompletableFuture<T> {
//        MinimalStage() {
//        }
//
//        MinimalStage(Object r) {
//            super(r);
//        }
//
//        @Override
//        public <U> CompletableFuture<U> newIncompleteFuture() {
//            return new MinimalStage<U>();
//        }
//
//        @Override
//        public T get() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public T get(long timeout, TimeUnit unit) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public T getNow(T valueIfAbsent) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public T join() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean complete(T value) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean completeExceptionally(Throwable ex) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean cancel(boolean mayInterruptIfRunning) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public void obtrudeValue(T value) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public void obtrudeException(Throwable ex) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean isDone() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean isCancelled() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean isCompletedExceptionally() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public int getNumberOfDependents() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public CompletableFuture<T> orTimeout(long timeout, TimeUnit unit) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public CompletableFuture<T> toCompletableFuture() {
//            Object r;
//            if ((r = result) != null) return new CompletableFuture<T>(encodeRelay(r));
//            else {
//                CompletableFuture<T> d = new CompletableFuture<>();
//                unipush(new UniRelay<T, T>(d, this));
//                return d;
//            }
//        }
//    }
//
//    // VarHandle mechanics
//    private static final VarHandle RESULT;
//    private static final VarHandle STACK;
//    private static final VarHandle NEXT;
//
//    static {
//        try {
//            MethodHandles.Lookup l = MethodHandles.lookup();
//            RESULT = l.findVarHandle(CompletableFuture.class, "result", Object.class);
//            STACK = l.findVarHandle(CompletableFuture.class, "stack", Completion.class);
//            NEXT = l.findVarHandle(Completion.class, "next", Completion.class);
//        } catch (ReflectiveOperationException e) {
//            throw new ExceptionInInitializerError(e);
//        }
//
//        // Reduce the risk of rare disastrous classloading in first call to
//        // LockSupport.park: https://bugs.openjdk.java.net/browse/JDK-8074773
//        Class<?> ensureLoaded = LockSupport.class;
//    }
//}
