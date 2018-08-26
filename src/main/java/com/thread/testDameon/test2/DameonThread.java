package com.thread.testDameon.test2;

import java.io.IOException;

/***
 * 守护线程在没有用户线程可服务时自动离开.它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。
 * 如果User Thread全部撤离，那么Daemon Thread也就没啥线程好服务的了，所以虚拟机也就退出了。 
 * @author mayanlu
 *
 */
public class DameonThread extends Thread {

	public DameonThread() {
	}

	/**
	 * 线程的run方法，它将和其他线程同时运行
	 */
	public void run() {
		while(true){
			
			try {
				System.out.println("一直监控...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。
	 * @param args
	 */
	public static void main(String[] args) {
		DameonThread test = new DameonThread();
		test.setDaemon(true);
		test.start();
		System.out.println("isDaemon = " + test.isDaemon());
		try {
			System.in.read(); // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
