package com.thread.blocking.blockingQueue.demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) throws Exception {
		BlockingQueueTest bq = new BlockingQueueTest();
		bq.add();
		bq.addAndRemove();
	}

	private void add() throws Exception {
		BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此队列中
			bqueue.put("加入元素" + i);
			System.out.println("向阻塞队列中添加了元素:" + i);
		}
		System.out.println("程序到此运行结束，即将退出----");
	}

	private void addAndRemove() throws Exception {
		BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此队列中
			bqueue.put("" + i);
			System.out.println("向阻塞队列中添加了元素:" + i);
			if (i > 18) {
				// 从队列中获取队头元素，并将其移出队列
				System.out.println("从阻塞队列中移除元素：" + bqueue.take());
			}
		}
		System.out.println("程序到此运行结束，即将退出----");
	}
}