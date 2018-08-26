package com.thread.communicate.test3;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 * <pre>
 * 从上面的结果可以看到，多个线程必须等到其它线程的同一阶段的任务全部完成才能进行到下一个阶段，
 * 并且每当完成某一阶段任务时，Phaser都会执行其onAdvance方法。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class PhaserDemo {
	public static void main(String[] args) throws IOException {
		int parties = 3;
		int phases = 4;
		final Phaser phaser = new Phaser(parties) {
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("====== Phase : " + phase + " ======");
				return registeredParties == 0;
			}
		};

		for (int i = 0; i < parties; i++) {
			int threadId = i;
			Thread thread = new Thread(() -> {
				for (int phase = 0; phase < phases; phase++) {
					System.out.println(String.format("Thread %s, phase %s", threadId, phase));
					phaser.arriveAndAwaitAdvance();
				}
			});
			thread.start();
		}
	}
}