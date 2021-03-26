package com.netty.test8.server;

import com.netty.test8.server.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @author myl
 * @since 16/6/28
 */
public class NettyServerBootstrap {

    private int port;

    private SocketChannel socketChannel;

    public NettyServerBootstrap(int port) {
        this.port = port;
        bind();
    }

    private void bind() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work);
        bootstrap.channel(NioServerSocketChannel.class);

        /**
         * 调优:高水位/低水位.
         *
         * <pre>
         * 概念:
         * http://stackoverflow.com/questions/25281124/netty-4-high-and-low-write-watermarks
         *
         * For instance, imagine you have a queue of tasks on server side that is filled by clients and processed by backend. In case clients send
         * tasks too quick the length of the queue grows.
         * One needs to introduce so named high watermark and low watermark. If queue length is greater than high watermark stop reading from
         * sockets and queue length will decrease.
         * When queue length becomes less than low watermark start reading tasks from sockets again.
         * Note, to make it possible for clients to adapt to speed you process tasks (actually to adapt window size) one shouldn't make a big gap
         * between high and low watermarks.
         * From the other side small gap means you'll be too often add/remove sockets from the event loop.
         * </pre>
         *
         * <pre>
         * http://xw-z1985.iteye.com/blog/1970844
         *
         * 可以借鉴的几个点：
         * 1、轻量级对象池的使用
         * 2、buffer数组的循环存储
         * 3、ChannelWritabilityChanged事件的触发
         * 4、AtomicLongFieldUpdater的使用
         *
         *
         * Netty的写操作由两个步骤组成：
         * Write:将msg存储到ChannelOutboundBuffer中
         * Flush：将msg从ChannelOutboundBuffer中flush到套接字的发送缓冲区中。
         *
         * </pre>
         *
         * <pre>
         *
         * 为什么说这个地方是坑呢，因为大部分时候我们往一个channel写数据会判断channel是否active，但是往往忽略了这种慢的情况。
         *
         * 那这个问题怎么解决呢？其实ChannelOutboundBuffer虽然无界，但是可以给它配置一个高水位线和低水位线，
         * 当buffer的大小超过高水位线的时候对应channel的isWritable就会变成false，
         * 当buffer的大小低于低水位线的时候，isWritable就会变成true。
         * 所以应用应该判断isWritable，如果是false就不要再写数据了。
         * 高水位线和低水位线是字节数，默认高水位是64K，低水位是32K，我们可以根据我们的应用需要支持多少连接数和系统资源进行合理规划。
         * </pre>
         *
         */
        bootstrap.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 64 * 1024);
        bootstrap.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 32 * 1024);

        // 调优:输入连接指示（对连接的请求）的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接。
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        // 通过noDelay 禁用Nagle,使消息立即发出去，不用等待到一定的数据量发出去
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        // 保存长连接状态
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ObjectEncoder());
                pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                pipeline.addLast(new NettyServerHandler());

            }
        });
        ChannelFuture future = null;
        try {
            future = bootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (future.isSuccess()) {
            System.out.println("server start ------------->†");
        }

    }

    public static void main(String[] args) throws Exception {
        NettyServerBootstrap bootstrap = new NettyServerBootstrap(9999);
        while (true) {
            TimeUnit.SECONDS.sleep(15);
        }
    }
}
