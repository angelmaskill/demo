package com.netty.test5.cli;

import com.netty.test5.ser.PersonDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    /**
                     * <pre>
                     * 注意:想获取从服务端过来的信息,必须添加解码器.
                     * 编码器/解码器谁先谁后没关系.
                     * </pre>
                     */
                    ch.pipeline().addLast(new PersonEncoder());
                    ch.pipeline().addLast(new PersonDecoder());
                    ch.pipeline().addLast(new ClientInitHandler());
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connect("127.0.0.1", 8000);
    }
}