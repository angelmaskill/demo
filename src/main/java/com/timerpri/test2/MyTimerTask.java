package com.timerpri.test2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *
 * Timer将等待它完成，一旦完成，它将再次从队列开始下一个任务。
 * 可以创建Java Timer对象以将相关任务作为守护程序线程运行。
 * Timer类的cancel()方法用于终止计时器并丢弃任何计划任务，但它不会干扰当前正在执行的任务并让它完成。
 * 如果计时器作为守护程序线程运行，无论我们是否取消它，它将在所有用户线程完成执行后立即终止。
 *
 * Timer类包含几个schedule()方法，用于安排任务在给定日期或延迟一段时间后运行一次。有几个scheduleAtFixedRate()方法以一定的间隔定期运行任务。
 * 在使用Timer调度任务时，应该确保时间间隔超过正常的线程执行，否则任务队列大小将继续增长，最终任务将始终执行。
 *
 * 参考：https://www.yiibai.com/java/java-timer-timertask-example.html
 *
 * </pre>
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-4-18 15:23
 * @description：
 * @modified By：
 * @version:
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Timer task started at:" + new Date());
        completeTask();
        System.out.println("Timer task finished at:" + new Date());
    }

    /**
     * 跑一个任务需要  1s
     */
    private void completeTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TimerTask timerTask = new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        //跑完一个任务之后，间隔x s ，执行下一个任务
        timer.scheduleAtFixedRate(timerTask, 0, 5 * 1000);
        System.out.println("TimerTask started");

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 12秒之后，取消timer
        timer.cancel();
        System.out.println("TimerTask cancelled");
    }
}

