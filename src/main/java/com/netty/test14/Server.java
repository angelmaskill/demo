package com.netty.test14;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.Charset;

public class Server {
    public static void main(String[] args) {
        new Server().testServer();
    }

    public void testServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();// 接受连接事件组
        EventLoopGroup workerGroup = new NioEventLoopGroup();// 处理每个连接业务事件组
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);// 可以只使用一个group，分成两个group的好处是：IO耗时较长导致阻塞时，不会对接受连接造成影响。
            b.channel(NioServerSocketChannel.class);// 设置通道类型
            b.option(ChannelOption.TCP_NODELAY, true);

            b.childHandler(new ChannelInitializer<SocketChannel>() {
                // 实现ChannelInitializer接口的initChannel方法，将处理器（业务逻辑）加到通道管道的末尾：
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new MainMessageDecoder(Charset.forName("utf-8")));
                    pipeline.addLast(new MainMessageEncoder(Charset.forName("utf-8")));
                    pipeline.addLast(new TimeServerHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口
            ChannelFuture f = b.bind(9999).sync();
            // 等待结束
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}