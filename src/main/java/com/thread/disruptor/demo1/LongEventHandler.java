package com.thread.disruptor.demo1;

import com.lmax.disruptor.EventHandler;

/**
 * @ClassName LongEventHandler
 * @Description 定义事件消费者
 * @Author yanlu.myl
 * @Date 2021-03-25 21:49
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者:" + event.getValue());
    }
}