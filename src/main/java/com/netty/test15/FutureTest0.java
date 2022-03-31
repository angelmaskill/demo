package com.netty.test15;

import junit.framework.TestCase;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * jdk future 练习
 *
 * <p>
 * JDK 5 引入了 Future 模式。Future 接口是 Java 多线程 Future 模式的实现，
 * 在 java.util.concurrent包中，可以来进行异步计算。
 *
 * <p>
 * 对于异步编程，我们想要的实现是：提交一个任务，在任务执行期间提交者可以做别的事情，
 * 这个任务是在异步执行的，当任务执行完毕通知提交者任务完成获取结果。
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-25 13:14
 * @description：
 * @modified By：
 * @version:
 */
public class FutureTest0 extends TestCase {
    /**
     * executorService.submit()方法获取带返回值的 Future 结果有两种方式：
     * 1。 一种是通过实现 Callable接口；
     * 2。 第二种是中间变量返回。继承 Future 的子类: FutureTask，通过 FutureTask 返回异步结果而不是在主线程中获取（FutureTask 本质也是使用 Callable 进行创建）。
     */
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
    }

    //通过 FutureTask 包装异步线程的返回，返回结果在 FutureTask 中获取而不是 在提交线程中
    @Test
    @SneakyThrows
    public void testFuture2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<Integer> submit = (FutureTask<Integer>) executorService.submit(new NewCallableTask());
        // Future 提供的功能是：用户线程需要主动轮询 Future 线程是否完成当前任务，如果不通过轮询是否完成而是同步等待获取则会阻塞直到执行完毕为止。
        // 我们会发现如果 future 提交给线程池执行之后立刻 get()，其实执行效率并不会变高，反而由于线程的开销会比同步调用更慢。这种将来式的 future 适用多个耗时操作并发执行的场景。
        Integer integer = submit.get();
        System.out.println(integer);
    }

    /**
     * 通过实现 Callable 接口
     */
    static class NewCallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }
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
