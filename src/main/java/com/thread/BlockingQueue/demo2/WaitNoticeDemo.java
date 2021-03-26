package com.thread.BlockingQueue.demo2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * User: myl
 * Date: 4/24/17
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 * <p>
 * 生产者与消费者模型中，要保证以下几点：
 * 1 同一时间内只能有一个生产者生产
 * 2 同一时间内只能有一个消费者消费
 * 3 生产者生产的同时消费者不能消费
 * 4 消费者消费的同时生产者不能生产
 * 5 共享空间空时消费者不能继续消费
 * 6 共享空间满时生产者不能继续生产
 * <p>
 * 使用并发库中的BlockingQueue(阻塞队列） 实现生产者与消费者
 */
public class WaitNoticeDemo {
    public static void main(String[] args) {
        // 固定容器大小为10
        BlockingQueue<Food> foods = new LinkedBlockingQueue<Food>(10);
        Thread produce = new Thread(new Produce(foods));
        Thread consume = new Thread(new Consume(foods));
        produce.start();
        consume.start();
    }
}

/**
 * 生产者
 */
class Produce implements Runnable {
    private BlockingQueue<Food> foods;

    Produce(BlockingQueue<Food> foods) {
        this.foods = foods;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                // 当生产的食品数量装满了容器，那么在while里面该食品容器(阻塞队列)会自动阻塞 wait状态 等待消费
                foods.put(new Food("食品" + i));
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
            }
        }
    }
}

/**
 * 消费者
 */
class Consume implements Runnable {
    private BlockingQueue<Food> foods;

    Consume(BlockingQueue<Food> foods) {
        this.foods = foods;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); // 用于测试当生产者生产满10个食品后是否进入等待状态
            while (true) {
                // 当容器里面的食品数量为空时，那么在while里面该食品容器(阻塞队列)会自动阻塞 wait状态 等待生产
                Food food = foods.take();
                System.out.println("消费" + food.getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // To change body of catch statement use File |
            // Settings | File Templates.
        }
    }
}

/**
 * 食品
 */
class Food {
    private String name;

    String getName() {
        return name;
    }

    Food(String name) {
        this.name = name;
        System.out.println("生产" + name);
    }
}