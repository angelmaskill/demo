package com.netty.test13.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.netty.test1.HelloClientIntHandler;

public class Client {
	public static void main(String[] args) {
		new Client().testClient();
	}

	public void testClient() {

		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();

			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			// 优化:设置连接的时候的超时时间.超过3秒就不再尝试建立连接.
			b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeEncoder());
					ch.pipeline().addLast(new TimeDecoder());
					ch.pipeline().addLast(new TimeClientHandler3());
				}
			}).option(ChannelOption.SO_KEEPALIVE, true);

			// Start the client.
			ChannelFuture f;
			// 连接
			f = b.connect("localhost", 9999).sync();
			// 这一句不写无法收到服务端返还的信息.
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}

	}
}
