package com.mina.test5;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 * 当心跳超时时的处理，也可以用默认处理 这里like
 * KeepAliveRequestTimeoutHandler.LOG的处理
 * <p>
 * KeepAliveFilter(heartBeatFactory,IdleStatus.BOTH_IDLE,heartBeatHandler)
 * 心跳超时处理 KeepAliveFilter 在没有收到心跳消息的响应时，会报告给的KeepAliveRequestTimeoutHandler。
 * 默认的处理是 KeepAliveRequestTimeoutHandler.CLOSE
 * （即如果不给handler参数，则会使用默认的从而Close这个Session）
 *
 * @author cruise
 */

public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

    @Override
    public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
        Server.LOG.info("《*服务器端*》心跳包发送超时处理(及长时间没有发送（接受）心跳包)");
    }

}