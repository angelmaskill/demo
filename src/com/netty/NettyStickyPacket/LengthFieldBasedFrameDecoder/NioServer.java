package com.netty.NettyStickyPacket.LengthFieldBasedFrameDecoder;

import java.net.InetSocketAddress;  
import java.nio.ByteBuffer;  
import java.nio.charset.Charset;  
import java.util.concurrent.Executors;  
  
import org.jboss.netty.bootstrap.ServerBootstrap;  
import org.jboss.netty.buffer.ChannelBuffer;  
import org.jboss.netty.buffer.ChannelBuffers;  
import org.jboss.netty.channel.ChannelHandlerContext;  
import org.jboss.netty.channel.ChannelPipeline;  
import org.jboss.netty.channel.ChannelPipelineFactory;  
import org.jboss.netty.channel.ChannelStateEvent;  
import org.jboss.netty.channel.Channels;  
import org.jboss.netty.channel.MessageEvent;  
import org.jboss.netty.channel.SimpleChannelHandler;  
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;  
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;  
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;  
  
/**
 * NIO为了提高效率，默认情况下可以服务端一次write造成客户端多次read，或者服务商多个write但客户端一次全读取过来。
 * @author mayanlu
 *
 */
public class NioServer {  
    public static void main(String args[]){  
        //Server服务启动器  
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));  
        //设置处理客户端消息和各种消息事件的类(Handler)  
        bootstrap.setPipelineFactory(new ChannelPipelineFactory(){  
            @Override  
            public ChannelPipeline getPipeline() throws Exception {  
                return Channels.pipeline(new LengthFieldPrepender(2),  
                        new LengthFieldBasedFrameDecoder(64*1024,0,2,0,2),  
                        new BusinessHandler());  
            }             
        });  
        //绑定8000端口供客户端访问  
        bootstrap.bind(new InetSocketAddress(8000));  
    }  
      
    private static class BusinessHandler extends SimpleChannelHandler{  
          
        @Override  
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {  
            ChannelBuffer buffer = (ChannelBuffer)e.getMessage();  
            System.out.println("Receive:"+buffer.toString(Charset.defaultCharset()));  
            String msg = buffer.toString(Charset.defaultCharset()) + "has been processed!";  
            ChannelBuffer buffer2 = ChannelBuffers.buffer(msg.length());  
            buffer2.writeBytes(msg.getBytes());  
            e.getChannel().write(buffer2);  
        }  
    }  
}  