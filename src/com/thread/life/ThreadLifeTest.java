package com.thread.life;

import java.lang.Thread.State;

public class ThreadLifeTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Thread a = new Thread(new ThreadA(), "a 线程");
		Thread b = new Thread(new ThreadB(), "b 线程");
		long runtime = System.currentTimeMillis() - start;

		a.start();
		b.start();
		try {
			/**
			 * a.join(); 
			 * b.join(); 
			 * System.out.println(a.getState());
			 */
			while (true) {
				if (a.getState().equals(State.TERMINATED)) {
					System.out.println("A DIED");
					break;
				} else {
					Thread.sleep(10);
					System.out.println("axxxxxxxxxxxx");
				}

			}
			while (true) {
				if (b.getState().equals(State.TERMINATED)) {
					System.out.println("b DIED");
					break;
				} else {
					System.out.println("b00000000000000");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " " + runtime + " funish ");
		Thread.currentThread().interrupt();
		if (Thread.currentThread().isInterrupted() != false) {
			Thread.currentThread().stop();
		}
		// System.exit(0);

	}
}

class ThreadA extends Thread {
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				Thread.currentThread().sleep(2000);
				System.out.println(Thread.currentThread().getName() + i + "is running");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadB extends Thread {
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				Thread.currentThread().sleep(1000);
				System.out.println(Thread.currentThread().getName() + i + "is running");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}