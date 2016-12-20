package com.mina.test1;

import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 1; i++) {
			Thread.sleep(300);
			System.out.println(i);
			Thread t = new Thread(new A(String.valueOf(i)));
			t.start();

		}

	}

}

class A implements Runnable {

	public static final String IP_ADDR = "127.0.0.1";// 服务器地址
	public static final int PORT = 8888;// 服务器端口号
	String s = "";

	public A(String st) {
		s = st;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket socket = null;
		try {
			socket = new Socket(IP_ADDR, PORT);
			PrintStream out = new PrintStream(socket.getOutputStream());
			while (true) {
				out.print(s);
				// String ret = input.readUTF();
				// System.out.println("服务器端返回过来的是: " + ret);
				// out.close();
				Thread.sleep(30000);

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}