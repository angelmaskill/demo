package com.thread.testLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPri {
	public static void main(String[] args) {
		final ReentrantLock reloc = new ReentrantLock();
		final Condition condition = reloc.newCondition();

		new Thread(new Runnable() {
			public void run() {
				reloc.lock();
				System.out.println(Thread.currentThread().getName() + "要等一个新信号");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "拿到一个信号");
				System.out.println(Thread.currentThread().getName() + "doing something...");
				reloc.unlock();
			}
		}, "等代者").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				reloc.lock();
				System.out.println(Thread.currentThread().getName() + "拿到锁了!");
				try {
					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName() + "doing something...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				condition.signalAll();
				System.out.println(Thread.currentThread().getName() + "发了一个信号,唤醒大家!");
				reloc.unlock();
			}
		}, "唤醒者").start();
	}
}
