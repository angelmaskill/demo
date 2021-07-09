package com.thread.testJoin;

import lombok.SneakyThrows;

/**
 * 要求线程a执行完才开始线程b, 线程b执行完才开始线程
 *
 * @ClassName ThreadTestJoin5
 * @Description 解法2
 * @Author yanlu.myl
 * @Date 2021-07-09 11:43
 */
public class ThreadTestJoin5 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                runSomething();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                runSomething();
            }
        }, "b");
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                runSomething();
            }
        }, "c");

        b.start();
        b.join();

        a.start();
        a.join();

        c.start();
        c.join();
    }

    @SneakyThrows
    private static void runSomething() {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + " is running.." + i);
        }
    }
}
