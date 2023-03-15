package com.zzzj.juc;

import cn.hutool.core.thread.NamedThreadFactory;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompletableFutureTest {

    @Test
    public void create() {
        // 1. 使用runAsync,返回的future的泛型是Void,即无返回值的future
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> {
        });

        // 2. 使用supplyAsync,默认提交任务给ForkJoinPool.commonPool
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> "hello world");

        // 3. 使用supplyAsync,提交给指定的线程池
        CompletableFuture<String> supplyFuture2 = CompletableFuture.supplyAsync(() -> "hello world", Executors.newFixedThreadPool(1));
    }

    /**
     * 1. get() 阻塞获取
     * 2. get(long time, TimeUnit unit) 超时阻塞获取
     * 3. join() , 同get(),但是无编译异常
     * 4. getNow(valueIfAbsent) 立即获取,如果future还未完成,则返回传进去的参数
     * 5. complete(valueIfAbsent) 立即获取
     * 1. 如果future完成,返回true,再使用future.join()获取完成的值
     * 2. 如果future未完成,返回false,再使用future.join()获取传递的valueIfAbsent
     */
    @Test
    public void getResult() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world");

        // 1. 阻塞获取
        future.get();
        future.join();

        // 2. 定时阻塞获取
        future.get(1, TimeUnit.MINUTES);

        // 3. 立即获取,如果future还未完成,则返回传进去的参数
        future.getNow("ifAbsent");

        // 4. 立即获取
        boolean completed = future.complete("ifAbsent");

        if (completed) {
            assertThat("hello world", is(future.get()));
        } else {
            assertThat("ifAbsent", is(future.get()));
        }
    }

    /**
     * 三个then , thenAccept , thenApply , thenRun
     */
    @Test
    public void then() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world");

        // 1. 消费结果
        future.thenAccept(s -> {

        });

        // 2. 消费结果并且执行下一步骤
        future.thenApply(s -> {
            return s + " thenApply";
        });

        // 3. 不消费结果执行下一步骤
        future.thenRun(() -> {
        });
    }

    /**
     * 5. 结论: 一致不使用async,则使用创建future时指定的线程池
     * 一旦开始使用async,后续一直使用ForkJoinPool的线程池
     */
    @Test
    public void thenWithAsync() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world", Executors.newFixedThreadPool(3, new NamedThreadFactory("zzzj", false)));

        // 1. 一开始未使用Async,后续也未使用async,则一直使用创建Future的线程池
        future.thenAccept(unused -> {
            // threadName is zzzjPool's Thread
        }).thenAccept(unused -> {
            // threadName is zzzjPool's Thread
        });

        // 2. 一开始未使用Async,后续使用async,则使用async后使用commonJoinPool
        future.thenAccept(unused -> {
            // threadName is zzzjPool's Thread
        }).thenAcceptAsync(unused -> {
            // threadName is ForkJoinPool's Thread
        }).thenAcceptAsync(unused -> {
            // threadName is ForkJoinPool's Thread
        });

        // 3. 一开始使用async,后续不使用async,还是和上面规则一致,使用async后的继续使用forkJoin的线程
        future.thenAcceptAsync(unused -> {
            // threadName is ForkJoinPool's Thread
            System.out.println(" 1 -> " + Thread.currentThread().getName());
        }).thenAccept(unused -> {
            // threadName is ForkJoinPool's Thread
            System.out.println(" 2 -> " + Thread.currentThread().getName());
        });

        // 4. 一开始使用async,后续也使用async, 同3

        Thread.currentThread().join();
    }

    /**
     * handle和apply的区别在于
     * <p>
     * 1. 一旦某个apply方法中抛出了异常,后续的apply | accept | run方法将不再执行,如果指定了exceptionally,则会把异常传递给该方法
     * <p>
     * 2. 使用handle方法接收一个结果对象和异常对象,所以即使上面的 apply | accept | run | handle方法抛出了异常, 下面的handle方法依然会执行
     */
    @Test
    public void handle() throws Exception {
        // 1. thenApply
        Object result = CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("some exception");
                })
                .thenApplyAsync(o -> {
                    // 走不到这里
                    return null;
                })
                .exceptionally(throwable -> {
                    System.out.println(throwable.getMessage());
                    return null;
                }).get();

        assertThat(result, IsNull.nullValue());

        // 2. handle
        Object handleResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(1 / 0);
            return "some result";
        }).handleAsync((res, throwable) -> {
            // 可以走到这里
            if (throwable == null) {
                return res;
            }
            return "hand result";
        }).get();

        assertThat(handleResult, is("hand result"));
    }

    /**
     * <pre>
     * future1.applyToEither(future2, (result) -> {
     *    future1和future2谁更快返回,result就是更快的future的结果
     * })
     * </pre>
     */
    @Test
    public void race() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello world111";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello world222";
        });

        // future1更快返回,此处的s是future的返回值
        future1.applyToEither(future2, s -> {
            assertThat(s, is("hello world111"));
            return s;
        });

    }

    /**
     * <pre>
     * future1.combine(future2, (result1, result2) -> {
     *      result1 === future1.result
     *      result2 === future2.result
     * })
     * </pre>
     */
    @Test
    public void combine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello world111";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello world222";
        });

        future1.thenCombine(future2, (s, s2) -> {
            // s  === hello world111
            // s2 === hello world222
            return s + s2;
        });

        Thread.currentThread().join();
    }

    /**
     * 再组合另外一个CompletableFuture, 两个 ( this ) 和 ( other )只要有一个完成 ( 非异常完成 ) , 就执行 { consumer }
     * 如果两个future任一一个出现了异常, consumer都不会执行
     */
    @Test
    public void acceptEitherAsync() throws Exception {

        Executor executor = new DirectExecutor();

        CompletableFuture.supplyAsync(() -> "zzzj", executor)
                .acceptEitherAsync(
                        // other
                        CompletableFuture.completedFuture("haha"),
                        // consumer
                        System.out::println,
                        // 线程池
                        executor
                );

    }

    @Test
    public void allOfTests() throws Exception {

        Executor executor = Executors.newFixedThreadPool(4);

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "f1", executor);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "f2", executor);
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "f3", executor);

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(f1, f2, f3);
    }

}
