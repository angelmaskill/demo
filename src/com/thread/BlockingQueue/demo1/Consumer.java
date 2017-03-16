package com.thread.BlockingQueue.demo1;

import java.util.concurrent.BlockingQueue;

/***
 * 消费者
 **/
public class Consumer extends Thread {
	/***
	 * 利用队列存储样品
	 */
	private BlockingQueue<String> bq;

	public Consumer() {
		// TODO Auto-generated constructor stub
	}

	public Consumer(BlockingQueue<String> bq) {
		super();
		this.bq = bq;

	}

	@Override
	public void run() {

		while (true) {
			System.out.println(getName() + "消费者准备消费集合元素");
			try {

				Thread.sleep(2000);
				// 尝试取出元素，如果队列为空，则被线程阻塞
				bq.take();

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(getName() + "消费完成" + bq);
		}

	}

}