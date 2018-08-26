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

package com.mina.test5;

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
public class Client {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws Exception {
		Socket client;
		client = new Socket("localhost", 9123);
		PrintStream out = null; // 发送数据
		BufferedReader input = null; // 接收键盘数据
		BufferedReader buf = null; // 一次性接收完成
		input = new BufferedReader(new InputStreamReader(System.in));
		buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintStream(client.getOutputStream());

		/*
		 * 发送信息到服务器
		 */

		String str = "client says:begin ";
		String info="";
		/**
		 * 获取服务端数据
		 */

		// 客户端进行反馈
		while (!"bye".equals(info)) {
			System.out.print("客户端输入信息：");
			str = input.readLine(); // 接收键盘的输入信息
			out.println(str);
			//接受服务器的信息
			info = buf.readLine();
			System.out.println("服务器端回复：" + info);
		}
		buf.close();
		client.close();

	}
}
