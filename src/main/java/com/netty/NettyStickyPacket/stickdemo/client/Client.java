package com.netty.NettyStickyPacket.stickdemo.client;

import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 10101);
		String message = "hello";

		for (int i = 0; i < 20; i++) {
			socket.getOutputStream().write(message.getBytes());
		}

		socket.close();
	}
}