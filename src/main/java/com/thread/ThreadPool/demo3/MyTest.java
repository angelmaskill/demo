package com.thread.ThreadPool.demo3;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-4-18 10:38
 * @description：
 * @modified By：
 * @version:
 */

public class MyTest extends TestCase {

    @Test
    public void testTimeout1() throws Exception {
        int timeout = 3000;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = step1(executor);
        try {
            Integer result = future.get(timeout, TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName() + " get result:" + result);
        } catch (TimeoutException e) {
            System.out.println(Thread.currentThread().getName() + " 超时了!");
        }
        step2();
    }

    /**
     * summit 一个callable  ，拿到一个future.
     *
     * @param executor
     * @return
     */
    private Future<Integer> step1(ExecutorService executor) {
        Future<Integer> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " run task!");
            Thread.sleep(5000);
            return 100;
        });
        return future;
    }

    /**
     * 如果代码1执行时间过长则不再执行（代码1没有没按照规定时间执行完），继续执行后面的代码2
     *
     * @throws Exception
     */
    @Test
    public void testTimeout2() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> future = stepOne(executorService);
        try {
            String result = future.get(5, TimeUnit.SECONDS);
            //String result=future.get(50,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " get result:" + result);
        } catch (TimeoutException e) {
            System.out.println(Thread.currentThread().getName() + " 超时了!");
        }
        step2();
    }

    /**
     * execute 一个 futureTask. 从 futureTask中拿数据
     *
     * @param executorService
     * @return
     */
    private FutureTask<String> stepOne(ExecutorService executorService) {
        FutureTask<String> future = new FutureTask<>(() ->
        {
            System.out.println(Thread.currentThread().getName() + " run task!");
            Thread.sleep(10000);
            return "success";
        }
        );
        executorService.execute(future);
        return future;
    }

    private static void step2() {
        System.out.println(Thread.currentThread().getName() + " run step2 finish!");
    }
}
