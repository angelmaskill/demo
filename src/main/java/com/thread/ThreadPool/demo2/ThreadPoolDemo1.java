package com.thread.ThreadPool.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author myl
 * <p>
 * 线程池的submit方法和excute()的方法的区别： 1：两者都是利用线程池中的线程执行传入的任务 2：submit()
 * 会返回结果(具体返回Future或FutureTask)，excute()返回void
 * @2012-4-13上午10:46:26
 */
@SuppressWarnings("unused")
public class ThreadPoolDemo1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
    }

    private void testNewFixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + " loop of " + j + " on task " + task);
                    }
                }
            });
        }
        System.out.println(" all task execut complete!");
    }

    private void testNewScheduledThreadPool() {
        /*
         * 定时器线程池
         */
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            public void run() {
                System.out.println(" bombing!");
            }
        }, 5, TimeUnit.SECONDS);// 多久后开始bombing
    }

    private void testNewScheduledThreadPool2() {
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "," + " bombing!");// 多久后开始bombing，然后每个多久bomning
            }
        }, 5, 2, TimeUnit.SECONDS);
    }
}