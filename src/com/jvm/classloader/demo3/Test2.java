/**
 * @(#)Test2.java
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-3
 */
public class Test2 {
	static {
		System.out.println("静态初始化块执行了！");
	}

	@Test
	public void test() throws Exception {
		class A {
			public String s = new Date().toLocaleString();

			public String toString() {
				return s;
			}
		}
		;

		List<A> sa = new ArrayList<A>();
		sa.add(new A());
		List<A> sb = new ArrayList<A>();
		for (A a : sa) {
			sb.add(a);
			a = null;
		}

		for (A a : sb) {
			a = null;
		}
		System.out.println(sa);
		// sa = null;
		// sa.clear();
		System.out.println(sb);

	}
}
