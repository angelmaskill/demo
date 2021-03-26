package com.mina.test3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();

        Thread.sleep(5000); // 睡眠5秒钟后再读输入流

        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        do {
            len = inputStream.read(buff);
            System.out.println(len);
            if (len != -1) {
                buffer.write(buff, 0, len);
            }
        } while (len != -1);
        System.out.println(new String(buffer.toByteArray())); // 把字节数组转换为字符串
    }
}