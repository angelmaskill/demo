package com.netty.test13.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

class TimeClientHandler3 extends ChannelHandlerAdapter {

    private Persons person;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已经连上服务端,准备给服务端发送消息...");
        person = new Persons("myl", 17, 10000);
        // 连接建立，向服务端发送数据
        ctx.write(person);
        // 注意：需要调用flush将数据发送到服务端
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Persons person = (Persons) msg;
        System.out.println("客户端收到信息:" + person.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端读取完成");
        ctx.flush();
    }
}