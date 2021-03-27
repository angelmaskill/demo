package com.thread.disruptor.demo2;

import com.lmax.disruptor.RingBuffer;

/**
 * @ClassName OrderEventProducer
 * @Description 作为生产者使用。
 * @Author yanlu.myl
 * @Date 2021-03-27 10:04
 */
public class OrderEventProducer {
    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String orderId) {
        long sequence = ringBuffer.next();
        try {
            OrderEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setId(orderId);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}