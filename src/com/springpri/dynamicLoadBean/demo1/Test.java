package com.springpri.dynamicLoadBean.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws Exception, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:com/springpri/dynamicLoadBean/demo1/data-access-config.xml");
		DynamicLoadBean dynamicBeanLoad = (DynamicLoadBean) ctx.getBean("dynamicLoadBean");
		printWapper(ctx, "11");
		printMagazine(ctx, "22");
		dynamicBeanLoad.loadBean("classpath:com/springpri/dynamicLoadBean/demo1/data-access-dynamicConfig.xml");
		printMagazine(ctx, "33");
		printWapper(ctx, "44");

	}

	static void printMagazine(ClassPathXmlApplicationContext ctx, String name) throws Exception, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		/**
		 * 方法1:此方法不能成功打印.
		 */
//		MagazineService magezineService = (MagazineService) ctx.getBean("magazineService");
//		magezineService.print();

		/**
		 * 方法2:此方法可以成功打印.
		 */
		Object magezineService = ctx.getBean("magazineService");
		Class clazz = magezineService.getClass();
		Method m1 = clazz.getDeclaredMethod("setName", String.class);
		m1.invoke(magezineService, name);
		tryInvoke(magezineService);
	}

	private static void printWapper(ClassPathXmlApplicationContext ctx, String name) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Msg msg = (Msg) ctx.getBean("msg");
		HashMap map = msg.getProtocolMap();
		Object magezineService = map.get("01");
		Class clazz = magezineService.getClass();
		Method m1 = clazz.getDeclaredMethod("setName", String.class);
		m1.invoke(magezineService, name);
		tryInvoke(magezineService);
	}

	private static void tryInvoke(Object bean) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> paramTypes[] = new Class[0];
		Method method = bean.getClass().getDeclaredMethod("print", paramTypes);
		Object paramValues[] = new Object[0];
		method.invoke(bean, paramValues);

	}

}
