package com.mina.test2;

import java.io.IOException;  
import java.net.InetSocketAddress;  
import java.net.SocketAddress;  
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;  
import org.apache.mina.core.service.IoAcceptor;  
import org.apache.mina.core.session.IdleStatus;  
import org.apache.mina.filter.codec.ProtocolCodecFilter;  
import org.apache.mina.filter.logging.LoggingFilter;  
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;  
  
public class ServerMain {  
      
    public static void main(String[] args) throws IOException {  
          
        //  在服务器端创建一个监听连接的接收器 基于tcp/ip  
        IoAcceptor acceptor = new NioSocketAcceptor();  
          
        //  绑定的端口  
        SocketAddress address = new InetSocketAddress("localhost", 8888);  
          
        //   获取过滤器链  
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();  
          
        //  添加日志过滤器  
        chain.addLast("logger", new LoggingFilter());  
          
        //　配置自定义的编解码器　  
        chain.addLast("mycodec", new ProtocolCodecFilter(new MyCoderFactory()));  
          
        //  添加数据处理的处理器  
        acceptor.setHandler(new ServerHandler());  
          
        //  进行配置信息的设置       
        acceptor.getSessionConfig().setReadBufferSize(100);       
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);   
          
        //  绑定服务器端口　  
        acceptor.bind(address);  
          
        System.out.println("服务器开始在 8888 端口监听.......");  
          
    }  
}  