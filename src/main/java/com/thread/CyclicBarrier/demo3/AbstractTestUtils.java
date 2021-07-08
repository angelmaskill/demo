package com.thread.CyclicBarrier.demo3;

import com.thread.CyclicBarrier.demo3.model.Hr;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestUtils
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-07-05 15:11
 */
public abstract class AbstractTestUtils {
    private static final int emp_cnt = 6;

    public void doInterview() {
        ExecutorService service;
        CyclicBarrier cyclicBarrier;

        //第1面,3位hr
        int turn = 1;
        cyclicBarrier = new CyclicBarrier(emp_cnt, new Hr(3, "hr"));
        service = Executors.newCachedThreadPool();
        beginInterview(emp_cnt, service, cyclicBarrier, turn);

        //第2面,2位技术人员
        turn = 2;
        cyclicBarrier = new CyclicBarrier(emp_cnt, new Hr(2, "技术人员"));
        service = Executors.newCachedThreadPool();
        beginInterview(emp_cnt, service, cyclicBarrier, turn);

        //第3面,1位技术主管
        turn = 3;
        cyclicBarrier = new CyclicBarrier(emp_cnt, new Hr(1, "技术主管"));
        service = Executors.newCachedThreadPool();
        beginInterview(emp_cnt, service, cyclicBarrier, turn);
        service.shutdown();
    }

    protected abstract void beginInterview(int emp_cnt, ExecutorService service, CyclicBarrier cyclicBarrier, int turn);
}
