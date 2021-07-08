package com.thread.testLock.demo1;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.SneakyThrows;

/**
 * <pre>
 * 设计一个可以放 10 个任务的队列 一个生产者往里放任务；一个消费者去队列中取任务
 *      没有任务的时候，是不能消费的；一旦消费了，表示可以通知工人继续生产
 *      队列满的时候，是不能生产的；一旦生产了，就可以通知消费者继续消费
 * </pre>
 *
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/7/8 10:36 下午
 * @Modified By:
 */
public class ConditionTest1 {
    /*队列中中任务的个数*/
    private int taskSize = 10;
    /*队列*/
    private PriorityQueue<Integer> queue = new PriorityQueue<>();
    private ReentrantLock lock = new ReentrantLock();
    /*达到生产信号*/
    private Condition canProduce = lock.newCondition();
    /*达到消费信号*/
    private Condition canConsumer = lock.newCondition();

    @SneakyThrows
    public static void main(String[] args) {
        ConditionTest1 test = new ConditionTest1();
        ExecutorService consumerPool = Executors.newFixedThreadPool(3);
        consumerPool.execute(test.new Comsumer("顾客1", 900l));
        consumerPool.execute(test.new Comsumer("顾客2", 300l));
        consumerPool.execute(test.new Comsumer("顾客3", 700l));

        ExecutorService producerPool = Executors.newFixedThreadPool(5);
        producerPool.execute(test.new Producer("工人1", 900l));
        producerPool.execute(test.new Producer("工人2", 300l));
        producerPool.execute(test.new Producer("工人3", 900l));
        producerPool.execute(test.new Producer("工人4", 100l));
        producerPool.execute(test.new Producer("工人5", 400l));
    }

    class Producer extends Thread {

        private String name;
        private Long speed;

        public Producer(String name, Long speed) {
            this.name = name;
            this.speed = speed;
        }

        @Override
        public void run() {
            produce();
        }

        @SneakyThrows
        void produce() {
            boolean isProducing = true;
            while (isProducing) {
                lock.lock();
                try {
                    /*队列满的时候，是不能生产的；一旦生产了，就可以通知消费者继续消费*/
                    while (queue.size() == taskSize) {
                        System.out.println("队列满了，车间工人请等待。。。");
                        canProduce.await();//生产信号熄灭
                        isProducing = false;//生产者歇歇
                    }
                    queue.offer(1);
                    Thread.sleep(speed);//生产者生产一个任务之后暂定一会再继续
                    canConsumer.signal();
                    System.out.println(
                        this.name + "向队列中插入一个数据,当前 size: " + queue.size() + ",isProducing: "
                        + isProducing);
                    isProducing = true;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Comsumer extends Thread {
        private String name;
        private Long speed;

        public Comsumer(String name, Long speed) {
            this.name = name;
            this.speed = speed;
        }

        @Override
        public void run() {
            comsume();
        }

        @SneakyThrows
        void comsume() {
            boolean isConsuming = true;
            while (isConsuming) {
                lock.lock();
                try {
                    /*没有任务的时候，是不能消费的；一旦消费了，表示可以通知工人继续生产*/
                    while (queue.isEmpty()) {
                        System.out.println("没有任务，不能消费");
                        canConsumer.await();//消费信号熄灭
                        isConsuming = false;//消费者歇歇
                    }
                    queue.poll();//消费一笔数据
                    Thread.sleep(this.speed);//消费者消费一个任务之后暂定一会儿再继续
                    canProduce.signal();//通知工人生产
                    System.out.println(
                        this.name + "从队列中移除一个数据,当前 size: " + queue.size() + ",isConsuming: "
                        + isConsuming);
                    isConsuming = true;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
