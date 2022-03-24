package com.thread.ThreadPool.demo2;

import commonutil.DateUtils;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.*;

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
        new ThreadPoolDemo1().testMyThreadPool();
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

    /**
     * 测试自定义线程池
     */
    private void testMyThreadPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 7, 60l, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4));
        for (int i = 1; i <= 6; i++) {
            pool.execute(new mytask("张" + i, pool));
        }
    }

    class mytask implements Runnable {
        private String name;
        private ThreadPoolExecutor pool;


        public mytask(String name, ThreadPoolExecutor pool) {
            this.name = name;
            this.pool = pool;
        }

        @SneakyThrows
        @Override
        public void run() {
            print(pool);
            System.out.println(DateUtils.dateToString(new Date()) + " name " + name + " is  running");
            Thread.sleep(4000);
            System.out.println(DateUtils.dateToString(new Date()) + " name " + name + " is  finished");
            print(pool);
        }
    }

    private void print(ThreadPoolExecutor threadPoolExecutor) {
        int poolSize = threadPoolExecutor.getPoolSize();
        long taskCount = threadPoolExecutor.getTaskCount();
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        int size = threadPoolExecutor.getQueue().size();
        System.out.println(DateUtils.dateToString(new Date()) + " 当前池子里的线程数:" + poolSize + "；任务数目:" + taskCount + "；核心线程数目:" + corePoolSize+"; 队列中任务数量："+size);
    }
}