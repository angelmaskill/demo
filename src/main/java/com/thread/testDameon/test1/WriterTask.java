package com.thread.testDameon.test1;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriterTask implements Runnable {

	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	// 这个为双向队列
	private Deque<Event> deque;

	public Deque<Event> getDeque() {
		return deque;
	}

	public void setDeque(Deque<Event> deque) {
		this.deque = deque;
	}

	/**
	 * 创建100个事件,总共需要200秒钟
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event();
			event.setDate(new Date());
			event.setEvent("The Thread " + Thread.currentThread().getName() + " has generated a event");
			deque.addFirst(event);
			try {
				/**
				 * 创建事件的线程,在休眠的时候,daemon线
				程就会占用CPU time 来运行清理建立好的事件，这样就会让队列一直维护在25-30左右
				*/
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}