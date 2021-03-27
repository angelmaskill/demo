package com.thread.disruptor.demo1;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.thread.disruptor.demo1.consumer.LongEventHandler;
import com.thread.disruptor.demo1.event.LongEvent;
import com.thread.disruptor.demo1.event.LongEventFactory;
import com.thread.disruptor.demo1.producer.LongEventProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName DisruptorMain
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-03-25 21:50
 */
public class DisruptorMain {
    public static void main(String[] args) {
        // 1.创建一个可缓存的线程 提供线程来出发Consumer 的事件处理
        ThreadFactory executor = Executors.defaultThreadFactory();
        // 2.创建工厂来生产事件
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        // 3.创建ringBuffer 大小,一定是2的N次方
        int ringBufferSize = 1024 * 1024; // ringBufferSize大小一定要是2的N次方
        // 4.创建Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,
                                                        ringBufferSize,         //环形数组大小
                                                        executor,               //消费者消费的线程工厂
                                                        ProducerType.SINGLE,    //表示是单个生产者还是多个生产者
                                                        new YieldingWaitStrategy());
        // 5.连接消费端方法
        disruptor.handleEventsWith(new LongEventHandler());
        // 6.启动
        disruptor.start();
        //--------------------消费者已经启动了------------------------------
        // 7.创建RingBuffer容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        // 9.指定缓冲区大小
        for (int i = 1; i <= 10000; i++) {
            producer.onData(Long.valueOf(i));
        }
        //10.关闭disruptor和executor
        disruptor.shutdown();
    }
}