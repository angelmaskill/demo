package com.netty.NettyStickyPacket.LengthFieldBasedFrameDecoder;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

public class NioClient {
    public static void main(String args[]) {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new LengthFieldPrepender(2), new LengthFieldBasedFrameDecoder(64 * 1024, 0, 2,
                        0, 2), new RequestHandler());
            }
        });
        // 连接到本地的8000端口的服务端
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (future.isSuccess()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Request001");
            for (int i = 0; i < 100; i++) {
                builder.append("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }

            ChannelBuffer buffer = ChannelBuffers.buffer(builder.toString().length());
            buffer.writeBytes(builder.toString().getBytes());
            future.getChannel().write(buffer);
        }
    }

    private static class RequestHandler extends SimpleChannelHandler {

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            System.out.println(buffer.toString(Charset.defaultCharset()));
        }
    }
}