package com.java8;

public class ThreadLam {
    public static void main(String[] args) {
        // Lambda Runnable
        Runnable task2 = () -> {
            System.out.println("ID:" + Thread.currentThread().getId() + ",NAME:" + Thread.currentThread().getName()
                    + " is running");
        };

        // start the thread
        new Thread(task2).start();

        Thread thread2 = new Thread(() -> {
            System.out.println("ID:" + Thread.currentThread().getId() + ",NAME:" + Thread.currentThread().getName() + "匿名启动.");
        }, "thread2");
        thread2.start();

        new Thread(() -> {
            System.out.println("ID:" + Thread.currentThread().getId() + ",NAME:" + Thread.currentThread().getName() + "匿名启动.");
        }, "thread3").start();

        Object obj = new Thread(new Runnable() {
            public void run() {
                System.out.println("我是一个线程");
            }
        });
        System.out.println("obj对象的类名:" + obj.getClass().getName());
    }
}
