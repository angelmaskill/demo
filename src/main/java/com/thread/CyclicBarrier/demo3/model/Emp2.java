package com.thread.CyclicBarrier.demo3.model;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计执行成功的任务数
 *
 * @ClassName Emp
 * @Description 面试者对象
 * @Author yanlu.myl
 * @Date 2021-07-05 14:33
 */
public class Emp2 implements Runnable {
    private volatile AtomicInteger sum_success;
    private CyclicBarrier barrier;
    private int no;
    private int turn;

    public Emp2(AtomicInteger sum_success, CyclicBarrier barrier, int no, int turn) {
        this.sum_success = sum_success;
        this.barrier = barrier;
        this.no = no;
        this.turn = turn;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(no + " 进入" + turn + "面，等待。。。");
        // 等所有面试者都到了，才能开始面试
        Thread.sleep(1000);
        System.out.println(no + " 号" + turn + "面面试完毕！");
        sum_success.incrementAndGet();
    }
}