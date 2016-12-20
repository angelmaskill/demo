package com.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class TestRunnable implements Runnable {

	public void run() {

		try {

			Thread.sleep(1000);// 守护线程阻塞1秒后运行

			File f = new File("daemon.txt");

			FileOutputStream os = new FileOutputStream(f, true);

			os.write("daemon".getBytes());

		}

		catch (IOException e1) {

			e1.printStackTrace();

		}

		catch (InterruptedException e2) {

			e2.printStackTrace();
		}

	}

}

public class DameonThread2 {

	public static void main(String[] args) throws InterruptedException

	{

		Runnable tr = new TestRunnable();
		Thread thread = new Thread(tr);
		thread.setDaemon(true); // 设置守护线程
		thread.start(); // 开始执行分进程
		System.out.println("主线程开始执行");

	}

}
