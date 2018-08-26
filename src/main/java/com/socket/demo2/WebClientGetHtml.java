/**
 * @(#)WebClientGetHtml.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
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

package com.socket.demo2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-11-6
 */
public class WebClientGetHtml {
    public static void main(String[] args) {
        try {
            // 打开一个客户端socket连接 
            Socket clientSocket1 = new Socket("www.jit.edu.cn", 80);
            System.out.println("Client1: " + clientSocket1);
            // 取得一个网页 
            getPage(clientSocket1);
        } catch (UnknownHostException uhe) {
            System.out.println("UnknownHostException: " + uhe);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    }

    /**
     * * *通过建立的连接请求一个页面，显示回应然后关闭socket 
     * */
    public static void getPage(Socket clientSocket) {
        try {
            /*
             * 输出到服务器的流：发送到服务器的信息
             */
            DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
            // 向服务器发出HTTP请求 
            outStream.writeBytes("GET / HTTP/1.0\n\n");
            // 读出回应 
            String responseLine;
            /*
             * 输入流：获取服务器端返回的信息
             * 读取器：BufferedReader
             */
            BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while ((responseLine = inReader.readLine()) != null) {
                // 把每一行显示出来 
                System.out.println(responseLine);
            }
            /*
             * 关闭流：先关闭输出流；再关闭输入流；最后关闭客户端
             */
            outStream.close();
            inReader.close();
            clientSocket.close();
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe);
        }
    }

}
