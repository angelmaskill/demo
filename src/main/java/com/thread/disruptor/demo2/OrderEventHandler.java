package com.thread.disruptor.demo2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName OrderEventHandler
 * @Description 创建 OrderEventHandler 类，并实现 EventHandler<T> 和 WorkHandler<T> 接口，作为消费者
 * @Author yanlu.myl
 * @Date 2021-03-27 10:04
 */
@Slf4j
public class OrderEventHandler implements EventHandler<OrderEvent>, WorkHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) {
        log.info("event: {}, sequence: {}, endOfBatch: {}", event, sequence, endOfBatch);
    }
    @Override
    public void onEvent(OrderEvent event) {
        log.info("event: {}", event);
    }
}