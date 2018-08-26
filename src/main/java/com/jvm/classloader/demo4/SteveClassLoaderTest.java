package com.jvm.classloader.demo4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class SteveClassLoaderTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {

		SteveClassLoader loader = new SteveClassLoader();

//		loadClass(loader);

//		 findClass1(loader);

		 findClass2(loader);
	}

	/**
	 * <pre>
	 * SteveClassLoader loadclass 会默认调用父类的loadClass方法进行加载,默认使用父加载器来加载.
	 * ,测试时执行loadClass方法会发现BMW类是委托AppClassLoader加载的
	 * ，所以AppClassLoader可以访问到，不会出错
	 * </pre>
	 * 
	 * @param loader
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static void loadClass(SteveClassLoader loader) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		System.out.println("SteveClassLoaderTest Loaded by : " + SteveClassLoaderTest.class.getClassLoader());
		System.out.println("SteveClassLoader Loaded by : " + SteveClassLoader.class.getClassLoader());
		Class<?> c = loader.loadClass("com.jvm.classloader.demo4.BMW");

		System.out.println("BMW Loaded by :" + c.getClassLoader());

		Car car = (Car) c.newInstance();
		car.run();

		BMW bmw = (BMW) c.newInstance();
		bmw.run();
	}
	
	
	private static void findClass1(SteveClassLoader loader) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = loader.findClass("com.jvm.classloader.demo4.BMW");
		System.out.println("Loaded by :" + c.getClassLoader());
		Object ob = c.newInstance();
		Method md = c.getMethod("run");
		md.invoke(ob);
	}

	

	/**
	 * <pre>
	 * SteveClassLoaderTest类是AppClassLoader加载的,父加载器
	 * 所以其看不见由SteveClassLoader 加载的BMW类,子加载器
	 * 所以child加载的类parent访问不到
	 * </pre>
	 * 
	 * @param loader
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static void findClass2(SteveClassLoader loader) throws InstantiationException, IllegalAccessException {
		System.out.println("SteveClassLoaderTest Loaded by : " + SteveClassLoaderTest.class.getClassLoader());
		System.out.println("SteveClassLoader Loaded by : " + SteveClassLoader.class.getClassLoader());
		Class<?> c = loader.findClass("com.jvm.classloader.demo4.BMW");
		System.out.println("BMW is Loaded by :" + c.getClassLoader());
		Car car = (Car) c.newInstance();
		System.out.println("Car  is loaded by: "+ Car.class.getClassLoader());
		System.out.println("car  is a instance: "+ car.getClass());
		car.run();

		BMW bmw = (BMW) c.newInstance();// BMW 被 SteveClassLoader加载,findClass2 方法是被AppClassLoader 加载,看不见.
		bmw.run();
	}
}