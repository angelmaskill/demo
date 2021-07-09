package com.thread.testJoin;

/***
 * 测试:join用法
 * Java Thread中， join() 方法是让调用该方法的主线程执行run()时暂时卡住，等run()执行完成后， 主线程再调用执行join()后面的代码
 * */
class ThreadTesterA implements Runnable {

    private int counter;

    @Override
    public void run() {
        while (counter <= 10) {
            System.out.print("Counter = " + counter + " ");
            counter++;
        }
        System.out.println();
    }
}

class ThreadTesterB implements Runnable {

    private int i;

    @Override
    public void run() {
        while (i <= 10) {
            System.out.print("i = " + i + " ");
            i++;
        }
        System.out.println();
    }
}

public class ThreadTestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread ta = new Thread(new ThreadTesterA());
        Thread tb = new Thread(new ThreadTesterB());
        ta.start();
        ta.join(); // wait ta to be finished
        tb.start();
        tb.join(); // in this program, this may be removed
    }
}
