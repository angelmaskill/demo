package com.thread.ProductAndConsumer.demo3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.thread.BlockingQueue.demo1.Consumer;
import com.thread.BlockingQueue.demo1.Producer;

/**
 * 采用BlockingQueue实现
 * 
 * @author AngelMa
 *
 */
public class Test {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
		// BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		// 不设置的话，LinkedBlockingQueue默认大小为Integer.MAX_VALUE

		// BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);

		Consumer consumer = new Consumer(queue);
		Producer producer = new Producer(queue);
		for (int i = 0; i < 5; i++) {
			new Thread(producer, "Producer" + (i + 1)).start();

			new Thread(consumer, "Consumer" + (i + 1)).start();
		}
	}
}