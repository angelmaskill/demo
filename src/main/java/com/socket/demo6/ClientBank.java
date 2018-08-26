/**
 * @(#)ClientBank.java
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
 *  1     2014-12-1     Administrator    Created
 **********************************************
 */

package com.socket.demo6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-12-1
 */
public class ClientBank {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Socket client;
        client = new Socket("218.22.252.74", 9192);
//        client = new Socket("192.168.0.109", 7777);
        PrintStream out = null; // 发送数据
        BufferedReader buf = null; // 一次性接收完成
        out = new PrintStream(client.getOutputStream());
        /*
         * 发送信息到服务器
         */
        System.out.print("客户端输入信息：");
        String str = ""; // 接收键盘的输入信息
        str="000004161.00010000025699999999999999994B  3416020001          10000000000388                                              0001|测试用户       00012014120211560312A6094A9F48035A0F4A9023CC041FE181E81B636D2CC51927372E52ADE6977013F9978F430BD63B5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F5426E0C9B1A4A74F08BB5DB6B37C06D7";
        out.println(str);//只发送了一次信息。
        buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        /*
         * 从服务器读取信息
         * 读取器：BufferedReader
         */

        String info;
        while ((info = buf.readLine()) != null) {
            System.out.println("服务器端给客户端返回：" + info);//输出到屏幕
        }
        buf.close();
        client.close();
       
    }
}
