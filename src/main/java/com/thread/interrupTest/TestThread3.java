package com.thread.interrupTest;

import java.util.concurrent.TimeUnit;

/**
 * 对于正在睡眠中的线程,你进行中断的时候,会抛出中断异常.同时中断标志位会被清理掉.
 * 此时,可以捕获中断异常,通过return 或者修改共享变量来达到停止线程的目的.
 * @author mayanlu
 *
 */
public class TestThread3 implements Runnable{ 
	 
    boolean stop = false; 
    public static void main(String[] args) throws Exception { 
    	/***
    	 * 正在运行中的线程,收到中断信号,并不会立刻响应中断
    	 */
        Thread thread = new Thread(new TestThread3(),"My Thread"); 
        System.out.println( "Starting thread..." ); 
        thread.start(); 
        //休眠5秒,让Thread 充分运行
        TimeUnit.SECONDS.sleep(5);
        System.out.println("线程状态:"+thread.getState().name() + ",接着 Interrupting thread..." ); 
        
        thread.interrupt(); 
        System.out.println("线程是否中断：" + thread.isInterrupted()); 
        System.out.println("Stopping application..." ); 
        
    } 
   
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				stop= true ;
			}
		}
		System.out.println("My Thread exiting under request..." ); 
	}
} 