package com.netty.NettyStickyPacket.DelimiterBaseFrame.Server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DelimiterDecoderServerHandler extends ChannelHandlerAdapter {
	private int counter;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("服务器端开始接收数据:");
		String body = (String) msg;
		System.out.println("This is " + (++counter) + " receive client msg " + body);
		String sendContent = "receive message is ok:" + "$_";
		ByteBuf byteBuf = Unpooled.copiedBuffer(sendContent.getBytes());
		ctx.writeAndFlush(byteBuf);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}