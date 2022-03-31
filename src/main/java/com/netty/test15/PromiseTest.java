package com.netty.test15;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * netty-Promise 模型
 * <p>
 * 将值（future）与其计算方式（promise）分离，从而允许更灵活地进行计算，特别是通过并行化。
 * Future 表示目标计算的返回值，Promise 表示计算的方式，这个模型将返回结果和计算逻辑分离，目的是为了让计算逻辑不影响返回结果，从而抽象出一套异步编程模型。
 * 那计算逻辑如何与结果关联呢？它们之间的纽带就是 callback。
 *
 * <p>
 * Future 有两种模式：将来式和回调式。而回调式会出现回调地狱的问题，由此衍生出了 Promise 模式来解决这个问题。这才是 Future 模式和 Promise 模式的相关性。
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-25 14:09
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class PromiseTest extends TestCase {

    /**
     * Promise相当于一个容器，可以用于存放各个线程中的结果，然后让其他线程去获取该结果
     * 本例：在主线程中获取结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testNettyPromise1() throws ExecutionException, InterruptedException {
        EventLoop eventLoop = new NioEventLoopGroup().next();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);
        new Thread(() -> promise.setSuccess(50)).start();
        // 主线程从Promise中获取结果
        System.out.println(Thread.currentThread().getName() + " " + promise.get());
    }


    /**
     * Promise相当于一个容器，可以用于存放各个线程中的结果，然后让其他线程去获取该结果
     * 本例：在另外一个子线程中获取结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testNettyPromise2() throws InterruptedException {
        String value = "haha";
        NioEventLoopGroup loop = new NioEventLoopGroup();
        DefaultPromise<String> promise = new DefaultPromise<>(loop.next());
        loop.schedule(() -> {
            try {
                Thread.sleep(1000);
                log.info("thread name: " + Thread.currentThread().getName() + "任务执行中！");
                //任务结束以后，需要调用 promise.setSuccess(result) 或失败 作为通知
                promise.setSuccess("执行成功! " + value);
                return promise;
            } catch (InterruptedException ignored) {
                promise.setFailure(ignored);
            }
            return promise;
        }, 0, TimeUnit.SECONDS);
        promise.addListener(future -> {
            log.info("thread name: " + Thread.currentThread().getName() + future.get() + ", something is done");
        });
        Thread.sleep(2000);
        log.info("主线程跑完了！");
    }
}
