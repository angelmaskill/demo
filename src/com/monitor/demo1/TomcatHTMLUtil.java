package com.monitor.demo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import sun.misc.BASE64Encoder;

public class TomcatHTMLUtil {

	public static void main(String[] args) {
		String appname = "examples";
//		message("http://localhost:8088/manager/jmxproxy?qry=*%3Atype%3DManager%2C*");//获取JMX Proxy Servlet
		message("http://localhost:8088/manager/serverinfo");// 获取服务器信息
		getTomcatWebAppData();// 获取项目列表
//		reloadWebApp(appname);//重启项目
//		stopWebApp(appname);//停止项目
//		startWebApp(appname);// 启动项目
//		getTomcatWebAppData();// 获取项目列表
	}

	private static String message(String operateURL) {

		StringBuffer dataResult = new StringBuffer();
		URL url = null;
		try {
			url = new URL(operateURL);

			URLConnection conn = (URLConnection) url.openConnection();
			// 这里我是把admin，123456
			// 这个用户信息放到了一个json文件中以json形式存放，然后取出来，如果不是以这种方式存放，则可以直接设置username =
			// admin ，password =123456
//			String data = FileUtil.readJson();
			String username = "admin";
			String password = "123456";
			String configuration = username + ":" + password; // manager角色的用户
			String encodedPassword = new BASE64Encoder().encode(configuration.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + encodedPassword);
			// URL授权访问 -- End

			InputStream is = conn.getInputStream();
			BufferedReader bufreader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = bufreader.readLine()) != null) {
				dataResult.append(line + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dataResult.toString());
		return dataResult.toString();
	}

	public static ArrayList<WebApp> getTomcatWebAppData() {

		ArrayList<WebApp> webAppArrayList = new ArrayList<WebApp>();

		String data = TomcatHTMLUtil.message("http://localhost:8088/manager/list");

		String[] oldDataStrs = data.split("/");

		if (oldDataStrs[0].startsWith("OK")) {
			for (int i = 0; i < oldDataStrs.length; i++) {
				String name = oldDataStrs[i].split(":")[0];
				if (name.startsWith("legacy-proxy")) {
					WebApp webApp = new WebApp();
					webApp.setName(name);
					if (oldDataStrs[i].split(":")[1].equals("running")) {
						if (oldDataStrs[i].split(":")[2].equals("0")) {
							webApp.setStatus("运行");
						} else {
							webApp.setStatus("异常");
						}
					} else if (oldDataStrs[i].split(":")[1].equals("stopped")) {
						if (oldDataStrs[i].split(":")[2].equals("0")) {
							webApp.setStatus("停止");
						} else {
							webApp.setStatus("异常");
						}
					} else {
						webApp.setStatus("异常");
					}
					System.out.println("app  status: " + webApp.toString());
					webAppArrayList.add(webApp);
				}
			}
		}
		return webAppArrayList;
	}

	/**
	 * 重新部署一个项目
	 * 
	 * @param webAppName
	 * @return
	 */
	public static boolean reloadWebApp(String webAppName) {
		String data = TomcatHTMLUtil.message("http://localhost:8088/manager/reload?path=/" + webAppName);
		if (data.startsWith("OK")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 停止一个项目
	 * 
	 * @param webAppName
	 * @return
	 */
	public static boolean stopWebApp(String webAppName) {
		String data = TomcatHTMLUtil.message("http://localhost:8088/manager/stop?path=/" + webAppName);
		if (data.startsWith("OK")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 开始一个项目
	 * 
	 * @param webAppName
	 * @return
	 */
	public static boolean startWebApp(String webAppName) {
		String data = TomcatHTMLUtil.message("http://localhost:8088/manager/start?path=/" + webAppName);
		if (data.startsWith("OK")) {
			return true;
		} else {
			return false;
		}
	}

}