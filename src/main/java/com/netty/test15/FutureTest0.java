package com.netty.test15;

import junit.framework.TestCase;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * jdk future 练习
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-25 13:14
 * @description：
 * @modified By：
 * @version:
 */
public class FutureTest0 extends TestCase {
    @Test
    @SneakyThrows
    public void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> submit = executorService.submit(() -> {
            Thread.sleep(3000);
            return 1;
        });
        // 用户线程需要主动轮询 Future 线程是否完成当前任务
        // Future并不是真正的异步，因为它少了一个回调，充其量只能算是一个同步非阻塞模式。
        Integer o = submit.get();
        System.out.println(o);

        /**
         *  通过 FutureTask 包装异步线程的返回，返回结果在 FutureTask 中获取而不是 在提交线程中
         */
        FutureTask<Integer> submit1 = (FutureTask<Integer>) executorService.submit(() -> {
            Thread.sleep(3000);
            return 1;
        });
        //我们会发现如果 future 提交给线程池执行之后立刻 get()，其实执行效率并不会变高，反而由于线程的开销会比同步调用更慢。这种将来式的 future 适用多个耗时操作并发执行的场景。
        Integer integer = submit1.get();
        System.out.println(integer);
    }

    @Test
    @SneakyThrows
    public void testCompletableFuture() {
        System.out.println("start");
        // CompletableFuture.runAsync()方法提供了异步执行无返回值任务的功能。
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep done");
        });
        Thread.sleep(4000);
        System.out.println("done");
    }
}
