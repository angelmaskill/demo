package com.httpclientpri.demo1;

import com.socket.utils.Nksocket;
import junit.framework.TestCase;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class TestHttpClientMain extends TestCase {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
//		String ip = "192.168.110.0";//没有这个IP,走的参数是clientManagerPara,不设置参数,默认84S
        String ip = "192.168.32.181";//有这个IP,走的参数是 clientPara,不设置参数,默认30S
        String urlStress = "http://" + ip;
//		checkConnect(ip, 80);
//		testAddress(urlStress);
//		init(client);
        printPara(client);
        long begin = System.currentTimeMillis();
        httpSendGet(client, urlStress);
        long end = System.currentTimeMillis();
        System.out.println("eclipsed time:" + (end - begin));
    }

    public static void checkConnect(String ip, int port) throws Exception {
        long begin = System.currentTimeMillis();
        InetAddress addr = InetAddress.getByName(ip);
        Nksocket ns = new Nksocket();
        ns.printReachableIP(addr, port);
        long end = System.currentTimeMillis();
        System.out.println("eclipsed time:" + (end - begin));

    }

    public static String httpSendGet(HttpClient client, String urlStress) {
        HttpMethod method = new GetMethod(urlStress);
        String result = "";
        try {
            int statusCode = client.executeMethod(method);
            System.out.println(statusCode);
            byte[] responseBody = null;
            responseBody = method.getResponseBody();
            result = new String(responseBody);
            System.out.println(result);

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String httpSendPost(HttpClient client, String urlStress, String msg) {
        String result = "";
        PostMethod method = new PostMethod(urlStress);
        ((PostMethod) method).addParameter("msg", msg);
        HttpMethodParams param = method.getParams();
        param.setContentCharset("UTF-8");
        try {
            client.executeMethod(method);
            // 打印服务器返回的状态
            System.out.println(method.getStatusLine());
            // 打印返回的信息
            InputStream stream = method.getResponseBodyAsStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuffer buf = new StringBuffer();
            String line;
            while (null != (line = br.readLine())) {
                buf.append(line).append("\n");
            }
            result = buf.toString();

        } catch (Exception e) {
            return null;
        } finally {
            // 释放连接
            method.releaseConnection();
        }
        return result;
    }

    private static void printPara(HttpClient client) {
        /**
         * 直接设置连接的参数.
         */
        HttpClientParams clientParams = client.getParams();
        long clientCoTimeout = clientParams.getConnectionManagerTimeout();
        long clientSoTimeout = clientParams.getSoTimeout();
        DefaultHttpMethodRetryHandler retry = (DefaultHttpMethodRetryHandler) clientParams
                .getParameter(HttpMethodParams.RETRY_HANDLER);
        int retcount = retry.getRetryCount();

        /**
         * 设置连接管理器参数.
         */
        HttpConnectionManager connectionManager = client.getHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        long clientManagerCoTimeout = params.getConnectionTimeout();
        long clientManagerSoTimeout = params.getSoTimeout();

        System.out.println("====客户端参数设置检测con:" + clientCoTimeout + ";  readData:" + clientSoTimeout + ";  retry:" + retcount);
        System.out.println("====客户端管理器设置检测con:" + clientManagerCoTimeout + ";  readData:" + clientManagerSoTimeout + ";  retry:"
                + retcount);

    }

    private static void init(HttpClient client) {
        /**
         * 设置客户端连接参数
         */
        HttpClientParams httpClientParams = client.getParams();
        // 设置httpClient的连接超时，对连接管理器设置的连接超时是无用的
        httpClientParams.setConnectionManagerTimeout(10000);
        httpClientParams.setSoTimeout(10000);
        // 等价于4.2.3中的 CONN_MANAGER_TIMEOUT
        client.setParams(httpClientParams);
        // 另外设置http client的重试次数，默认是3次；当前是禁用掉（如果项目量不到，这个默认即可）
        httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));

        /**
         * 设置客户端连接管理器参数
         */

        HttpConnectionManagerParams managerParams = new HttpConnectionManagerParams();
        HttpConnectionManager connectionManager = client.getHttpConnectionManager(); // 连接超时
        managerParams.setConnectionTimeout(2000); // 读取数据超时
        managerParams.setSoTimeout(2000); // 最大连接数
        managerParams.setMaxTotalConnections(500); // 单机最大连接数
        managerParams.setDefaultMaxConnectionsPerHost(500);
        managerParams.setStaleCheckingEnabled(true);
        connectionManager.setParams(managerParams);

    }

    /*
     * 测试大病保险接口是否通畅 by mayanlu 2014-06-11
     */
    public static int testAddress(String urlpathStr) {
        Boolean flag = true;
        String urlStr = urlpathStr;
        URL url;
        HttpURLConnection url_con = null;
        String response_content;
        long begin = 0;
        long end = 0;
        int connectionInt = 500;
        try {
            begin = System.currentTimeMillis();
            // 打开远程连接
            url = new URL(urlStr);
            url_con = (HttpURLConnection) url.openConnection();

            // 如果不设置以下两个时间，可能会导致程序僵死而不继续往下执行
            url_con.setConnectTimeout(8000);
            url_con.setReadTimeout(8000);

            // url_con.connect();//此方法不好用。
            int size = url_con.getHeaderFields().size();
            if (size > 0) {
                connectionInt = url_con.getResponseCode();
            }

            if (connectionInt == 500) {
                throw new RuntimeException("无法建立连接，请核查接口设置或网络设置！");
            }

            url_con.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
            end = System.currentTimeMillis();
            System.out.println("testAddress eclipsed time:" + (end - begin));
        }
        return connectionInt;
    }

}
