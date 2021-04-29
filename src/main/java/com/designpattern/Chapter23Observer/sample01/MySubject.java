package com.designpattern.Chapter23Observer.sample01;

import java.util.ArrayList;

/**
 * 主题中维护了一个订阅者列表
 */
public abstract class MySubject {
    protected ArrayList observers = new ArrayList();

    // 绑定
    public void attach(MyObserver observer) {
        observers.add(observer);
    }

    // 解绑
    public void detach(MyObserver observer) {
        observers.remove(observer);
    }

    // 主题的动作:哭
    public abstract void cry();
}