package com.thread.CyclicBarrier.demo3;

import com.thread.CyclicBarrier.demo3.model.Emp;
import com.thread.CyclicBarrier.demo3.model.Hr;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * 有六个人A、B、C、D、E、F共同面试，共有三轮面试，
 * 第一轮为hr面试，
 * 第二轮为技术面试，
 * 第三轮为技术主管面试，
 *
 * 第一轮共有3位hr可同时面试，
 * 第二轮共有2位技术人员可同时面试，
 * 第三轮仅有1位技术主管可面试，
 *
 * 在单轮全部人都面试完毕后，才可进行下一轮面试，三轮面试完毕才会给出最终结果。为保证效率最高，请通过多线程思路模拟完成本次面试。
 * </pre>
 *
 * @ClassName CylicBarrierTest31
 * @Author yanlu.myl
 * @Date 2021-07-05 13:51
 */
public class CylicBarrierTest31 {
    private static final int emp_cnt = 6;

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier;

        //第1面,3位hr
        int turn = 1;
        cyclicBarrier = new CyclicBarrier(emp_cnt, new Hr(3, "hr"));
        beginInterview(emp_cnt, service, cyclicBarrier, turn);

        //第2面,2位技术人员
        turn = 2;
        cyclicBarrier = new CyclicBarrier(emp_cnt, new Hr(2, "技术人员"));
        beginInterview(emp_cnt, service, cyclicBarrier, turn);

        //第3面,1位技术主管
        turn = 3;
        cyclicBarrier = new CyclicBarrier(emp_cnt, new Hr(1, "技术主管"));
        beginInterview(emp_cnt, service, cyclicBarrier, turn);
        service.shutdown();
    }

    /**
     * 执行面试
     * 如何判断线程中的任务都执行完毕：  主线程调用await
     * 线程池可以复用（未关闭）
     *
     * @param emp_cnt       面试人数
     * @param service       线程池
     * @param cyclicBarrier 回环屏障
     * @param turn          第几轮面试
     * @throws InterruptedException
     */
    private static void beginInterview(int emp_cnt, ExecutorService service, CyclicBarrier cyclicBarrier, int turn) throws InterruptedException {
        final CountDownLatch endGate = new CountDownLatch(emp_cnt);
        for (int i = 1; i <= emp_cnt; i++) {
            service.execute(new Thread(new Emp(cyclicBarrier, i, turn, endGate)));
        }
        endGate.await();
        System.out.println(turn + "面结束！");
    }
}





