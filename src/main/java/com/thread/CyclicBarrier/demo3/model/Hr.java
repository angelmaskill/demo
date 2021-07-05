package com.thread.CyclicBarrier.demo3.model;

/**
 * @ClassName Hr
 * @Description 面试官信息
 * @Author yanlu.myl
 * @Date 2021-07-05 14:33
 */
public class Hr implements Runnable {
    private int no;
    private String type;

    public Hr(int no, String type) {
        this.no = no;
        this.type = type;
    }

    @Override
    public void run() {
        for (int i = 1; i <= no; i++) {
            System.out.println(i + "号" + type + "进入面试考场！");
        }
    }
}