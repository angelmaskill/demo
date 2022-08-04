package com.socket.demo9;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-8-4 11:03
 * @description：
 * @modified By：
 * @version:
 */
public class SocketClient {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        Socket client = new Socket("localhost", 8888);
        OutputStream out = client.getOutputStream();
        InputStream input = client.getInputStream();

        out.write("client say hello socket to server!".getBytes());
        out.flush();
        client.shutdownOutput();  //调用shutdown 通知对端请求完毕
        read(input);
        out.close();
    }

    public static void read(InputStream input) throws IOException {
        byte[] buf = new byte[128];
        int size = 0;
        while ((size = input.read(buf, 0, buf.length)) != -1) {
            System.out.print(new String(buf));
        }
    }
}
