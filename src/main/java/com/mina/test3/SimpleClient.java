package com.mina.test3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 8000);
		// socket.setSoLinger(true, 0); //Socket关闭后，底层Socket立即关闭
		// socket.setSoLinger(true, 3600); //Socket关闭后，底层Socket延迟3600秒再关闭
		OutputStream os = socket.getOutputStream();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10000; i++) {
			sb.append(i);
		}
		os.write(sb.toString().getBytes()); // 发送一万个字符
		System.out.println("开始关闭Socket");
		long begin = System.currentTimeMillis();
		socket.close();
		long end = System.currentTimeMillis();
		System.out.println("关闭socket所使用的时间为：" + (end - begin) + "ms");
	}

}