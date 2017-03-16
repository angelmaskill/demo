package com.monitor.demo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import sun.misc.BASE64Encoder;

/**
 * 
 * 使用 Tomcat提供的manager应用进行数据采集
 * 
 * <pre>
 * 		第一步:修改/conf目录下的tomcat-users.xml文件
 * 		第二步:双击eclipse中服务器中的tomcat
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class TomcatHTMLUtil {

	public static void main(String[] args) {
//		getV6();
//		getV7();
		getV8();
	}

	private static void getV6() {
		String appname = "examples";
		String serverUrl6 = "";
//		serverUrl6 = "http://localhost:8088/manager/jmxproxy?qry=*%3Aj2eeType=Servlet%2c*";//测试通过
//		serverUrl6 = "http://localhost:8088/manager/jmxproxy?qry=Catalina%3Atype%3DEnvironment%2Cresourcetype%3DGlobal%2Cname%3DsimpleValue";
//		serverUrl6 = "http://localhost:8088/manager/jmxproxy?qry=*%3Atype%3DRequestProcessor%2C*";//测试通过
		serverUrl6 = "http://localhost:8088/manager/jmxproxy?qry=*%3Atype%3DManager%2C*";// 测试通过
		message(serverUrl6);// 获取JMX Proxy Servlet
		System.out.println("-----------------------");
		serverUrl6 = "http://localhost:8088/manager/serverinfo";
		message(serverUrl6);// 获取服务器信息
		System.out.println("-----------------------");
		String appUrl6 = "http://localhost:8088/manager/list";
		getTomcatWebAppData(appUrl6);// 获取项目列表
		System.out.println("-----------------------");
		reloadWebApp(appname, 6, 8088);// 重启项目
		stopWebApp(appname, 6, 8088);// 停止项目
		startWebApp(appname, 6, 8088);// 启动项目
		getTomcatWebAppData(appUrl6);// 获取项目列表
	}

	private static void getV7() {
		String appname = "examples";
		String serverUrl7 = "";
//		serverUrl7=		"http://localhost:8080/manager/jmxproxy?qry=*%3Atype%3DRequestProcessor%2C*";//测试通过
//		serverUrl7=		"http://localhost:8080/manager/jmxproxy?qry=*%3Aj2eeType=Servlet%2c*";//测试通过
//		serverUrl7=		"http://localhost:8080/manager/jmxproxy?qry=Catalina%3Atype%3DEnvironment%2Cresourcetype%3DGlobal%2Cname%3DsimpleValue";//测试通过
		serverUrl7 = "http://localhost:8080/manager/jmxproxy?qry=*%3Atype%3DManager%2C*";// 测试通过
		message(serverUrl7);// 获取服务器信息
		System.out.println("-----------------------");
		serverUrl7 = "http://localhost:8080/manager/text/serverinfo";
		message(serverUrl7);// 获取服务器信息
		System.out.println("-----------------------");
		String appUrl7 = "http://localhost:8080/manager/text/list";
		getTomcatWebAppData(appUrl7);// 获取项目列表
		System.out.println("-----------------------");
		reloadWebApp(appname, 7, 8080);// 重启项目
		stopWebApp(appname, 7, 8080);// 停止项目
		startWebApp(appname, 7, 8080);// 启动项目
		getTomcatWebAppData(appUrl7);// 获取项目列表
	}

	private static void getV8() {
		String appname = "examples";
		String serverUrl8 = "";
//		serverUrl8=		"http://localhost:8080/manager/jmxproxy?qry=*%3Atype%3DRequestProcessor%2C*";//测试通过
//		serverUrl8=		"http://localhost:8080/manager/jmxproxy?qry=*%3Aj2eeType=Servlet%2c*";//测试通过
//		serverUrl8=		"http://localhost:8080/manager/jmxproxy?qry=Catalina%3Atype%3DEnvironment%2Cresourcetype%3DGlobal%2Cname%3DsimpleValue";//测试通过
		serverUrl8 = "http://localhost:8080/manager/jmxproxy?qry=*%3Atype%3DManager%2C*";// 测试通过
		message(serverUrl8);// 获取服务器信息
		System.out.println("-----------------------");

		serverUrl8 = "http://localhost:8080/manager/text/serverinfo";
		message(serverUrl8);// 获取服务器信息
		System.out.println("-----------------------");
		String appUrl8 = "http://localhost:8080/manager/text/list";
		getTomcatWebAppData(appUrl8);// 获取项目列表
		System.out.println("-----------------------");
		reloadWebApp(appname, 7, 8080);// 重启项目
		stopWebApp(appname, 7, 8080);// 停止项目
		startWebApp(appname, 7, 8080);// 启动项目
		getTomcatWebAppData(appUrl8);// 获取项目列表
	}

	/**
	 * 采集服务器基本信息
	 * 
	 * @param operateURL
	 * @return
	 */
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

	/**
	 * 通过list命令查看Web应用列表和会话数信息
	 * 
	 * @return
	 */
	public static ArrayList<WebApp> getTomcatWebAppData(String url) {

		ArrayList<WebApp> webAppArrayList = new ArrayList<WebApp>();

		String data = TomcatHTMLUtil.message(url);

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
	public static boolean reloadWebApp(String webAppName, int version, int port) {
		String versionStr = version == 6 ? "" : "text/";
		String data = TomcatHTMLUtil.message("http://localhost:" + port + "/manager/" + versionStr + "reload?path=/"
				+ webAppName);
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
	public static boolean stopWebApp(String webAppName, int version, int port) {
		String versionStr = version == 6 ? "" : "text/";
		String data = TomcatHTMLUtil.message("http://localhost:" + port + "/manager/" + versionStr + "stop?path=/"
				+ webAppName);
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
	public static boolean startWebApp(String webAppName, int version, int port) {
		String versionStr = version == 6 ? "" : "text/";
		String data = TomcatHTMLUtil.message("http://localhost:" + port + "/manager/" + versionStr + "start?path=/"
				+ webAppName);
		if (data.startsWith("OK")) {
			return true;
		} else {
			return false;
		}
	}

}