package com.jvm.classloader.demo4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * resolveClass方法：用于连接一个Class，如果已经连接则什么都不做。该方法直接调用本地方法实现
 * 
 * defineClass方法：将字节码转换为Class实例，即加载.class文件后需要创建一个对应的java.lang.Class对象用于描述该Class。该方法直接调用本地方法实现。
 * 
 * SteveClassLoader默认构造函数会设置System class loader为parent,AppClassLoader
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class SteveClassLoader extends ClassLoader {

	/*
	 * 这是自定义class loader类必须覆盖的方法， 用于告诉class loader到哪里去加载类，比如某个目录或者JAR URL等。
	 * 
	 * @see java.lang.ClassLoader#findClass(java.lang.String):要加载的类全名
	 */
	@Override
	public Class<?> findClass(String name) {
		byte[] bt = loadClassData(name);
		return defineClass(name, bt, 0, bt.length);
	}

	private byte[] loadClassData(String className) {
		// read class
		String strPath = className.replace(".", "/") + ".class";
		System.out.println("strPath : "+strPath);
		InputStream is = getClass().getClassLoader().getResourceAsStream(strPath);
		ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
		// write into byte
		int len = 0;
		try {
			while ((len = is.read()) != -1) {
				byteSt.write(len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// convert into byte array
		return byteSt.toByteArray();
	}

}