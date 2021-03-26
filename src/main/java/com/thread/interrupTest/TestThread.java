package com.thread.interrupTest;

public class TestThread implements Runnable {

    boolean stop = false;

    public static void main(String[] args) throws Exception {
        /***
         * 正在运行中的线程,收到中断信号,并不会立刻响应中断
         */
        Thread thread = new Thread(new TestThread(), "My Thread");
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("线程是否中断：" + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Stopping application...");

    }

    public void run() {
        while (!stop) {
            System.out.println("My Thread is running...");
            // 让该循环持续一段时间，使上面的话打印次数少点 
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
        }
        System.out.println("My Thread exiting under request...");
    }


} 