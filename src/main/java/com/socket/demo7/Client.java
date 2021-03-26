package com.socket.demo7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client {

    public static void main(String args[]) throws Exception {
        int c;
        Socket s = new Socket("192.168.32.181", 52333);
        System.out.println("connect to 192.168.32.181:52333");
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        DataOutputStream dos = new DataOutputStream(out);
        DataInputStream dis = new DataInputStream(in);

        dos.write("maxlisa0102221122001212122121252".getBytes());
        // dos.writeInt(12);
        System.out.println("write maxlisa");

        System.out.print("return value: ");
        byte bytes[] = new byte[3];
        while ((c = in.read(bytes)) != -1) {
            System.out.print(new String(bytes));
        }
        s.close();
        System.out.println("close connection");
    }
}