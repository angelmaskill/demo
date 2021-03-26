/**
 * @(#)WebServerTest.java Copyright Oristand.All rights reserved. This software is the XXX system.
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

package com.socket.demo3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class description goes here.
 程序8.12是一个简单的服务器程序，监听80端口，当接收到连接请求后，
 读取客户端发送的命令，然后发送文本。运行该程序，在IE的地址栏中输入http://localhost/，
 可以看到图8.6所示的画面，刷新5次后，程序结束运行。
 * @author Administrator
 * @since 2014-11-6
 *
 */
public class WebServerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int connects = 0;
        try {
            // 建立一个服务器socket
            serverSocket = new ServerSocket(80);
            while (connects < 5) {//连接80端口的次数
                // 等待连接
                clientSocket = serverSocket.accept();
                // 服务连接
                ServiceClient(clientSocket, connects);
                connects++;
                System.out.println("===" + connects);
            }
            serverSocket.close();
        } catch (IOException ioe) {
            System.out.println("Error in SimpleWebServer: " + ioe);
        }
    }

    public static void ServiceClient(Socket client, int connects) throws IOException {
        DataInputStream inStream = null;
        DataOutputStream outStream = null;
        try {
            /*
             * 输入流：从客户端获取的信息
             */
            inStream = new DataInputStream(client.getInputStream());
            /*
             * 输出流：发送给客户端的信息
             */
            outStream = new DataOutputStream(client.getOutputStream());
            String buffer = "this is your " + connects + " time to access！";
            String inputLine;
            while ((inputLine = inStream.readLine()) != null) {
                // 读取客户端的HTTP请求后发送响应
                if (inputLine.equals("")) {
                    outStream.writeBytes(buffer);
                    break;
                }
            }
        } finally {
            outStream.close();
            inStream.close();
            client.close();
        }
    }
}
