package com.thread.CyclicBarrier.demo3;

import com.thread.CyclicBarrier.demo3.model.Emp;
import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
 * @ClassName CylicBarrierTest33
 * @Author yanlu.myl
 * @Date 2021-07-05 13:51
 */
public class CylicBarrierTest33 extends AbstractTestUtils {
    private static final int emp_cnt = 6;

    @SneakyThrows
    public static void main(String[] args) {
        CylicBarrierTest33 test = new CylicBarrierTest33();
        test.doInterview();
    }

    /**
     * 执行面试
     * <p>
     * 要求在线程池中的任务都完成后才能执行后续的任务，
     * 线程池不能复用（已经关闭）
     *
     * @param emp_cnt       面试人数
     * @param service       线程池
     * @param cyclicBarrier 回环屏障
     * @param turn          第几轮面试
     * @throws InterruptedException
     */
    @Override
    protected void beginInterview(int emp_cnt, ExecutorService service, CyclicBarrier cyclicBarrier, int turn) {
        for (int i = 1; i <= emp_cnt; i++) {
            service.execute(new Thread(new Emp(cyclicBarrier, i, turn)));
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(turn + "面结束！");
    }
}





