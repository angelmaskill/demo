package com.netty.test13.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

class TimeEncoder extends MessageToByteEncoder<Persons> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Persons msg, ByteBuf out) throws Exception {
        System.out.println("...begin encode...");
        System.out.println("the orgin msg:" + msg.toString());
        Persons person = msg;
        out.writeInt(person.getName().getBytes("GBK").length);
        out.writeBytes(person.getName().getBytes("GBK"));
        out.writeInt(person.getAge());
        out.writeDouble(person.getSalary());
    }

}