package com.mina.test2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * <pre>
 *  IoConnector 		这个地方用于与服务端建立连接，每连接一个服务端（每调用一次connect()方法），就启用一个线程，我们不能改变
 *  IoAcceptor  		用于监听客户端的连接,每监听一个端口（每调用一次bind()方法），都启用一个线程，这个数字我们不能改变
 *  IoProcessor 		线程用于IO的处理
 * 	I/O Filter Chain 	在输入和输出的io流中进行过滤
 * 	I/O Handler 		业务逻辑实现
 * 	
 * 	各种线程的产生 :
 * 	当 IoAcceptor/IoConnector  实例创建的时候，同时一个关联在IoAcceptor/IoConnector上的IoProcessor线程池也被创建。
 * 	当IoAcceptor/IoConnector  建立套接字（IoAcceptor 的bind()或者是IoConnector 的connect()方法被调用）时，从线程池中取出一个线程，监听套接字端口。
 * 	当 IoAcceptor/IoConnector  监听到套接字上有连接请求时，建立IoSession 对象，从IoProcessor池中取出一个IoProcessor线程执行IO处理。
 * 	如若过滤器中配置了“threadPool”  过滤器，则使用此线程池建立线程执行业务逻辑（IoHandler）处理，否则使用IoProcessor线程处理业务逻辑。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class ServerMain {

	public static void main(String[] args) throws IOException {

		// 在服务器端创建一个监听连接的接收器 基于tcp/ip
		IoAcceptor acceptor = new NioSocketAcceptor();

		// 绑定的端口
		SocketAddress address = new InetSocketAddress("localhost", 8888);

		// 获取过滤器链
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

		// 添加日志过滤器
		chain.addLast("logger", new LoggingFilter());

		// 配置自定义的编解码器　
		chain.addLast("mycodec", new ProtocolCodecFilter(new MyCoderFactory()));

		// 配置自己的业务线程池,不能保证任务处理的顺序性
		//chain.addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		//  配置自己的业务线程池,能保证任务处理的顺序性
		chain.addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor(500)));

		// 添加数据处理的处理器
		acceptor.setHandler(new ServerHandler());

		// 进行配置信息的设置
		acceptor.getSessionConfig().setReadBufferSize(100);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		/**
		 * 绑定服务器端口,每次监听一个端口,都启动一个线程. 针对一个端口的请求,只有一个线程来处理IO
		 */
		acceptor.bind(address);

		System.out.println("服务器开始在 8888 端口监听.......");

	}
}