/**
 * @(#)BootstrapClassLoade.java
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
 *  1     2015-8-3     Administrator    Created
 **********************************************
 */

package com.jvm.classloader.demo3;

import java.io.InputStream;
import java.net.URL;

import junit.framework.TestCase;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-3
 */
public class ClassLoade extends TestCase {
	class HelloWorld {

	}

	public void testClassLoad() throws Exception {
		ClassLoader loader = HelloWorld.class.getClassLoader();
		System.out.println(loader);
		// 使用ClassLoader.loadClass()来加载类，不会执行初始化块
		// loader.loadClass("com.jvm.classloader.Test2");

		// 使用Class.forName()来加载类，默认会执行初始化块
		// Class.forName("com.jvm.classloader.Test2");

		// 使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
		// Class.forName("com.jvm.classloader.Test2", false, loader);
	}

	public void testGetClassLoader() {
		HelloWorld hello = new HelloWorld();
		Class c = hello.getClass();
		ClassLoader loader = c.getClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
	}

	public void testBootClassload() {
		// 获取根类加载器所加载的全部URL数组
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}
		System.out.println(System.getProperty("sun.boot.class.path"));
	}

	public void testExtClassLoad() {
		System.out.println(System.getProperty("java.ext.dirs"));
		ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader().getParent();
		System.out.println("the parent of extension classloader : " + extensionClassloader.getParent());
	}

	public void testAppClassload() throws Exception {
		System.out.println(System.getProperty("java.class.path"));
		ClassLoader appClassloader = ClassLoader.getSystemClassLoader();
		System.out.println("the parent of appClassloader classloader : " + appClassloader.getParent());
		System.out.println("the parent's parent of appClassloader classloader : "
				+ appClassloader.getParent().getParent());
	}

	public void testClassload() {
		ClassLoader myLoder = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
				InputStream in = getClass().getResourceAsStream(fileName);
				Class<?> c = null;
				try {
					if (in == null) {
						return super.loadClass(name);
					}
					byte[] b = new byte[in.available()];
					in.read(b);
					c = defineClass(name, b, 0, b.length);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return c;
			}
		};
		Object obj = null;
		try {
			obj = myLoder.loadClass("com.jvm.classloader.ClassLoade").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(obj.getClass());
		System.out.println(obj.getClass().getClassLoader());
		System.out.println(this.getClass().getClassLoader());
		System.out.println(obj instanceof com.jvm.classloader.demo3.ClassLoade);

	}
}
