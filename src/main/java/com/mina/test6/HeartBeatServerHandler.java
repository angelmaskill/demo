package com.mina.test6;

/**
 * @Title: HeartBeatServerHandler.Java
 * @Package com.underdark.March
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Minsc Wang ys2b7_hotmail_com
 * @date 2010-3-14 下午02:50:40
 * @version V0.9.0
 */

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

/**
 * @ClassName: HeartBeatServerHandler
 * @Description: 心跳测试服务器消息处理
 * @author Minsc Wang ys2b7_hotmail_com
 * @date 2011-3-7 下午02:49:14
 *
 */
public class HeartBeatServerHandler extends IoHandlerAdapter {


    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionCreated(session);
        System.out.println("one client sessionOpened" + session.getRemoteAddress());
        SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
        cfg.setReceiveBufferSize(2 * 1024 * 1024);
        cfg.setReadBufferSize(2 * 1024 * 1024);
        cfg.setKeepAlive(true);
        cfg.setSoLinger(0); // 这个是根本解决问题的设置
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        Server.LOG.info("捕获异常");
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        Server.LOG.info("接受到客户端回话:  " + (String) message);
        session.write("received your business msg");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        Server.LOG.info("session被关闭");
        session.close(true);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        Server.LOG.info("session正空闲" + status.toString());
        session.close(true);
    }
}