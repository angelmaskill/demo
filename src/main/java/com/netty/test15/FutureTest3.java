package com.netty.test15;


import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * CompletableFuture 练习
 *
 * <p>
 * 前面提到了 Netty 和 Guava 的扩展都提供了 addListener 这样的接口，用于处理 Callback 调用，但其实 jdk1.8 已经提供了一种更为高级的回
 * 调方式：CompletableFuture。首先尝试用 CompletableFuture 来解决回调的问题。
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-31 13:26
 * @description：
 * @modified By：
 * @version:
 */
public class FutureTest3 extends TestCase {

    /**
     * 主线程运算耗时:43ms
     * ForkJoinPool.commonPool-worker-9线程拿到结果：100
     *
     * <pre>
     * 可以发现耗时操作没有占用主线程的时间片，达到了异步调用的效果。我们也不需要引入任何第三方的依赖，这都是依赖于
     * java.util.concurrent.CompletableFuture 的出现。CompletableFuture 提供了近 50 多个方法，
     * 大大便捷了 java 多线程操作，和异步调用的写法。
     * </pre>
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        long l = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行耗时操作...");
            timeConsumingOperation();
            return 100;
        });
        completableFuture.whenComplete((result, e) -> {
            System.out.println(Thread.currentThread().getName() + "线程拿到结果：" + result);
        });
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
        new CountDownLatch(1).await();
    }


    // 合并两个异步任务
    // 如果有两个任务需要异步执行，且后面需要对这两个任务的结果进行合并处理，CompletableFuture 也支持这种处理：
    @Test
    public void testCombine() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "task1", executorService);
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "task2", executorService);
        CompletableFuture<String> combine = task1.thenCombineAsync(task2, (a, b) -> a + b);
        String s = combine.get();
        System.out.println(s);
    }

    // 下一个依赖上一个的结果
    //CompletableFuture.thenComposeAsync()支持将第一个任务的结果传入第二个任务中。
    @Test
    public void testDependence() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "task1", executorService);
        CompletableFuture<String> task2 = task1.thenComposeAsync(a -> CompletableFuture.supplyAsync(() -> a + " task2"));
        System.out.println(task2.get());
    }

    /**
     * 解决回调地狱问题
     * <p>
     * 使用 thenCompose 或者 thenComposeAsync 等方法可以实现回调的回调，且写出来的方法易于维护。
     */
    @Test
    public void testCompletableFuture() throws InterruptedException {
        long l = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("在回调中执行耗时操作...");
            timeConsumingOperation();
            return 100;
        });
        completableFuture = completableFuture.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("在回调的回调中执行耗时操作...");
                timeConsumingOperation();
                return i + 100;
            });
        });//<1>
        completableFuture.whenComplete((result, e) -> {
            System.out.println("计算结果:" + result);
        });
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
        new CountDownLatch(1).await();
    }


    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}