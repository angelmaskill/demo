package com.netty.test15;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * netty future 练习
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-25 13:14
 * @description：
 * @modified By：
 * @version:
 */
public class FutureTest1 extends TestCase {

    /**
     * Netty中的Future对象，可以通过EventLoop的sumbit()方法得到
     * <p>
     * 可以通过Future对象的get方法，阻塞地获取返回结果
     * 也可以通过getNow方法，获取结果，若还没有结果，则返回null，该方法是非阻塞的
     * 还可以通过future.addListener方法，在Callable方法执行的线程中，异步获取返回结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testNettyFuture() throws ExecutionException, InterruptedException {
        String mainThreadName = Thread.currentThread().getName();
        long l = System.currentTimeMillis();
        //获取 eventGroup 对象，方法一：比较耗时。
        EventLoop group = new NioEventLoopGroup().next();

        //方法2： 比较快速：
        // EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        System.out.println(mainThreadName + " 获取group, 主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
        Future<String> submit = group.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "aaaa";
            }
        });
        // NIO线程中异步获取结果
        submit.addListener(new GenericFutureListener<Future<? super String>>() {
            @Override
            public void operationComplete(io.netty.util.concurrent.Future<? super String> future) throws Exception {
                System.out.println("子线程:" + Thread.currentThread().getName() + "获得结果！getnow:" + future.getNow() + "运算耗时:" + (System.currentTimeMillis() - l) + "ms");
            }
        });
        // 主线程中获取结果
        System.out.println(mainThreadName + " 获取结果, 主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
        new CountDownLatch(1).await();
    }

    /**
     * 结果分析：使用了 addListener 这样的方法为一个 future 结果添加回调，从而达到“当耗时操作完成后，自行触发钩子去执行打印操作”的效果。
     * 细心的朋友会发现，主线程只耗费了不到 1s 的时间，整个过程没有被耗时操作阻塞，这才是异步编程的推荐方式：回调。
     */
    @Test
    public void testFuture() {
        long l = System.currentTimeMillis();
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        io.netty.util.concurrent.Future<Integer> f = group.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("执行耗时操作...");
                timeConsumingOperation();
                return 100;
            }
        });
        f.addListener(new FutureListener<Object>() {
            @Override
            public void operationComplete(io.netty.util.concurrent.Future<Object> objectFuture) throws Exception {
                System.out.println("计算结果:：" + objectFuture.get());
            }
        });
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
        //new CountDownLatch(1).await();
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
