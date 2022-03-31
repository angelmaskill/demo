package com.netty.test15;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * ** 回调式实现二 –Guava **
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-31 13:26
 * @description：
 * @modified By：
 * @version:
 */
public class FutureTest2 {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("执行耗时操作...");
                timeConsumingOperation();
                return 100;
            }
        });//<1>
        Futures.addCallback(future, new FutureCallback<Integer>() {
            public void onSuccess(Integer result) {
                System.out.println("计算结果:" + result);
            }

            public void onFailure(Throwable throwable) {
                System.out.println("异步处理失败,e=" + throwable);
            }
        });//<2>
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
        new CountDownLatch(1).await();
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
