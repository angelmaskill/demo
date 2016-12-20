/**
 * @(#)SocketTest1.java
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
 *  1     2014-10-30     Administrator    Created
 **********************************************
 */

package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import junit.framework.TestCase;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-30
 */
public class SocketTest1 extends TestCase {
    
    /*
     * 1 Java的基本网路支持
     * 获取ip对象
     * 判断是否联通
     * 
     */
    /*public void test1() throws Exception {
        InetAddress locAdd = InetAddress.getLocalHost();//获取本机ip对象。
        InetAddress remAdd = InetAddress.getByName("cd.itcast.cn");

        byte[] ip = { (byte) 211, 100, 52, 39 };
        
        InetAddress remAdd2 = InetAddress.getByAddress(ip);//通过ip获取百度地址对象。
        System.out.println("本机IP= " + locAdd.getHostAddress());
        System.out.println("成都传智播客IP= " + remAdd.getHostAddress());
        System.out.println("是否可以送达：" + remAdd2.isReachable(3000));
    }*/
    
    /*
     * URL对象
     * 
     */
    /*public void testUrl() throws Exception {
        URL url = new URL("http", "cd.itcast.cn", 80, "/cd/cdjava.shtml");
        InputStream is = url.openStream();
        Scanner sc = new Scanner(is);
        sc.useDelimiter("\r\n");
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }

    }*/
    
    /*
     * URLConnection
     * URLConnection封装访问远程网络资源一般方法的类，通过它可以建立与远程服务器的连接，检查远程资源的一些属性。
     *
     *常用方法：
        public int getContentLength():获取内容长度
        public String getContentType():取得内容类型
        public InpurStream getInputStream():获得连接的输入流

     */
    /*public void testUrlConnection() throws Exception {
        URL url = new URL("http", "cd.itcast.cn", 80, "/cd/cdjava.shtml");
        URLConnection uc = url.openConnection();
        System.out.println("内容长度= " + uc.getContentLength());
        System.out.println("内容类型= " + uc.getContentType());
    }*/
    
    /*
     * URLEncoder与URLDecoder
     * 
     * Java中URLEncoder可以为传递的内容编码，而URLDecoder可以为传递的内容解码；
        URLEncoder：
        public static String encode(String s,String enc):使用指定的编码机制将字符串转编码成application/x-www-form-urlencoded MIME字符串；
        URLDecoder：
        public static String decode(String s,String enc):使用指定编码机制对application/x-www-form-urlencoded MIME字符串解码
     */
    /*public void testUrlCoder() throws Exception {
        String s = "成都传智播客cditcast";

        s = URLEncoder.encode(s, "UTF-8");
        System.out.println("编码后： " + s);

        s = URLDecoder.decode(s, "UTF-8");
        System.out.println("解码后： " + s);

    }*/
    
    /**
     * 服务端：ServerSocket
     * ServerSocket类主要在服务端程序的开发中，用于接收客户端的连接请求。
     * 方法1；创建serverSocket实例，指定监听端口   ServerSocket(int port)
     * 方法2；等待客户端连接，此方法连接之前一直堵塞  accept()
     * 方法3：放回服务器的ip地址                  getInetAddress()
     * 方法4：返回ServerSocket 的关闭状态         isClosed()
     * 方法5：关闭ServerSocket                  close() 
     * 
     */
    /**
     * 客户端连接：Socket。每一个Socket都表示每一个客户端连接对象。
     * 方法：
     *  Socket(String host,int port):       由服务器的主机名称和端口得到Socket对象
        InputStream getInputStream():       返回其输入流
        OutputStream getOutputStream():     返回其输出流
        void  close():                      关闭Socket连接
        boolean isClosed():                 判断Socket是否关闭

     */
    
    /**
     * Socket编程步骤
     *  1.建立网络连接；
        2.打开连接到Socket的输入/输出流；
        3.通过已打开的IO流进行读写操作；
        4.关闭已打开的IO流和Socket；

     */
    
    /*public void testServerSocket() throws Exception {
        ServerSocket ss = new ServerSocket(1433);
        System.out.println("等到客户端连接");
        Socket s = ss.accept();//等待客户端连接，得到连接的客户端
        System.out.println("客户端IP= "+s.getLocalAddress());//获取客户端的ip
        String msg = "亲，你好，帅哥！";
        PrintStream ps = new PrintStream(s.getOutputStream());//客户端的输出
        ps.print(msg);//从服务端把数据打印出去
        
        s.close();
        ss.close();

    }
    
    public void testServer() throws Exception {
        Socket s = new Socket("127.0.0.1", 1433);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = br.readLine();
        System.out.println("server:" + str);
        s.close();
        br.close();
    }*/
}
