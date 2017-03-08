package com.jvm.classloader.demo8;

public class Main {

	public static void main(String[] args) throws Exception {
		// loader1的父加载器为系统类加载器
		MyClassLoader loader1 = new MyClassLoader("loader1");
		loader1.setPath("D://path//lib1//");
//		loader1.setPath("D:/gitproj/demo/target/classes");

		// loader2的父加载器为loader1
		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
		loader2.setPath("D://path//lib2//");
//		loader2.setPath("D:/gitproj/demo/target/classes");

		// loader3的父加载器为根类加载器
		MyClassLoader loader3 = new MyClassLoader(null, "loader3");
		loader3.setPath("D://path//lib3//");
//		loader3.setPath("D:/gitproj/demo/target/classes");

//		test(loader1);
		System.out.println("---------------------1");
		/**
		 * 当执行loader2.loaderClass("Sample")时，先由它上层的所有父加载器尝试加载Sample类。loader1从D:/
		 * lib1/目录下成功的加载了Sample类，因此laoder1是Sample类的定义类加载器，
		 * loader1和loader2是Sample类的初始类加载器。
		 */
//		test(loader2);
		System.out.println("---------------------2");
		/**
		 * 当执行loader3.loadClass("Sample")时，先由它上层的所有父加载器尝试加载Sample类。
		 * loader3的父加载器为根类加载器，它无法加载Sample类，接着loader3从D:/lib3/目录下成功地加载了Sample类，
		 * 因此loader3是Sample类的定义类加载器即初始类加载器。
		 */
		test(loader3);
		System.out.println("---------------------3");
	}

	@SuppressWarnings("unused")
	public static void test(MyClassLoader loader) throws Exception {
		/**
		 * <pre>
		 * 没有重写loadClass,走的双亲委派,先让父类C1加载.
		 * 在通过父类加载器检查所请求的类后，此方法将被 loadClass 方法调用。默认实现抛出一个 ClassNotFoundException。
		 * </pre>
		 */
		Class<?> clazz = loader.loadClass("Sample");
		ClassLoader cl = clazz.getClassLoader();
		Object obj = clazz.newInstance();
	}
}
