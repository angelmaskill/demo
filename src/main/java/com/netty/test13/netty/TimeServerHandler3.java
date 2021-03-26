package com.netty.test13.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 用POJO代替ChannelBuffer
 */

class TimeServerHandler3 extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Persons person = (Persons) msg;
        System.out.println("服务器端开始接收数据:" + person.toString());
        person.setSalary(20000);
        System.out.println("服务端准备发送:" + person.toString());
        ChannelFuture future = ctx.writeAndFlush(person);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Persons person = new Persons("周杰伦123", 31, 10000.44);
        System.out.println("通道已经联通--------");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}