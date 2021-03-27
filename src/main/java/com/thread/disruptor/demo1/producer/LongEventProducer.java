package com.thread.disruptor.demo1.producer;

import com.lmax.disruptor.RingBuffer;
import com.thread.disruptor.demo1.event.LongEvent;

/**
 * @ClassName LongEventProducer
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-03-25 21:50
 */
//定义生产者
public class LongEventProducer {
    /*核心类:环形数组*/
    public final RingBuffer<LongEvent> ringBuffer;

    /*通过构造方法初始化环形数组这个核心类*/
    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 生产事件的方法
     * 往环形数组里方数据
     * @param data
     */
    public void onData(Long data) {
        // 1.ringBuffer 事件队列 下一个空的位置
        long sequence = ringBuffer.next();
        try {
            //2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            //3.设置事件中封装的数据
            longEvent.setValue(data);
        } finally {
            System.out.println("生产者准备发送数据: "+ data);
            //4.发布事件
            ringBuffer.publish(sequence);
        }
    }
}