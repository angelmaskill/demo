package com.socket.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * [使用 Java 测试网络连通性的几种方法 - 文章 - 伯乐在线](http://blog.jobbole.com/30714/)
 * @author mayanlu
 *
 */
public class Nksocket {
	public static final  String ORACLE_TYPE="1";
	public static final  String HBASE_TYPE="2";
	
	private static class SingletonHolder {  
	    private static final Nksocket INSTANCE = new Nksocket();  
	    }  
	    public Nksocket (){}  
	    public static final Nksocket getInstance() {  
	    return SingletonHolder.INSTANCE;  
	}  
	    
	public boolean printReachableIP(InetAddress remoteAddr, int port) {
		String retIP = null;
		boolean isReach =false;

		Enumeration<NetworkInterface> netInterfaces;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
				while (localAddrs.hasMoreElements()) {
					InetAddress localAddr = localAddrs.nextElement();
					if (isReachable(localAddr, remoteAddr, port, 5000)) {
						retIP = localAddr.getHostAddress();
						break;
					}
				}
			}
		} catch (SocketException e) {
			System.out.println("Error occurred while listing all the local network addresses.");
		}
		if (retIP == null) {
			System.out.println("NULL reachable local IP is found!");
		} else {
			isReach =true;
			System.out.println("Reachable local IP is found, it is " + retIP);
		}
		return isReach;
	}

	/**
	 * 使用socket 的connect 方法進行链路检测.
	 * @param localInetAddr
	 * @param remoteInetAddr
	 * @param port
	 * @param timeout
	 * @return
	 */
	boolean isReachable(InetAddress localInetAddr, InetAddress remoteInetAddr, int port, int timeout) {

		boolean isReachable = false;
		Socket socket = null;
		try {
			socket = new Socket();
			// 端口号设置为 0 表示在本地挑选一个可用端口进行连接
			SocketAddress localSocketAddr = new InetSocketAddress(localInetAddr, 0);
			socket.bind(localSocketAddr);
			InetSocketAddress endpointSocketAddr = new InetSocketAddress(remoteInetAddr, port);
			socket.connect(endpointSocketAddr, timeout);
			System.out.println("SUCCESS - connection established! Local: " + localInetAddr.getHostAddress()
					+ " remote: " + remoteInetAddr.getHostAddress() + " port" + port);
			isReachable = true;
		} catch (IOException e) {
			System.out.println("FAILRE - CAN not connect! Local: " + localInetAddr.getHostAddress() + " remote: "
					+ remoteInetAddr.getHostAddress() + " port" + port);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Error occurred while closing socket..");
				}
			}
		}
		return isReachable;
	}
	public static void main(String[] args) throws UnknownHostException {
		Nksocket ns2 = new Nksocket();
		InetAddress addr = InetAddress.getByName("192.168.113.12");
		ns2.printReachableIP(addr,2181);
	}

	public static  boolean valNetReach(String type) throws UnknownHostException{
		boolean isReach = false;
		Nksocket ns2 =  Nksocket.getInstance();
		Map<String ,Boolean> paraMap = new HashMap();
		String [][] ipConfig=null;
		String [][] ipConfig_oracle =  {{"192.168.113.8","1521"}};
		String [][] ipConfig_hbase =  {{"192.168.113.11","2181"},{"192.168.113.12","2181"},{"192.168.113.13","2181"}};
		if("1".equals(type)){
			ipConfig=ipConfig_oracle;
		}else if("2".equals(type)){
			ipConfig=ipConfig_hbase;
		}
		for (String[] strings : ipConfig) {
			String ip = strings[0];
			String port = strings[1];
			InetAddress addr = InetAddress.getByName(ip);
			boolean val = ns2.printReachableIP(addr,Integer.parseInt(port));
			paraMap.put(ip + port, val);
		}
		if(paraMap.containsValue(true)){
			isReach = true;
		}
		return isReach;
	}
}
