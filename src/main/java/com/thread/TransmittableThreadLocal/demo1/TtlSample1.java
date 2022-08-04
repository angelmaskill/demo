package com.thread.transmittableThreadLocal.demo1;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TransmittableThreadLocal是阿里开源的一个类，主要目的是处理父子线程变量不能共用的情况。
 * ThreadLocal是跟当前线程挂钩的，所以脱离当前线程它就起不了作用。
 *
 * <pre>
 *      TransmittableThreadLocal跟InheritableThreadLocal的区别?
 *      1）InheritableThreadLocal解决父子线程的问题，它是在线程创建的时候进行复制上下文的。那么对于线程池的已经创建完了就无从下手了，
 *
 *      2）TransmittableThreadLocal 是在线程提交的时候要进行上下文的复制：要支持线程池中能访问提交任务线程的本地变量，其实只需要在
 *      父线程向线程池提交任务时复制父线程的上下环境，那在子线程中就能够如愿访问到父线程中的本地变量，实现本地环境变量在线程池调用中
 *      的透传，从而为实现链路跟踪打下坚实的基础
 * </pre>
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-6-8 18:53
 * @description：
 * @modified By：
 * @version:
 */
public class TtlSample1 {
    // 跨线程传递 ThreadLocal变量
    static InheritableThreadLocal<String> TTL = new InheritableThreadLocal<>();
    // 声明一个支持线程池获取上线文的ThreadLocal变量
    static ThreadLocal<String> context = new TransmittableThreadLocal<>();


    public static void main(String[] args) throws Exception {
        //testThread();
        testThreadPool();
    }

    private static void testThreadPool() {
        // 额外的处理，生成修饰了的对象executorService，返回来ExecutorServiceTtlWrapper 对象。
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        // 外层设置需要传递的值
        context.set("set-parent-thread");
        // 打印当前主线程的上线文内容
        printThreadPoolContext();
        Runnable task1 = new Runnable() {
            // 进入run的时候，把父线程的参数备份；用自己所在线程看到的参数推进执行；
            // 执行完毕之后，要退出子线程，需要使用之前备份的父线程参数进行还原。
            @Override
            public void run() {
                printThreadPoolContext();
                context.set("set-sub-thread111");
                printThreadPoolContext();
            }
        };
        executorService.submit(task1);
        //修改主线程的值
        //printThreadPoolContext();
        context.set("set-parent-thread-new!");
        //printThreadPoolContext();
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                //此时这里期望看到最新主线程设置的值（因为线程的复用，此处必须能拿到提交的任务的时候传递的上下文）
                printThreadPoolContext();
                context.set("set-sub-thread222");
                printThreadPoolContext();
            }
        };
        executorService.submit(task2);


    }

    private static void testThread() {
        // 在父线程中设置变量
        TTL.set("throwable");
        printThreadContext();
        new Thread(() -> {
            printThreadContext();
            TTL.set("throwable1111");
            new Thread(() -> {
                TTL.set("throwable2222");
                printThreadContext();
            }).start();
            try {
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void printThreadContext() {
        System.out.println(Thread.currentThread() + " get context: " + TTL.get());
    }

    private static void printThreadPoolContext() {
        System.out.println(Thread.currentThread() + " get context: " + context.get());
    }
}