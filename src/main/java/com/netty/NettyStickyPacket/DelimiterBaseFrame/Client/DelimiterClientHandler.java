package com.netty.NettyStickyPacket.DelimiterBaseFrame.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DelimiterClientHandler extends ChannelHandlerAdapter {
	private int counter;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for (int i = 0; i < 100; i++) {
			byte[] req = ("Y T" + "$_").getBytes();
			ctx.writeAndFlush(Unpooled.copiedBuffer(req));
			/**
			 * 以下方法也能发送成功:
			 */
//			message = Unpooled.buffer(req.length);
//			message.writeBytes(req);
//			ctx.writeAndFlush(message);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("this is " + (++counter) + " receive server message" + msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端读取完成");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("客户端异常退出");
	}
}
