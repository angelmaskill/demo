package com.mina.test8.ser;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * 以被动型心跳机制为例，服务器在接受到客户端连接以后被动接受心跳请求，当在规定时间内没有收到客户端心跳请求时 将客户端连接关闭
 *
 * @author mayanlu
 */
public class Server {

    private static final int PORT = 9123;
    /** 30秒后超时 */
    private static final int IDELTIMEOUT = 30;
    /** 3秒发送一次心跳包 */
    private static final int HEARTBEATRATE = 15;

    protected static final Logger LOG = LoggerFactory.getLogger(Server.class);

    /**
     * 虽然设置了setIdleTime 空闲断开,但是心跳保活机制,使得已经建立的socket连接不会关闭
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDELTIMEOUT);

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));

        KeepAliveMessageFactory heartBeatFactory = new MyKeepAliveMessageFactory();
        /**
         * 实例化一个 KeepAliveFilter 过滤器，传入
         *  KeepAliveMessageFactory引用，
         * IdleStatus参数为 BOTH_IDLE,
         * 及表明如果当前连接的读写通道都空闲的时候在指定的时间间隔getRequestInterval后发送出发Idle事件。
         */
        KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE, heartBeatHandler);

        // KeepAliveFilter heartBeat = new
        // KeepAliveFilter(heartBeatFactory,IdleStatus.BOTH_IDLE);

        // idle事件回发 当session进入idle状态的时候 依然调用handler中的idled方法
        heartBeat.setForwardEvent(true);
        /**
         * 说明：设置心跳包请求时间间隔，其实对于被动型的心跳机制来说，设置心跳包请求间隔貌似是没有用的，因为它是不会发送心跳包的，但是它会触发 sessionIdle事件，
         * 我们利用该方法，可以来判断客户端是否在该时间间隔内没有发心跳包，一旦 sessionIdle方法被调用，则认为 客户端丢失连接并将其踢出 。
         * 因此其中参数 heartPeriod其实就是服务器对于客户端的IDLE监控时间。
         */
        // 本服务器为被动心跳 即需要每3秒接受一个心跳请求 否则该连接进入空闲状态 并且发出idled方法回调
        heartBeat.setRequestInterval(HEARTBEATRATE);

        acceptor.getFilterChain().addLast("heartbeat", heartBeat);

        acceptor.setHandler(new MyHandler());
        acceptor.bind(new InetSocketAddress(PORT));
        Server.LOG.info("Server started on port： " + PORT);
    }
}