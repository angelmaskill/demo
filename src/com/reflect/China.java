/**
 * @(#)China.java
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
 *  1     2015-8-18     Administrator    Created
 **********************************************
 */

package com.reflect;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-18
 */
interface China {
	public static final String name = "Rollen";
	public static int age = 20;

	public void sayChina();

	public void sayHello(String name, int age);
}

class Person implements China {
	public Person() {

	}

	public Person(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public void sayChina() {
		System.out.println("hello ,china");
	}

	@Override
	public void sayHello(String name, int age) {
		System.out.println(name + "  " + age);
	}

	private String sex;
}

class hello {
	public static void main(String[] args) {
		Class<?> demo = null;
		try {
			demo = Class.forName("Reflect.Person");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 保存所有的接口
		Class<?> intes[] = demo.getInterfaces();
		for (int i = 0; i < intes.length; i++) {
			System.out.println("实现的接口   " + intes[i].getName());
		}
	}
}