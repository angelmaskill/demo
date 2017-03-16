package com.annotation.demo4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TimerUtil {

	public void getTime() {
		// 获取当前类型名字
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		System.out.println("current className(expected): " + className);
		try {
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			Method[] methods = c.getDeclaredMethods();
			for (Method m : methods) {
				// 判断该方法是否包含Timer注解
				if (m.isAnnotationPresent(Timer.class)) {
					m.setAccessible(true);
					long start = System.currentTimeMillis();
					// 执行该方法
					m.invoke(obj);
					long end = System.currentTimeMillis();
					System.out.println(m.getName() + "() time consumed: " + String.valueOf(end - start) + "\\\\n");
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	

}