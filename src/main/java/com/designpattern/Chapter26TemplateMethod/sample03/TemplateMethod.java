package com.designpattern.Chapter26TemplateMethod.sample03;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-7-5 11:37
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class TemplateMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            log.info("come on!");
        };
        Template template = new Template(runnable) {
            @Override
            protected void before() {
                log.info("i am iron man !");
            }

            @Override
            protected void after() {
                log.info("iron man die !");
            }
        };
        template.run();
    }

    @RequiredArgsConstructor
    static abstract class Template implements Runnable {
        private final Runnable runnable;

        protected abstract void before();

        protected abstract void after();

        @Override
        public void run() {
            before();
            runnable.run();
            after();
        }
    }
}