/**
 * @(#)ClientAppMul.java
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

package com.socket.demo4MulThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-11-6
 */
public class ClientAppMul {

    /**
     * @param args
     * @throws IOException 
     * @throws UnknownHostException 
     */
    /*public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO Auto-generated method stub
        Socket client;
        client = new Socket("localhost",1028);
        PrintStream out = null; // 发送数据
        BufferedReader input = null; // 接收键盘数据
        BufferedReader buf = null; // 一次性接收完成
        try {
            
            input = new BufferedReader(new InputStreamReader(System.in));
            buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream());
            
             * 发送信息到服务器
             
            System.out.print("客户端输入信息：");
            String str = input.readLine(); // 接收键盘的输入信息
            out.println(str);
            
             * 从服务器读取信息
             * 读取器：BufferedReader
             

            String info;
            while ((info = buf.readLine()) != null) {
                System.out.println("服务器端给客户端返回："+info);//输出到屏幕
            }
            out.close();
            input.close();
            buf.close();
            client.close();
        } catch (SecurityException e) {
            System.out.println("SecurityException when connecting Server!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException when connecting Server!");
        }
    }*/
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Socket client;
        client = new Socket("localhost", 1028);
        PrintStream out = null; // 发送数据
        BufferedReader input = null; // 接收键盘数据
        BufferedReader buf = null; // 一次性接收完成
        input = new BufferedReader(new InputStreamReader(System.in));
        buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream());

        /*
         * 发送信息到服务器
         */
        System.out.print("客户端输入信息：");
        String str = input.readLine(); // 接收键盘的输入信息
        out.println(str);//只发送了一次信息。
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
