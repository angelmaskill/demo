package com.netty.test5.ser;

import io.netty.bootstrap.ServerBootstrap;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioServerSocketChannel;  
  
/**
 * Netty中，通讯的双方建立连接后，会把数据按照ByteBuf的方式进行传输，例如http协议中，就是通过HttpRequestDecoder对ByteBuf数据流进行处理，转换成http的对象。基于这个思路，我自定义一种通讯协议：Server和客户端直接传输java对象。
 * @author mayanlu
 *
 */
public class Server {  
    public void start(int port) throws Exception {  
        EventLoopGroup bossGroup = new NioEventLoopGroup();   
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            ServerBootstrap b = new ServerBootstrap();   
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)   
                    .childHandler(new ChannelInitializer<SocketChannel>() {   
                                @Override  
                                public void initChannel(SocketChannel ch) throws Exception {  
                                    ch.pipeline().addLast(new PersonDecoder());  
                                    ch.pipeline().addLast(new BusinessHandler());  
                                }  
                            }).option(ChannelOption.SO_BACKLOG, 128)   
                    .childOption(ChannelOption.SO_KEEPALIVE, true);   
  
            ChannelFuture f = b.bind(port).sync();   
  
            f.channel().closeFuture().sync();  
        } finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        Server server = new Server();  
        server.start(8000);  
    }  
}  