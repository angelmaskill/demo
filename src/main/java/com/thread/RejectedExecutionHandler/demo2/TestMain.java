package com.thread.RejectedExecutionHandler.demo2;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 控制线程池不再接受新的任务.
 *
 * @author AngelMa
 */
public class TestMain {
    public static void main(String[] args) {
        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(controller);
        System.out.printf("---------------Main: Starting.\n");
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task" + i);
            executor.submit(task);
        }

        System.out.printf("---------------Main: Shutting down the Executor.\n");
        executor.shutdown();

        System.out.printf("---------------Main: Sending another Task.\n");
        Task task = new Task("RejectedTask");
        executor.submit(task);

        System.out.println("---------------Main: End");

    }
}
