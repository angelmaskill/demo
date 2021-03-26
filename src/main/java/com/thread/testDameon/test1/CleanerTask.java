package com.thread.testDameon.test1;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {

    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;

        /**
         * 必须在start之前设置，因为线程一旦运行起来了就不能更改他的状态，这样就开 始了监控的状态，守护进程就OK了
         */
        setDaemon(true);// 设置为守护进程
    }

    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference = 0;
        boolean delete = false;

        if (deque.size() == 0) {
            return;
        }

        do {
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if (difference > 10000) {
                System.out.println("writer " + e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 10000);

        if (delete) {
            System.out.println("cleaner : the size of the deque " + deque.size());
        }
    }

    public Deque<Event> getDeque() {
        return deque;
    }

    public void setDeque(Deque<Event> deque) {
        this.deque = deque;
    }

}