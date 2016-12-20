package com.thread.testGroup;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 当创建了好几个线程的时候，很多线程的工作任务是类似或者一致的，这样我们就可以使用ThreadGroup来管理他
 * 们，ThreadGroup可以随时的获取在他里面的线程的运行状态，信息，或者一条命令关闭掉这个group里面的所有线 程，非常的简单实用
 * 
 * @author Administrator
 * 
 */
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class Result {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

public class FirstGroup implements Runnable {

	public FirstGroup(Result result) {
		this.result = result;
	}

	private Result result;

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("Thread Start " + name);
		try {
			doTask();
			result.setName(name);
		} catch (InterruptedException e) {
			System.out.printf("Thread %s: Interrupted\n", name);
			return;
		}
		System.out.println("Thread end " + name);
	}

	private void doTask() throws InterruptedException {
		Random random = new Random((new Date()).getTime());
		int value = (int) (random.nextDouble() * 100);
		System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
		TimeUnit.SECONDS.sleep(value);
	}

	public static void main(String[] args) {
		System.out.println("main thread start:");

		// 创建5个线程，并入group里面进行管理
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		FirstGroup searchTask = new FirstGroup(result);
		for (int i = 0; i < 5; i++) {
			Thread thred = new Thread(threadGroup, searchTask);
			thred.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 通过这种方法可以看group里面的所有信息
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();

		// 这样可以复制group里面的thread信息
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}

		waitFinish(threadGroup);
		// 将group里面的所有线程都给interpet
		threadGroup.interrupt();

		System.out.println("main thread end:");
	}

	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}