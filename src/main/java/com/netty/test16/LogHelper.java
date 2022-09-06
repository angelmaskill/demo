package com.netty.test16;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-5 11:55
 * @description：
 * @modified By：
 * @version:
 */
public class LogHelper {

    public static void printLog(String logContent) {
        System.out.println(getCurrentTime() + currentThreadId()  + logContent);
    }

    private static String getCurrentTime() {
        LocalTime time = LocalTime.now();
        return time.format(DateTimeFormatter.ofPattern("[HH:mm:ss.SSS]"));
    }

    private static String currentThreadId() {
        return "[" + Thread.currentThread().getName() + "|" + Thread.currentThread().getId() + "]";
    }
}