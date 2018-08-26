package com.xinnuo.se.util;

/**
 * @(#)PrinterStackMsg.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: pt_demo
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-9-12     Administrator    Created
 **********************************************
 */

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-9-12
 */
public class PrinterStackMsg {
    /*
     * 打印这个方法调用的堆栈信息
     */
    public static void printStackDetails() {
        Throwable ex = new Throwable();

        StackTraceElement[] stackElements = ex.getStackTrace();
        printDetails(stackElements);
        
    }
    
    public static void printStackDetails2(){
        StackTraceElement[] stackElements  = Thread.currentThread().getStackTrace();
        printDetails(stackElements);
    }
    
    private static void printDetails(StackTraceElement[] stackElements){
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                System.out.println("类名："+stackElements[i].getClassName());
                System.out.println("文件："+stackElements[i].getFileName());
                System.out.println("行号："+stackElements[i].getLineNumber());
                System.out.println("方法："+stackElements[i].getMethodName());
                System.out.println("-----------------------------------");
            }
        }
    }
}
