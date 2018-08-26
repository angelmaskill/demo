/**
 * @(#)ServerDemoMul2.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
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

package com.socket.demo5MulThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class description goes here.
    在实际的网络环境里，同一时间只对一个用户服务是不可行的。
    一个优秀的网络服务程序除了能处理用户的输入信息，还必须能够同时响应多个客户端的连接请求。
    在java中，实现以上功能特点是非常容易的。
　　
    设计原理：
　　 主程序监听一端口，等待客户接入；同时构造一个线程类，准备接管会话。
    当一个Socket会话产生后，将这个会话交给线程处理，然后主程序继续监听。
    运用Thread类或Runnable接口来实现是不错的办法。

 * @author Administrator
 * @since 2014-11-6
 */
public class ServerDemoMul2 extends ServerSocket {
    private static final int SERVER_PORT = 1028;

    public ServerDemoMul2() throws IOException {
        super(SERVER_PORT);

        try {
            while (true) {
                Socket socket = accept();
                
                new CreateServerThread(socket);
            }
        } catch (IOException e) {
        } finally {
            close();
        }
    }

    // --- CreateServerThread
    class CreateServerThread extends Thread {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;

        public CreateServerThread(Socket s) throws IOException {
            client = s;
            in = new BufferedReader(new InputStreamReader(client.getInputStream(), "GB2312"));
            out = new PrintWriter(client.getOutputStream(), true);
            start();
        }

        public void run() {
            try {
                System.out.println("当前线程id:"+this.currentThread().getId());
                String line = in.readLine();
                while (!line.equals("bye")) {
                    System.out.println("服务器端接收到信息："+line);
                    String msg = createMessage(line);
                    out.println(msg);//服务器端发送给客户端的信息
                    line = in.readLine();
                }
                out.println("--- See you, bye! ---");
                client.close();
            } catch (IOException e) {
               System.out.println("io异常");
            }
        }

        private String createMessage(String line)
        {
            return line;
        }
    }

    public static void main(String[] args) throws IOException {
        new ServerDemoMul2();
    }
}
