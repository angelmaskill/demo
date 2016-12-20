package com.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/*
 * 使用JMX协议,可以在运行的时候,动态改变内存对象的值.
 * JMX为应用程序植入管理功能的框架
 */
public class HelloAgent {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MBeanServer server = MBeanServerFactory.createMBeanServer();

        ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
        Hello h = new Hello();
        server.registerMBean(h, helloName);

        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter, adapterName);

        adapter.start();
        System.out.println("start.....");
        while(true){
        	System.out.println("name的值:"+h.getName());
        	Thread.sleep(2000);
        }
	}

}
