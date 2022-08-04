package com.thread.transmittableThreadLocal.demo2;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;

import static com.alibaba.ttl.TransmittableThreadLocal.Transmitter.runSupplierWithCaptured;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-7-8 17:40
 * @description：
 * @modified By：
 * @version:
 */
public class TestTtlCrr {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // ===========================================================================
        // 线程 A
        // ===========================================================================

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("value-set-in-parent");

        // (1) 抓取当前线程的所有TTL值
        final Object captured = TransmittableThreadLocal.Transmitter.capture();
        System.out.println("captured: " + captured);

        // ===========================================================================
        // 线程 B（异步线程）
        // ===========================================================================

        // (2) 在线程 B中回放在capture方法中抓取的TTL值，并返回 回放前TTL值的备份
        final Object backup = TransmittableThreadLocal.Transmitter.replay(captured);
        System.out.println("backup: " + backup);
        try {
            // 你的业务逻辑，这里你可以获取到外面设置的TTL值
            String value = context.get();
            System.out.println("Hello: " + value);
            String result = "World: " + value;
        } finally {
            // (3) 恢复线程 B执行replay方法之前的TTL值（即备份）
            TransmittableThreadLocal.Transmitter.restore(backup);
        }
    }

    private static void test2() {
        // ===========================================================================
        // 线程 A
        // ===========================================================================

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("value-set-in-parent");

        // (1) 抓取当前线程的所有TTL值
        final Object captured = TransmittableThreadLocal.Transmitter.capture();

        // ===========================================================================
        // 线程 B（异步线程）
        // ===========================================================================

        String result = runSupplierWithCaptured(captured, () -> {
            // 你的业务逻辑，这里你可以获取到外面设置的TTL值
            String value = context.get();
            System.out.println("Hello: " + value);
            return "World: " + value;
        }); // (2) + (3)
    }
}
