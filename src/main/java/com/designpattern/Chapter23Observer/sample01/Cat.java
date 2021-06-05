package com.designpattern.Chapter23Observer.sample01;

public class Cat extends MySubject {

    @Override
    public void cry() {
        System.out.println("cat: meo meo!");
        System.out.println("----------------------------");
        for (Object obs : observers) {
            ((MyObserver) obs).response();
        }
    }
}