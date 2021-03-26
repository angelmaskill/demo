/**
 * @(#)ServerDemoMul.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-11-6     Administrator    Created
 **********************************************
 */

package com.socket.demo4MulThread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class description goes here.
 * 程序8.15为服务器程序，该程序接受客户端的连接请求后，为每一个连接创建一个新的线程，向客户端发送数据。
 * @author Administrator
 * @since 2014-11-6
 */
public class ServerDemoMul {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Server game = new Server(); // 创建服务器线程
        game.setDaemon(true); // 设置为守护线程
        game.start(); // 启动服务器线程
        char c;
        // 主线程等待键盘输入，如按x退出
        do {
            c = (char) System.in.read();
        } while (c != 'x');
        game.stop();
        game.server.close();
    }
}

// 服务器类
class Server extends Thread {
    ServerSocket server;

    public Server() throws IOException {
        // 创建服务器 port = 1028
        server = new ServerSocket(1028);
        System.out.println("Server created.");
        System.out.println("waiting for client to connect on...");

    }

    // 服务器线程体
    public void run() {
        while (true) {
            try {
                // 等待连接
                Socket socket = server.accept();
                System.out.println("服务器联通客户端:" + socket.toString());
                // 创建一个新的线程为新连接服务
                Service serv = new Service(socket);
                serv.start();
            } catch (Exception e) {
                System.out.println("Exception:" + e.getMessage());
            }
        }
    }

    // 为一个连接服务的线程类
    class Service extends Thread {
        Socket socket;

        public Service(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            DataOutputStream out;
            BufferedReader in = null;
            try {
                out = new DataOutputStream(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//从客户端输入
                try {
                    System.out.println("服务器端进行业务处理开始...");
                    String str = in.readLine(); // 接收客户端发送的内容
                    System.out.println("服务器收到信息：" + str);
                    /*for (int i = 0; i < 10; i++) {
                        out.writeBytes(i+str + "\n");
                    }*/
                    out.writeBytes("i love you!" + str);
                } catch (Exception e) {
                    System.out.println("Exception:" + e.getMessage());
                }
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Exception:" + e.getMessage());
            }
        }
    }
}
