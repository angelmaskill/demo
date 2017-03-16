/**
 * @(#)ThreadTest1.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-10-24     Administrator    Created
 **********************************************
 */

package com.thread;

/**
 * 测试:happen-before,监视器锁规则 Class description goes here.
 * 
 * @author Administrator
 * @since 2014-10-24
 */
public class ThreadTest implements Runnable {

	static Integer sum = 0;

	public synchronized static void list() {
		// synchronized
		// 代表线程同步,
		// 如果去掉.就会不同步
		sum++;
		System.out.println("我是线程" + Thread.currentThread().getName() + " sum--------->" + sum);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			// 每条线程输出的次数 如果你是10次 100个线程,如果同步,sum
			// 应该是1000
			try {
				ThreadTest.list();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) { // 创建100个线程
			ThreadTest line = new ThreadTest();
			Thread t = new Thread(line, i + "");
			t.start();

		}
	}

}
