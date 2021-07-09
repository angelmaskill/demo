package com.thread.testJoin;

import lombok.SneakyThrows;

/**
 * 要求线程a执行完才开始线程b, 线程b执行完才开始线程
 *
 * @ClassName ThreadTestJoin4
 * @Description 解法1
 * @Author yanlu.myl
 * @Date 2021-07-09 11:10
 */
public class ThreadTestJoin4 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                runSomething();
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                a.join();
                runSomething();
            }
        }, "b");
        /* 线程a 和线程b交换位置，也不影响两者执行顺序 */
        b.start();
        a.start();

        b.join();
        System.out.println("主线程开始执行");
    }

    @SneakyThrows
    private static void runSomething() {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + " is running.." + i);
        }
    }
}
