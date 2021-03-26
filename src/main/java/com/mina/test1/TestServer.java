package com.mina.test1;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class TestServer {
    public static void main(String[] args) throws IOException {
        // 创建Acceptor
        IoAcceptor acceptor = new NioSocketAcceptor();

        // 注册filter
        /**
         * filter事实上主要是处理底层的通信字节流，通信协议等，一般跟业务逻辑没什么关系。
         * Handler是专门暴露给应用开发者，用来填写业务处理代码的。
         */
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        // 注册你的业务处理类
        acceptor.setHandler(new TimeServerHandler());

        // 配置参数
        acceptor.getSessionConfig().setReadBufferSize(2048);
        // 这里没有保活机制,socket会断开连接
        //acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        // 让Acceptor在绑定的地址侦听
        acceptor.bind(new InetSocketAddress(8888));
    }
}
