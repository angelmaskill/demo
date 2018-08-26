package com.thread.testLock;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如下是唤醒指定线程的demo 使用ReentrantLock配合多个condition来达到唤醒指定线程的目的，将唤醒目标 condition
 * 与thread 做关联，建立联系，找到thread的instance就可以找到thread的监视对象condition.
 * 
 * join方法的功能就是使异步执行的线程变成同步执行
 * 
 * @author mayanlu
 *
 */
public class NotifySpecfiedThread {

	private static final Map<Thread, Condition> notifyRelationship = new ConcurrentHashMap<Thread, Condition>();
	private static final ReentrantLock locker = new ReentrantLock(false);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final int waitThreadNumber = 10;
		for (int i = 1; i <= waitThreadNumber; i++) {
			final Thread t = new WaitThreadTest();
			notifyRelationship.put(t, locker.newCondition());
		}
		final Set<Thread> threads = notifyRelationship.keySet();
		for (final Thread t : threads) {
			t.start();
		}

		new NotifyThreadTest().join();// 等待NotifyThreadTest 线程执行完毕之后,才继续往下执行.
		System.out.println("main finished...");
	}

	static class WaitThreadTest extends Thread {
		public WaitThreadTest() {
		}

		/**
		 * notify the specified thread using different condition and lock with
		 * the same ReentrantLock instance
		 */
		public void run() {
			locker.lock();
			try {
				final Thread t = Thread.currentThread();
				final ThreadLocal<Boolean> isWait = new ThreadLocal<Boolean>();
				isWait.set(true);

				while (isWait.get()) {
					try {
						System.out.println("i am ready to wait," + " the thread name is:" + t.getName());
						final Condition c = notifyRelationship.get(this);
						c.await();
						isWait.set(false);
					} catch (InterruptedException ex) {
						System.err.println(ex.getMessage());
					}
				}
				System.out.println("i have been released, the thread name is:" + t.getName());
			} finally {
				locker.unlock();
			}

		}
	}

	/**
	 * loop all the condition instance and release the wait thread..
	 */
	static class NotifyThreadTest extends Thread {
		public NotifyThreadTest() {
			this.start();
		}

		public void run() {
			final Set<Entry<Thread, Condition>> entrySet = notifyRelationship.entrySet();
			for (final Entry<Thread, Condition> entry : entrySet) {
				final Thread t = entry.getKey();
				final Condition c = entry.getValue();

				System.out.println("i am ready to signal, " + "will be signaled thread name is:" + t.getName());
				locker.lock();
				try {
					// Wakes up one waiting thread.
					c.signal();
				} finally {
					locker.unlock();
				}
				System.out.println("i have alreay signaled the specified " + "thread:" + t.getName());
			}

		}
	}
}
