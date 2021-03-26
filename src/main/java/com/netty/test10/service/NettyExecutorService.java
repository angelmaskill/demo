package com.netty.test10.service;

import com.netty.test10.netty.NettyServer;
import org.springframework.stereotype.Service;

@Service
public class NettyExecutorService {

    public NettyExecutorService() {

        // 为了颜色一致，我们用管理Err输出
        System.err.println("---------- Spring自动加载         ---------- ");
        System.err.println("---------- 启动Netty线程池       ---------- ");

        /**
         * <pre>
         * 说明
         * 如果此处不用线程池，直接用server.run()启动【直接调用方法而已】
         * 那么你会看到tomcat启动过程中，在启动了Netty后就会一直等待连接
         * </pre>
         */
        NettyServer server = new NettyServer();
        server.run();
        // 线程池
        //ExecutorService es = Executors.newCachedThreadPool();
        // 启动线程池
        //es.execute(server);

    }

}
