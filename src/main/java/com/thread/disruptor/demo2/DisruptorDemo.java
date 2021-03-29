package com.thread.disruptor.demo2;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName DisruptorDemo
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-03-27 10:05
 */
@Slf4j
public class DisruptorDemo {
    public static void main(String[] args) {
        mulProducer();
    }

    private static void oneProducer() {
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                OrderEvent::new,
                1024 * 1024,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        /*单生产者单消费者模式*/
        //disruptor.handleEventsWith(new OrderEventHandler());
        /*下面传入的两个消费者会重复消费每一条消息*/
        disruptor.handleEventsWith(new OrderEventHandler(), new OrderEventHandler());
        /*一条消息在有多个消费者的情况下，只会被一个消费者消费*/
        disruptor.handleEventsWithWorkerPool(new OrderEventHandler(), new OrderEventHandler());

        disruptor.start();
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer eventProducer = new OrderEventProducer(ringBuffer);
        eventProducer.onData(UUID.randomUUID().toString());
    }

    /*多生产者多消费者*/
    private static void mulProducer() {
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                OrderEvent::new,
                1024 * 1024,
                Executors.defaultThreadFactory(),
                // 这里的枚举修改为多生产者
                ProducerType.MULTI,
                new YieldingWaitStrategy()
        );
        disruptor.handleEventsWithWorkerPool(new OrderEventHandler(), new OrderEventHandler());
        disruptor.start();
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer eventProducer = new OrderEventProducer(ringBuffer);
        // 创建一个线程池，模拟多个生产者
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(() -> eventProducer.onData(UUID.randomUUID().toString()));
        }
    }
}