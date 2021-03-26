package com.mina.test4;

import com.google.gson.Gson;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler implements IoHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable throwable) throws Exception {
        LOGGER.info("exceptionCaught" + throwable.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        LOGGER.info("messageReceived");
        Header header = (Header) message;
        LOGGER.info("收到的消息请求" + new Gson().toJson(message));
        new Execute(session, header).handler();// 根据消息类型，处理不同的操作
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        LOGGER.info("messageSent");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOGGER.info("sessionClosed");
        LoginBean bean = (LoginBean) session.getAttribute("USERNAME");
        LOGGER.info(bean.getUsername() + "退出登录");
        if (null != bean.getUsername()) {
            SessionMap.unregisterSession(bean.getId());
        }
        session.close(true);
        LOGGER.info(session + "关闭");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        LOGGER.debug("sessionCreated");
        SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
        cfg.setReceiveBufferSize(2 * 1024 * 1024);
        cfg.setReadBufferSize(2 * 1024 * 1024);
        cfg.setKeepAlive(true);// 是否保持长连接
        cfg.setSoLinger(0);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus idleStatus) throws Exception {
        LOGGER.info("sessionIdle");
        LoginBean bean = (LoginBean) session.getAttribute("USERNAME");
        if (null != bean) {
            LOGGER.info(bean.getUsername() + "退出登录");
            if (null != bean.getUsername()) {
                SessionMap.unregisterSession(bean.getId());
            }
        }
        session.close(true);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        LOGGER.info("sessionOpened");
    }
}