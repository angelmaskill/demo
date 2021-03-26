package com.thread.communicate.test4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * 在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()，
 * 传统线程的通信方式，Condition都可以实现，
 * 这里注意，Condition是被绑定到Lock上的，要创建一个Lock的Condition必须用newCondition()方法。
 * </pre>
 *
 * @author mayanlu
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        final Business business = new Business();
        /**
         * 子线程运行
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    business.sub(3);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
        /**
         * 主线程运行
         */
        business.main(10);
    }

}

class Business {
    private volatile boolean bool = true;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public/* synchronized */void main(int loop) throws InterruptedException {
        lock.lock();
        System.out.println("主线程拿到锁了..");

        try {
            /**
             * 当bool 为真,主线程需要进入等待队列等待.
             */
            System.out.println("开关设定,一开始就要求主线程 !" + (bool == true ? "停工" : "开工"));
            while (bool) {
                System.out.println("主线程释放锁,进入等待区,主线程在等待一个信号把自己叫醒!");
                condition.await();// this.wait();
            }
            /**
             * 当bool为假的时候,打印当前循环的到第几轮
             */

            for (int i = 0; i <= loop; i++) {
                System.out.println("主线程  " + " loop of " + i);
                Thread.sleep(300);
            }
            /**
             * 主线程要停止干活
             */
            bool = true;
            /**
             * 主线程停止干活之前,叫醒子线程
             */
            condition.signal();// this.notify();
            System.out.println("醒醒吧,子线程");
        } finally {
            lock.unlock();
        }

    }

    public/* synchronized */void sub(int loop) throws InterruptedException {
        lock.lock();
        System.out.println("子线程拿到锁了..");
        try {
            System.out.println("开关设定,一开始就要求子线程!" + (bool == false ? "停工" : "开工"));
            while (!bool) {
                System.out.println("子线程释放锁,进入等待区,子线程在等待一个信号把自己叫醒!");
                condition.await();// this.wait();
            }
            /**
             * 子线程从外部,被开关唤醒.开始工作
             */
            for (int i = 0; i <= loop; i++) {
                System.out.println("子线程   " + " loop of " + i);
                Thread.sleep(200);
            }
            /**
             * 子线程停止干活,准备进入等待区.
             */
            bool = false;
            /**
             * 子线程停止干活之前,叫醒别人.
             */
            condition.signal();// this.notify();
            System.out.println("醒醒吧,主线程");
        } finally {
            lock.unlock();
        }
    }
}
