/**
 * @(#)TestFutureTask3.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-10-24     Administrator    Created
 **********************************************
 */

package com.thread.testFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-24
 */
public class TestFutureTask3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long begin= System.currentTimeMillis();
        System.out.println(begin+"begin...");
        TestFutureTask3 test = new TestFutureTask3();

        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建两个有返回值的任务
        Callable c1 = test.new MyCallable(75);
        Callable c2 = test.new MyCallable2(75);

        // 执行任务并获取Future对象
        Future f1 = pool.submit(c1);
        Future f2 = pool.submit(c2);

        // 从Future对象上获取任务的返回值，并输出到控制台
        System.out.println(">>>" + f2.get().toString());
        System.out.println(">>>" + f1.get().toString());
        
        int  v1 = Integer.parseInt(f1.get().toString());
        int  v2 = Integer.parseInt(f2.get().toString());
        
        System.out.println(v1+v2);
        
        // 关闭线程池
        pool.shutdown();
        long end= System.currentTimeMillis();
        System.out.println("总共耗时："+(end-begin)/1000);
    }

    class MyCallable implements Callable {
        private int oid;

        MyCallable(int count) {
            this.oid = count;
        }

        public Object call() throws Exception {
            int i=0;
            for (i = 0; i < oid; i++) {
                i++;
                System.out.println("====================="+i);
                Thread.currentThread().sleep(200);
            }
            return i;
        }
    }
    class MyCallable2 implements Callable {
        private int oid;

        MyCallable2(int count) {
            this.oid = count;
        }

        public Object call() throws Exception {
            int i=0;
            for (i = 0; i < oid; i++) {
                i++;
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+i);
                Thread.currentThread().sleep(200);
            }
            return i;
        }
    }
}
