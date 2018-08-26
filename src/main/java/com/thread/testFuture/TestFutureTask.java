/**
 * @(#)TestFutureTask.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-10-24     Administrator    Created
 **********************************************
 */

package com.thread.testFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-24
 */
public class TestFutureTask {
    /** 
     * @param args 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub 
        //准备一份工作给一个打工仔employee叫Kaiwii 
        Employee kaiwii = new Employee("kaiwii");
        //新建一个监工头inspector 
        FutureTask<Double> inspector = new FutureTask<Double>(kaiwii);
        //让kaiwii这个打工仔工作 
        System.out.println("老板发话，让kaiwii这个打工仔工作吧！");
        new Thread(inspector).start();
        System.out.println("老板开始数钱！");
        //老板一边数钱，一边命令监工inspector监视Kaiwii工作；一旦kaiwii完成工作就拿来让他检查 
        while (!inspector.isDone()) {
            System.out.println("老板数钱中……");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //老板交监工inspector将kaiwii的结果呈上来以便他验收kaiwii计算出来的结果 
        try {
            System.out.println("老板发现kaiwii的结果是：" + inspector.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}

//子线程 
class Employee implements Callable<Double> {
    String employee_name;

    private void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Employee(String employee_name) {
        setEmployee_name(employee_name);
    }

    @Override
    public Double call() throws Exception {
        System.out.println("工人" + employee_name + "说：我开始工作了！！！！");
        for (int i = 1; i <= 10; i++) {
            System.out.println("工人" + employee_name + " 第" + i + "次说：我在工作呢！！！！");
            Thread.sleep(1000);
        }
        System.out.println("工人" + employee_name + "说：我搞好了！！！！");
        return Math.random();
    }
}
