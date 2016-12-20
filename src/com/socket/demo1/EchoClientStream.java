/**
 * @(#)Echo.java
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
 *  1     2014-10-30     Administrator    Created
 **********************************************
 */

package com.socket.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.TestCase;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-30
 */
public class EchoClientStream extends TestCase{
    
    public static void main(String[] args) throws Exception {
        EchoClientStream o = new EchoClientStream();
        o.testSocketClient();
    }
    public void testSocketClient() throws Exception {
        Socket client = null; // 表示客 户端
        client = new Socket("192.168.0.228", 8888);
        BufferedReader buf = null; // 一次性接收完成
        PrintStream out = null; // 发送数据
        BufferedReader input = null; // 接收键盘数据
        input = new BufferedReader(new InputStreamReader(System.in));
        buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream());
        boolean flag = true; // 定义标志位
        while (flag) {
            System.out.print("输入信息：");
            String str = input.readLine(); // 接收键盘的输入信息
            out.println(str);
            if ("bye".equals(str)) {
                flag = false;
            } else {
                String echo = buf.readLine(); // 接收返回结果
                System.out.println(echo); // 输出回应信息
            }
        }
        buf.close();
        client.close();
    }
}
