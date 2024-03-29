package com.mina.test8.ser;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class MyHandler extends IoHandlerAdapter {
    //private final int IDLE = 3000;//(单位s)
    private final Logger LOG = Logger.getLogger(MyHandler.class);
    // public static Set<IoSession> sessions = Collections.synchronizedSet(new HashSet<IoSession>());
    public static ConcurrentHashMap<Long, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<Long, IoSession>();

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.close(true);
        LOG.warn("session occured exception, so close it." + cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        LOG.warn("客户端" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress() + "连接成功！");
        session.setAttribute("type", message);
        String remoteAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        session.setAttribute("ip", remoteAddress);
        LOG.warn("服务器收到的消息是：" + str);
        session.write("welcome by he");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        LOG.warn("messageSent:" + message);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        LOG.warn("remote client [" + session.getRemoteAddress().toString() + "] connected.");
        // my
        Long time = System.currentTimeMillis();
        session.setAttribute("id", time);
        sessionsConcurrentHashMap.put(time, session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOG.warn("sessionClosed.");
        session.close(true);
        // my
        sessionsConcurrentHashMap.remove(session.getAttribute("id"));
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        LOG.warn("session idle, so disconnecting......");
        session.close(true);
        LOG.warn("disconnected.");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        LOG.warn("sessionOpened.");
        //  
        //session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDLE);
    }
}