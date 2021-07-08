package com.thread.CyclicBarrier.demo2;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * 举个报旅行团旅行的例子:
 *      出发时，导游会在机场收了护照和签证，办理集体出境手续，所以，要等大家都到齐才能出发，出发前再把护照和签证发到大家手里。
 *      对应CyclicBarrier使用。
 *      每个人到达后进入barrier状态。
 *          都到达后，唤起大家一起出发去旅行。
 *          旅行出发前，导游还会有个发护照和签证的动作。
 * </pre>
 *
 * <pre>
 * public CyclicBarrier(int parties, Runnable barrierAction)
 *      第一个参数，表示那个一起执行的线程个数。
 *      第二个参数，表示线程都处于barrier时，一起执行之前，先执行的一个线程。
 * </pre>
 *
 * @ClassName CyclicBarrierTest2
 * @Description 回环栅栏练习
 * @Author yanlu.myl
 * @Date 2021-07-05 13:31
 */
public class CyclicBarrierTest2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Guide());
        service.execute(new Thread(new Task(cyclicBarrier, 1)));
        service.execute(new Thread(new Task(cyclicBarrier, 2)));
        service.execute(new Thread(new Task(cyclicBarrier, 3)));
        service.shutdown();
    }
}

class Task implements Runnable {
    private CyclicBarrier barrier;
    private int no;

    public Task(CyclicBarrier barrier, int no) {
        this.barrier = barrier;
        this.no = no;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(no + " 进入等待。。。");
        barrier.await();
        System.out.println(no + " 准备出发。。。");
    }
}

class Guide implements Runnable {
    @Override
    public void run() {
        System.out.println("导游开始给大家发放签证。。。");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
