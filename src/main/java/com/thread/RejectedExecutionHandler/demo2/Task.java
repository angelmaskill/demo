package com.thread.RejectedExecutionHandler.demo2;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	private String name;

	public Task(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("task " + name + "is Starting ");
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n", name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task %s: Ending\n", name);

	}

	public String toString() {
		return name;
	}

}
