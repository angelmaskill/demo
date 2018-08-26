package com.thread.testGroup;

/*
 * 当一个线程抛出未处理异常时，JVM会首先查找该异常对应的异常处理器处理该异常；否则，JVM将会调用该线程所属的线程组对象的uncaughtException()方法来处理该异常。
 */
public class ThreadDemo2 {
	public static void main(String[] args) {
		// 设置主线程的异常处理器
		Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
		int a = 5 / 0;
		System.out.println("程序正常结束！");
	}
}

// 定义自己的异常处理器
class MyExHandler implements Thread.UncaughtExceptionHandler {
	// 实现uncaughtException方法，该方法将处理线程的未处理异常
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t + " 线程出现了异常：" + e);
	}
}