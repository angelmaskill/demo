package com.socket.demo7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ServerThread extends Thread {

	DataInputStream dis;

	DataOutputStream dos;

	public ServerThread(Socket s) throws Exception {
		InputStream is = s.getInputStream();
		dis = new DataInputStream(is);
		OutputStream os = s.getOutputStream();
		dos = new DataOutputStream(os);
	}

	public void run() {
		try {
			while (true) {
				byte[] bytes = new byte[20];
				dis.read(bytes);
				System.out.println("read bytes is " + new String(bytes));

				dos.write(bytes);
				System.out.println("write return value " + new String(bytes));
			}
		} catch (Exception e) {
		}
	}
}