package com.mina.test6;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

/**
 * 客户端会定时发送心跳请求（注意定时时间必须小于，服务器端的IDLE监控时间）,同时需要监听心跳反馈，
 * 以此来判断是否与服务器丢失连接。对于服务器的心跳请求不给与反馈。
 *
 * @author mayanlu
 */
public class Server {

    private static final int PORT = 9123;
    /** 30秒后超时 */
    private static final int IDELTIMEOUT = 30;
    /** 15秒发送一次心跳包 */
    private static final int HEARTBEATRATE = 15;
    /** 心跳包内容 */
    private static final String HEARTBEATREQUEST = "ALIVE";
    private static final String HEARTBEATRESPONSE = "YES";
    private static final String HEARTBEATRESPONSEmeta = "WAITING! I AM COMMING!";

    protected static final Logger LOG = LoggerFactory.getLogger(Server.class);

    private static SocketAcceptor acceptor;
    private static ExecutorService filterExecutor = new OrderedThreadPoolExecutor();
    private static IoHandler handler = new HeartBeatServerHandler();

    public static void main(String[] args) throws IOException {
        acceptor = new NioSocketAcceptor(Runtime.getRuntime()
                .availableProcessors());
        acceptor.setReuseAddress(true);
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
                IDELTIMEOUT);
        ((SocketSessionConfig) acceptor.getSessionConfig())
                .setReceiveBufferSize(1024);

        acceptor.getFilterChain().addLast("threadPool",
                new ExecutorFilter(filterExecutor));
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory()));

        /** 主角登场 */

        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
        KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
                IdleStatus.BOTH_IDLE, heartBeatHandler);
        /** 是否回发 */
        heartBeat.setForwardEvent(true);
        /** 发送频率 */
        heartBeat.setRequestInterval(HEARTBEATRATE);
        acceptor.getFilterChain().addLast("heartbeat", heartBeat);

        /** *********************** */

        acceptor.setHandler(handler);
        acceptor.bind(new InetSocketAddress(PORT));
        Server.LOG.info("服务器开启，监听端口：" + PORT);
    }

    /***
     * 这里的执行顺序是  分两条顺序
     * getResponse()---->isResponse();获取数据判断心跳事件（目的是判断是否触发心跳超时异常）
     * isRequest()----->getRequest(); 写回数据是心跳事件触发的数据（目的写回给服务器（客户端）心跳包）
     * 两条路线  没必要都实现行
     */
    private static class KeepAliveMessageFactoryImpl implements
            KeepAliveMessageFactory {

        /*
         * (non-Javadoc)
         *
         * 获取客户端的心跳包对象
         */
        @Override
        public Object getRequest(IoSession session) {
            Server.LOG.info("收到客户端的请求心跳包信息: ");
            return HEARTBEATREQUEST;
        }

        /*
         * (non-Javadoc)
         * 服务器构建一个对象,通过编码器返回给客户端
         */
        @Override
        public Object getResponse(IoSession session, Object request) {
            Server.LOG.info("服务器端构建信息,进行回复" + HEARTBEATRESPONSE);
            return HEARTBEATRESPONSE;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         *  在我们的项目中除了心跳包还有业务包
            在这里我们必须有个了断，干嘛？判断客户端发送的是否为心跳包。
         */
        @Override
        public boolean isRequest(IoSession session, Object message) {
            if (message.equals(HEARTBEATREQUEST)) {
                Server.LOG.info("接收到客户端心跳询问事件,心跳数据包是》》" + message);
                return true;
            }
            return false;
        }

        /*
         * (non-Javadoc)
         *
                                 判断是不是服务器给返回的心跳包.
         */
        @Override
        public boolean isResponse(IoSession session, Object message) {
            if (message.equals(HEARTBEATRESPONSE)) {
                Server.LOG.info("服务器发送数据包中引发心跳事件: " + message);
                return true;
            }
            return false;
        }
    }

    /***
     * @Description: 当心跳超时时的处理，也可以用默认处理 这里like
     *               KeepAliveRequestTimeoutHandler.LOG的处理
     *
     */
    private static class KeepAliveRequestTimeoutHandlerImpl implements
            KeepAliveRequestTimeoutHandler {

        /*
         * (non-Javadoc)
         *
         * @seeorg.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#
         * keepAliveRequestTimedOut
         * (org.apache.mina.filter.keepalive.KeepAliveFilter,
         * org.apache.mina.core.session.IoSession)
         */
        @Override
        public void keepAliveRequestTimedOut(KeepAliveFilter filter,
                                             IoSession session) throws Exception {
            Server.LOG.info("《*服务器端*》心跳包发送超时处理(及长时间没有发送（接受）心跳包)");
        }

    }
}