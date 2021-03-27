package com.thread.disruptor.demo1.event;

import com.lmax.disruptor.EventFactory;

/**
 * @ClassName LongEventFactory
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-03-25 21:49
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
