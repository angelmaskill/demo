package com.mina.test4;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class SocketServer {
	private static final int PORT = 2023; // 链接端口号
	private static IoAcceptor acceptor;

	public static void startMinaServer() {
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());// 日志过滤
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CoderFactory()));// 消息协议过滤
		acceptor.getFilterChain().addLast("executor", new ExecutorFilter(5, 800));//
		acceptor.setHandler(new ServerHandler());// 自己处理的业务
		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));// 端口
		acceptor.getSessionConfig().setReadBufferSize(2048);// 服务端读缓存大小
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 12);// 客户端和服务器心跳间隔时间（单位：秒）
		try {
			acceptor.bind();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}