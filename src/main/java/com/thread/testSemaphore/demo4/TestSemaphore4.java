package com.thread.testSemaphore.demo4;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类比例子：
 *      1.相信在学生时代都去餐厅打过饭，假如有3个窗口可以打饭，同一时刻也只能有3名同学打饭。第四个人来了之后就必须在外面等着，只要有打饭的同学好了，就可以去相应的窗口了。
 *
 *      2. Semaphore相当于一个厕所，我在造的时候可以想造几个坑就造几个坑，假如现在我就造了3个坑，现在有10个人想要来上厕所，那么每次就只能3个人上，谁最先抢到谁就进去，出
 *      来了一个人后，第4个人才能进去，这个就限制了上厕所的人数了，就这个道理。每个人上厕所之前都先acquire()一下，如果有坑，就可以进入，没有就被阻塞，在外面等；上完厕所后
 *      ，会release()一下，释放一个坑出来，以保证下一个人acquire()的时候有坑。
 *
 * 业务语义：
 *      号码有限， 拿到号的有资格做某事
 *      没有拿到号的只能排队等号
 *
 * 业务场景 ：
 * <p>
 * 1、停车场容纳总停车量10。
 * 2、当一辆车进入停车场后，显示牌的剩余车位数响应的减1.
 * 3、每有一辆车驶出停车场后，显示牌的剩余车位数响应的加1。
 * 4、停车场剩余车位不足时，车辆只能在外面等待。
 *
 * @ClassName TestSemaphore4
 * @Description 测试 信号量
 * @Author yanlu.myl
 * @Date 2021-07-05 10:43
 */
public class TestSemaphore4 {
    // 最大允许停3辆车
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        test0();
        //test1();
        //test2();
    }

    /**
     * 使用线程池和信号量的话，无法控制同步。
     */
    private static void test0() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            Runnable park = () -> {
                String carName = Thread.currentThread().getName();
                //System.out.println("==== " + Thread.currentThread().getName() + " 来到停车场");
                if (semaphore.availablePermits() == 0) {
                    //System.out.println("车位不足，请 " + Thread.currentThread().getName() + " 耐心等待");
                }
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第 " + carName + " 辆车入库");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                System.out.println("第 " + carName + " 辆车出库");
            };
            executorService.execute(park);
        }
        executorService.shutdown();
    }


    private static void test1() {
        for (int i = 1; i <= 10; i++) {
            final int NO = i;
            Thread thread = new Thread(() -> {
                String carName = Thread.currentThread().getName();
                //System.out.println("==== " + Thread.currentThread().getName() + " 来到停车场");
                if (semaphore.availablePermits() == 0) {
                    System.out.println("车位不足，请 " + Thread.currentThread().getName() + " 耐心等待");
                }
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第 " + carName + " 辆车入库");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                System.out.println("第 " + carName + " 辆车出库");
            }, i + "号车");
            thread.start();
        }
    }

    private static void test2() {
        //模拟100辆车进入停车场
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        //System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                        if (semaphore.availablePermits() == 0) {
                            //System.out.println("车位不足，请" + Thread.currentThread().getName() + "耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName() + "驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, i + "号车");
            thread.start();
        }
    }
}
