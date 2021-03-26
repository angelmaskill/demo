package com.thread.RejectedExecutionHandler.demo1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池异常处理类
 *
 * @author
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        // TODO Auto-generated method stub
        System.out.println("Begin exception handler-----------");
        //执行失败任务
        new Thread(task, "exception by pool").start();
        //打印线程池的对象
        System.out.println("The pool RejectedExecutionHandler = " + executor.toString());
    }
}