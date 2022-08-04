package com.socket.demo9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * readline()方法 在客户端socket未主动关闭 或者 读取的数据字符串未遇到 \n \r 回车，都是认为数据没有发送完毕，
 * 就造成readline() 会一直等待数据传送完毕，就造成了阻塞，导致程序不能顺利进行
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-8-4 11:03
 * @description：
 * @modified By：
 * @version:
 */
public class SocketServer {
    public static void main(String[] args) {
        SocketServer ss = new SocketServer();
        int port = 8888;
        try {
            ss.startServer(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket server = null;
        try {
            while (true) {
                server = serverSocket.accept();
                System.out.println("server socket is start……");
                try {
                    BufferedInputStream in = new BufferedInputStream(server.getInputStream());
                    BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));

                    String clientstring = null;
                    out.println("欢迎客户端连入！");
                    out.flush();
                    byte[] buf = new byte[128];
                    int size = 0;
                    // 客户端必须明确告知自己说完了，服务端这里才能拿到请求数据，否则read 方法会一直阻塞
                    /*while ((size = in.read(buf, 0, buf.length)) != -1) {
                        System.out.println("服务端收到请求信息：" + new String(buf));
                    }*/
                    String message = "";
                    String line = null;
                    while((line = input.readLine())!=null){
                        message += line;
                    }
                    System.out.println("服务端收到请求信息：" + new String(message));
                    out.print(" client is over");
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    server.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
