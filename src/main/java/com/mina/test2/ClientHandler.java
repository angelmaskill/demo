package com.mina.test2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

    /**
     * 接收消息
     */
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        System.out.println("客户端接收到的消息：" + (MyMsg) message);
    }

    /**
     * 发送消息
     */
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("客户端发送消息...");
        super.messageSent(session, message);

    }

    /**
     * 会话创建
     */
    public void sessionOpened(IoSession session) throws Exception {

        System.out.println("客户端已经连接到了服务器...");

    }

    /**
     * 会话关闭
     */
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("连接关闭...");
        super.sessionClosed(session);
    }

}  