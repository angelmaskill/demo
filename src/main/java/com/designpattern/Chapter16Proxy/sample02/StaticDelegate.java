package com.designpattern.Chapter16Proxy.sample02;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-7-5 11:22
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class StaticDelegate {

    public static void main(String[] args) throws Exception {
        new RunnableDelegate(() -> log.info("Hello World!")).run();
    }

    @Slf4j
    @RequiredArgsConstructor
    private static final class RunnableDelegate implements Runnable {

        private final Runnable runnable;

        @Override
        public void run() {
            try {
                log.info("Before run...");
                runnable.run();
                log.info("After run...");
            } finally {
                log.info("Finally run...");
            }
        }
    }
}