package com.thread.CyclicBarrier.demo3.model;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用回环栅栏 + 倒数计数器
 *
 * @ClassName Emp
 * @Description 面试者对象
 * @Author yanlu.myl
 * @Date 2021-07-05 14:33
 */
public class Emp implements Runnable {
    private CyclicBarrier barrier;
    private int no;
    private int turn;
    private CountDownLatch countDown;

    public Emp(CyclicBarrier barrier, int no, int turn) {
        this.barrier = barrier;
        this.no = no;
        this.turn = turn;
    }

    public Emp(CyclicBarrier barrier, int no, int turn, CountDownLatch countDown) {
        this.barrier = barrier;
        this.no = no;
        this.turn = turn;
        this.countDown = countDown;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(no + " 进入" + turn + "面，等待。。。");
        // 等所有面试者都到了，才能开始面试
        barrier.await();
        Thread.sleep(1000);
        System.out.println(no + " 号" + turn + "面面试完毕！");
        // 某人面试完毕之后，计数器减1
        if (null != countDown) {
            countDown.countDown();
        }
    }
}