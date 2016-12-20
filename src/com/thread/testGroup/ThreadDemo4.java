package com.thread.testGroup;

/***
 * 一共有5个线程，这5个线程除了main是我们自己代码run所在的线程， 其他都是虚拟机启动的线程。是不是出乎你的意外了？
 * 
 * @author AngelMa
 *
 */
public class ThreadDemo4 {
	public static void main(String[] args) {
		ThreadGroup g = Thread.currentThread().getThreadGroup();
		while (g != null) {
			ThreadGroup temp = g.getParent();
			if (temp == null) {
				break;
			}
			g = temp;
		}

		// 现在g就是跟线程组
		System.out.println("active count is " + g.activeCount());

		Thread[] all = new Thread[g.activeCount()];
		g.enumerate(all);
		for (Thread t : all) {
			System.out.println(t.getName());
		}
	}
}