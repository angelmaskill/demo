package com.mina.test2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.mina.utils.StackPrint;

public class ServerHandler extends IoHandlerAdapter {

	/**
	 * 接受消息
	 */
	public void messageReceived(IoSession session, Object message) throws Exception {
//		final MessageModel messageModel = (MessageModel) message;
		MyMsg msg = (MyMsg) message;
		System.out.println("服务器端接收到的消息：" + msg);

		// 返回一个消息给客户端
		msg = new MyMsg(10000L, 10001L, "你好，这是来自服务器的应答!");
		session.write(msg);
	}

	/**
	 * 发送消息
	 */
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("服务器端开始发送消息...");
		StackPrint.printStack();
		super.messageSent(session, message);
	}

	/**
	 * 会话开启
	 */
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("服务端会话已打开...");
		super.sessionOpened(session);
	}

	/**
	 * 异常处理
	 */
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("服务端发生异常了...");
		cause.printStackTrace();
	}

	/**
	 * 会话关闭
	 */
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("服务端会话已关闭...");
		super.sessionClosed(session);
	}

	/**
	 * 会话创建
	 */
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("服务端会话已创建...");
		super.sessionCreated(session);
	}

	/**
	 * 连接空闲
	 */
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("服务端连接空闲中...");
		super.sessionIdle(session, status);
	}
}