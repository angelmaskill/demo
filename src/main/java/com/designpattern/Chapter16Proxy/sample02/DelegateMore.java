package com.designpattern.Chapter16Proxy.sample02;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-7-5 11:26
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class DelegateMore {
    public static void main(String[] args) {
        Runnable target = () -> log.info("target");
        Delegate level1 = new Delegate("1", target);
        Delegate level2 = new Delegate("2", level1);
        Delegate level3 = new Delegate("3", level2);
        level3.run();
    }

    static class Delegate implements Runnable {
        private String name;
        private final Runnable runnable;

        public Delegate(String name, Runnable runnable) {
            this.name = name;
            this.runnable = runnable;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(this.name + "号工人开始干活：" + System.currentTimeMillis());
            Thread.sleep(1000);
            runnable.run();
        }
    }
}
