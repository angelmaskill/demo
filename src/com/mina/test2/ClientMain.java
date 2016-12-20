package com.mina.test2;

import java.net.InetSocketAddress;  
import java.net.SocketAddress;  
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;  
import org.apache.mina.core.future.ConnectFuture;  
import org.apache.mina.filter.codec.ProtocolCodecFilter;  
import org.apache.mina.filter.logging.LoggingFilter;  
import org.apache.mina.transport.socket.nio.NioSocketConnector;  
  
public class ClientMain {  
    public static void main(String[] args) {  
          
        //  创建客户端连接器 基于tcp/ip  
        NioSocketConnector connector = new NioSocketConnector();  
          
        //  连接的地址和端口  
        SocketAddress address = new InetSocketAddress("localhost",8888);  
  
        //  获取过滤器链  
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();  
      
        //  配置日志过滤器和自定义编解码器  
        chain.addLast("logger", new LoggingFilter());  
        chain.addLast("mycodec",new ProtocolCodecFilter(new MyCoderFactory()));  
      
        //  添加处理器  
        connector.setHandler(new ClientHandler());  
          
        //　连接到服务器　  
        ConnectFuture future = connector.connect(address);  
      
        //  等待连接创建完成  
        future.awaitUninterruptibly();  
          
        //  会话创建后发送消息到服务器  
        MyMsg msg = new MyMsg(10001L, 10000L, "你好，这是来自客户端的请求!");  
        future.getSession().write(msg);  
      
        //  等待28000毫秒后连接断开  
        future.getSession().getCloseFuture().awaitUninterruptibly(28000);  
  
        //  关闭连接  
        connector.dispose();  
      
    }  
}  