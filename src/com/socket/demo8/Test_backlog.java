package com.socket.demo8;

import java.io.*;
import java.net.*;
import org.apache.log4j.Logger;

/**
 * <pre>
 * 总结：
 * 管理客户连接请求的任务是由操作系统来完成的。操作系统把这些连接请求存储在一个先进先出的队列中。许多操作系统限定了队列的最大长度，一般为50。
 * 当队列中的连接请求达到了队列的最大容量时，服务器进程所在的主机会拒绝新的连接请求。
 * 只有当服务器进程通过ServerSocket的accept()方法从队列中取出连接请求，使队列腾出空位时，队列才能继续加入新的连接请求。
 * 对于客户进程，如果它发出的连接请求被加入到服务器的队列中，就意味着客户与服务器的连接建立成功，客户进程从Socket构造方法中正常返回。
 * 如果客户进程发出的连接请求被服务器拒绝，Socket构造方法就会抛出ConnectionException。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class Test_backlog {
	private static Logger logger = Logger.getLogger(Test_backlog.class);

	public static void main(String[] args) throws Exception {
		BufferedReader in = null;
		PrintWriter out = null;
		int backlog = 2;

		ServerSocket serversocket = new ServerSocket(10000, backlog);
		while (true) {
			logger.debug("启动服务端......");
			int i;
			Socket socket = serversocket.accept();
			logger.debug("有客户端连上服务端, 客户端信息如下：" + socket.getInetAddress() + " : " + socket.getPort() + ".");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			do {
				char[] c = new char[1024];
				i = in.read(c);
				logger.debug("服务端收到信息: " + new String(c, 0, i));
			} while (i == -1);
			out.close();
			in.close();
			socket.close();
			logger.debug("关闭服务端......");
		}
	}
}