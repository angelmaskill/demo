package com.designpattern.Chapter10Adapter.sample01;

public class DogAdapter extends Dog implements Robot {
    public void cry() {
        System.out.print("������ģ�£�");
        super.wang();
    }

    public void move() {
        System.out.print("������ģ�£�");
        super.run();
    }
}