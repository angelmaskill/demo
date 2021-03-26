package com.monitor.demo1;

import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * 使用 Tomcat提供的manager应用进行数据采集
 *
 * <pre>
 * 		第一步:修改/conf目录下的tomcat-users.xml文件
 * 		第二步:双击eclipse中服务器中的tomcat
 * </pre>
 *
 * @author mayanlu
 */
public class TomcatHTMLUtil {
    private final static String IP = "localhost";
    private final static String APPURL = "examples";
    private final static String CONTAINERNAME = "Tomcat Version: Apache Tomcat/";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "123456";
    private static String serverUrl = "";

    public static void main(String[] args) {
//		getV6();
//		getV7();
        getV8();

    }

    private static void getV6() {
        String appname = "examples";
        int port = 8088;
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Aj2eeType=Servlet%2c*";// 测试通过
        serverUrl = "http://" + IP + ":" + port
                + "/manager/jmxproxy?qry=Catalina%3Atype%3DEnvironment%2Cresourcetype%3DGlobal%2Cname%3DsimpleValue";
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Atype%3DRequestProcessor%2C*";// 测试通过
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Atype%3DManager%2C*";// 测试通过
        message(serverUrl);// 获取JMX Proxy Servlet
        System.out.println("-----------------------");
        serverUrl = "http://" + IP + ":" + port + "/manager/serverinfo";
        message(serverUrl);// 获取服务器信息
        System.out.println("-----------------------");
        String appUrl = "http://" + IP + ":" + port + "/manager/list";
        getAppInfoAndRestart(serverUrl, appUrl, port);
        getTomcatWebAppData(appUrl);// 获取项目列表
        System.out.println("-----------------------");
        reloadWebApp(appname, 6, port);// 重启项目
        stopWebApp(appname, 6, port);// 停止项目
        startWebApp(appname, 6, port);// 启动项目
        getTomcatWebAppData(appUrl);// 获取项目列表
    }

    private static void getV7() {
        String appname = "examples";
        int port = 8080;
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Atype%3DRequestProcessor%2C*";// 测试通过
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Aj2eeType=Servlet%2c*";// 测试通过
        serverUrl = "http://" + IP + ":" + port
                + "/manager/jmxproxy?qry=Catalina%3Atype%3DEnvironment%2Cresourcetype%3DGlobal%2Cname%3DsimpleValue";// 测试通过
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Atype%3DManager%2C*";// 测试通过
        message(serverUrl);// 获取服务器信息
        System.out.println("-----------------------");
        serverUrl = "http://" + IP + ":" + port + "/manager/text/serverinfo";
        message(serverUrl);// 获取服务器信息
        System.out.println("-----------------------");
        String appUrl = "http://" + IP + ":" + port + "/manager/text/list";
        getAppInfoAndRestart(serverUrl, appUrl, port);
        getTomcatWebAppData(appUrl);// 获取项目列表
        System.out.println("-----------------------");
        reloadWebApp(appname, 7, port);// 重启项目
        stopWebApp(appname, 7, port);// 停止项目
        startWebApp(appname, 7, port);// 启动项目
        getTomcatWebAppData(appUrl);// 获取项目列表
    }

    private static void getV8() {
        String appname = "examples";
        int port = 8080;
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Atype%3DRequestProcessor%2C*";// 测试通过
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Aj2eeType=Servlet%2c*";// 测试通过
        serverUrl = "http://" + IP + ":" + port
                + "/manager/jmxproxy?qry=Catalina%3Atype%3DEnvironment%2Cresourcetype%3DGlobal%2Cname%3DsimpleValue";// 测试通过
        serverUrl = "http://" + IP + ":" + port + "/manager/jmxproxy?qry=*%3Atype%3DManager%2C*";// 测试通过
        message(serverUrl);// 获取服务器信息
        System.out.println("-----------------------");
        serverUrl = "http://" + IP + ":" + port + "/manager/text/serverinfo";
        message(serverUrl);// 获取服务器信息
        int containerVersion = getContainerVersion(serverUrl);
        System.out.println("容器版本号:" + containerVersion);
        System.out.println("-----------------------");
        String appUrl = "http://" + IP + ":" + port + "/manager/text/list";
        getAppInfoAndRestart(serverUrl, appUrl, port);
        getTomcatWebAppData(appUrl);// 获取项目列表
        System.out.println("-----------------------");
//		reloadWebApp(appname, containerVersion, port);// 重启项目
//		stopWebApp(appname, containerVersion, port);// 停止项目
//		startWebApp(appname, containerVersion, port);// 启动项目
//		getTomcatWebAppData(appUrl);// 获取项目列表
    }

