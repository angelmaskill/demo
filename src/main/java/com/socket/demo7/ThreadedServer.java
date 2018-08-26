package com.socket.demo7;

import java.net.ServerSocket;
import java.net.Socket;

class ThreadedServer {

public static void main(String args[]) throws Exception {
    int port = 52333;
    ServerSocket ss = new ServerSocket(port);
    System.out.println("Server started");
    while (true) {
      Socket s = ss.accept();
      System.out.println("accept client");
      ServerThread st = new ServerThread(s);      
      st.start();
      System.out.println("start thread");
    }
}
}