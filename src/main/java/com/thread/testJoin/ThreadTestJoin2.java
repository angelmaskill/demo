/**
 * @(#)ThreadTestJoin2.java Copyright Oristand.All rights reserved. This software is the XXX system.
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


package com.thread.testJoin;

/**
 *
 *
 *      线程的合并的含义就是将几个并行线程的线程合并为一个单线程执行，应用场景是当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
 join为非静态方法，定义如下：
 void join(): 等待该线程终止。
 void join(long millis): 等待该线程终止的时间最长为 millis 毫秒。
 void join(long millis, int nanos): 等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。
 * @author Administrator
 * @since 2014-10-24
 */
public class ThreadTestJoin2 {

    public static void main(String[] args) {
        ThreadA ta = new ThreadA();
        new ThreadB(ta).start();
    }

}

class ThreadA extends Thread {
    public void run() {
        try {
            System.out.println("runing A start^^^^^^^^^^^");
            Thread.currentThread().sleep(2000);
            System.out.println("runing A over^^^^^^^^^^^");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread {

    private ThreadA ta;

    public ThreadB(ThreadA ta) {
        this.ta = ta;
    }

    public void run() {
        try {
            System.out.println("runing B start^^^^^^^^^^^");
            ta.start();
            // 在ThreaB中调用ThreadA的join()，此时的ThreadB将挂起，直到ThreadA执行结束ThreadB才继续执行
            ta.join();
            System.out.println("runing B over^^^^^^^^^^^");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}