    /**
     * 获取tomcat版本. 获取程序运行状态. 如果是停止状态,发送邮件.
     */
    private static void getAppInfoAndRestart(String serverUrl, String appUrl, int port) {
        int containerVersion = getContainerVersion(serverUrl);
        getAppStatusAndRestart(appUrl, containerVersion, port);

    }

    private static void getAppStatusAndRestart(String appUrl, int containerVersion, int port) {
        boolean isOK = true;
        ArrayList<WebApp> appList = getTomcatWebAppData(appUrl);
        // 如果发现有异常应用,重启
        for (WebApp webApp : appList) {
            String appName = webApp.getName();
            String appStatus = webApp.getStatus();
            System.out.println("app 状态: " + webApp.toString());
            if ("停止".equals(appStatus)) {
//					stopWebApp(appName, containerVersion, port);
                try {
                    Thread.sleep(1000);
                    startWebApp(appName, containerVersion, port);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isOK = false;
            }
        }
        /**
         * 如果有报错应用,就发送邮件,并告知已经尝试重启.
         */
        if (!isOK) {
            // ....发送邮件逻辑.
        }
        if (isOK == false) {// 曾经有报错应用.
            appList = getTomcatWebAppData(appUrl);
            for (WebApp webApp : appList) {
                String appName = webApp.getName();
                String appStatus = webApp.getStatus();
                System.out.println("app 状态: " + webApp.toString());
                // 只要发现了异常,就退出.
                if ("停止".equals(appStatus)) {
                    isOK = false;
                    break;
                } else {
                    isOK = true;
                }
            }

            /**
             * 如果还有报错应用,就发送邮件,告知已经重启,仍然有项目报错.
             */
            if (!isOK) {
                // ....发送邮件逻辑.
            }
        }

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

            String configuration = USERNAME + ":" + PASSWORD; // manager角色的用户
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
            for (int i = 1; i < oldDataStrs.length; i++) {
                String name = oldDataStrs[i].split(":")[0];
//				if (name.startsWith("legacy-proxy")) {
                WebApp webApp = new WebApp();
                webApp.setName(name);
                if (oldDataStrs[i].split(":")[1].equals("running")) {
                    webApp.setStatus("运行");
                } else if (oldDataStrs[i].split(":")[1].equals("stopped")) {
                    webApp.setStatus("停止");
                } else {
                    webApp.setStatus("异常");
                }
                System.out.println("app  status: " + webApp.toString());
                webAppArrayList.add(webApp);
            }
        }
//		}
        return webAppArrayList;
    }

    /**
     * 重新部署一个项目
     *
     * @param webAppName
     * @return
     */
    public static boolean reloadWebApp(String webAppName, int version, int port) {
        System.out.println("日志打印:" + webAppName + ";" + version + ";" + port + ";");
        String versionStr = version == 6 ? "" : "text/";
        String data = TomcatHTMLUtil.message("http://" + IP + ":" + port + "/manager/" + versionStr + "reload?path=/"
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
        String data = TomcatHTMLUtil.message("http://" + IP + ":" + port + "/manager/" + versionStr + "stop?path=/"
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
        String data = TomcatHTMLUtil.message("http://" + IP + ":" + port + "/manager/" + versionStr + "start?path=/"
                + webAppName);
        if (data.startsWith("OK")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取tomcat版本. 获取程序运行状态. 如果是停止状态,发送邮件.
     */
    private static int getContainerVersion(String operateURL) {
        StringBuffer dataResult = new StringBuffer();
        URL url = null;
        int version = 0;
        try {
            url = new URL(operateURL);

            URLConnection conn = (URLConnection) url.openConnection();
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
                if (line.indexOf(CONTAINERNAME) >= 0) {
                    int index = line.indexOf(CONTAINERNAME);
                    String containerVersion = line.substring(CONTAINERNAME.length(), line.length());
                    System.out.println(containerVersion);
                    version = Integer.parseInt(containerVersion.substring(0, 1));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataResult.toString());
        return version;
    }

}