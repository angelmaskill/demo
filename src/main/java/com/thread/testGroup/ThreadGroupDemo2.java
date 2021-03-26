package com.thread.testGroup;

/**
 * 测试不指定线程组,线程的默认组
 * 通常情况下我们创建线程时可能不设置线程组，这时候创建的线程会和创建该线程的线程所在组在一个组里面。
 *
 * @author AngelMa
 */
public class ThreadGroupDemo2 {
    public static void main(String[] args) {
        printGroupInfo(Thread.currentThread());

        Thread appThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("do loop " + i);
                }
            }
        });
        appThread.setName("appThread");
        appThread.start();
        printGroupInfo(appThread);
    }

    static void printGroupInfo(Thread t) {
        ThreadGroup group = t.getThreadGroup();
        System.out.println("thread " + t.getName() + " group name is " + group.getName() + " max priority is "
                + group.getMaxPriority() + " thread count is " + group.activeCount());

        ThreadGroup parent = group;
        do {
            ThreadGroup current = parent;
            parent = parent.getParent();
            if (parent == null) {
                break;
            }

            System.out.println(current.getName() + "'s parent is " + parent.getName());
        } while (true);
        System.out.println("--------------------------");
    }
}