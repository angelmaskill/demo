package com.netty.test13.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * FrameDecoder and ReplayingDecoder
 * allow you to return an object of any type.
 */
class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        System.out.println("...begin decode...");
        if (buffer.readableBytes() < 4) {
            return;
        }
        // 读到,并写入buf
        buffer.readBytes(buffer, buffer.readableBytes());
        int namelength = buffer.readInt();
        String name = new String(buffer.readBytes(namelength).array(), "GBK");
        int age = buffer.readInt();
        double salary = buffer.readDouble();
        Persons person = new Persons(name, age, salary);
        System.out.println("the final msg:" + person.toString());
        out.add(person);
    }
}