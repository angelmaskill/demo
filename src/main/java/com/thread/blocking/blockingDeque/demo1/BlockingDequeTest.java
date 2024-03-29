package com.thread.blocking.blockingDeque.demo1;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingDequeTest {
    public static void main(String[] args) throws Exception {
        BlockingDequeTest bdq = new BlockingDequeTest();
        bdq.add();
        bdq.addAndRemove();
    }

    private void add() throws Exception {
        // TODO Auto-generated method stub
        BlockingDeque<String> bDeque = new LinkedBlockingDeque<String>(20);
        for (int i = 0; i < 30; i++) {
            // 将指定元素添加到此阻塞栈中

            bDeque.putFirst("" + i);
            System.out.println("向阻塞栈中添加了元素:" + i);
        }
        System.out.println("程序到此运行结束，即将退出----");
    }

    private void addAndRemove() throws Exception {
        // TODO Auto-generated method stub
        BlockingDeque<String> bDeque = new LinkedBlockingDeque<String>(20);
        for (int i = 0; i < 30; i++) {
            // 将指定元素添加到此阻塞栈中
            bDeque.putFirst("" + i);
            System.out.println("向阻塞栈中添加了元素:" + i);
            if (i > 18) {
                // 从阻塞栈中取出栈顶元素，并将其移出
                System.out.println("从阻塞栈中移出了元素：" + bDeque.pollFirst());
            }
        }
        System.out.println("程序到此运行结束，即将退出----");
    }
}
