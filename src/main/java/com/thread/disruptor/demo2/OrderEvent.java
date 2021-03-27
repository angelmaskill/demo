package com.thread.disruptor.demo2;

import lombok.Data;

/**
 * @ClassName OrderEvent
 * @Description 这个类将会被放入环形队列中作为消息内容
 * @Author yanlu.myl
 * @Date 2021-03-27 10:04
 */
@Data
public class OrderEvent {
    private String id;
}