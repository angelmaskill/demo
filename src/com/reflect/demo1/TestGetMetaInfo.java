package com.reflect.demo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***
 * 此类用于测试,在方法内部: 获取到当前类名 ;执行的是哪个方法; 执行到了第几行.
 * 
 * @author mayanlu
 *
 */

public class TestGetMetaInfo {
	public static void main(String[] args) {
//		getMethod();
//		getMethodInfo("java.util.HashSet");
		getAll();
	}

	private static void getMethod() {
		// 获得当前类名
		String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
		// 获得当前方法名
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		// 获取当前行号
		int lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber();
		System.out.println("class name: " + clazz + "; Method Name: " + method + ";lineNumber : " + lineNumber);
	}

	/**
	 * 传入全类名获得对应类中所有方法名和参数名
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private static void getMethodInfo(String pkgName) {
		try {
			Class clazz = Class.forName(pkgName);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				System.out.println("方法名称:" + methodName);
				Class<?>[] parameterTypes = method.getParameterTypes();
				for (Class<?> clas : parameterTypes) {
					String parameterName = clas.getName();
					System.out.println("参数名称:" + parameterName);
				}
				System.out.println("*****************************");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private static void getAll() {
		try {
			Class clazz = Class.forName("com.reflect.demo1.TestGetMetaInfo");
			System.out.println("------获取类名-----------");
			System.out.println(clazz.toString());// 获取类的完整名
			System.out.println(clazz.getSimpleName());// 获取类名
			System.out.println("------获取类中的构造方法-----------");
			// 获得所有构造方法
			Constructor[] cons = clazz.getDeclaredConstructors();
			for (int i = 0; i < cons.length; i++) {
				System.out.println("所有构造方法之" + cons[i].getName());
				// 方法修饰符
				System.out.println("修饰符类型：" + cons[i].getModifiers());
			}
			// 获得所有公共构造方法
			Constructor[] cons1 = clazz.getConstructors();
			for (int i = 0; i < cons1.length; i++) {
				System.out.println("公共构造方法之" + cons1[i].getName() + "--");
				System.out.println("修饰符类型：" + cons1[i].getModifiers());
			}
			System.out.println("-----获取类中定义的方法------------");
			Method[] mths = clazz.getDeclaredMethods();
			for (int i = 0; i < mths.length; i++) {
				System.out.println("所有方法之" + mths[i].getName());
				System.out.println("修饰符类型：" + mths[i].getModifiers());
			}
			Method[] mths1 = clazz.getMethods();
			for (int i = 0; i < mths1.length; i++) {
				System.out.println("公有方法之" + mths1[i].getName());
				System.out.println("修饰符类型：" + mths1[i].getModifiers());
			}
			System.out.println("-------获取所有的属性------------");
			Field[] fls = clazz.getFields();
			for (int i = 0; i < fls.length; i++) {
				System.out.println("公有属性之" + fls[i].getName());
				System.out.println("修饰符类型：" + fls[i].getModifiers());
			}
			Field[] fls1 = clazz.getDeclaredFields();
			for (int i = 0; i < fls1.length; i++) {
				System.out.println("所有属性之" + fls1[i].getName());
				System.out.println("修饰符类型：" + fls1[i].getModifiers());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
