package com.netty.NettyStickyPacket.DelimiterBaseFrame.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import com.netty.NettyStickyPacket.DelimiterBaseFrame2.client.TimeClientHandler;

public class DelimiterClientTime {
	private static final int PORT = 8080;
	private static final String IP = "127.0.0.1";

	public static void main(String[] args) throws InterruptedException {
		new DelimiterClientTime().connect(PORT, IP);
	}

	public void connect(int port, String host) throws InterruptedException {
		// 创建时间顺循环线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							// TODO Auto-generated method stub
							// socket通道管道加入行解码器
							ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
							// 传输遍历的数据长度和界定符"#_"
							socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
							// socket通道管道加入字符串解码器
							socketChannel.pipeline().addLast(new StringDecoder());
							// socket通道管道加入客户端发送数据的适配器
							socketChannel.pipeline().addLast(new DelimiterClientHandler());
						}
					});
			// 发起异步连接,连接指定的主机，指定主机ip和port
			ChannelFuture f = bootstrap.connect(host, port).sync();
			// 等待客户端链路关闭
			f.channel().closeFuture().sync();
		} finally {
			// 释放线程组
			group.shutdownGracefully();
		}
	}
}
