package com.designpattern.Chapter13Decorator.sample01;

public class Airplane extends Changer {
    public Airplane(Transform transform) {
        super(transform);
        System.out.println("��ɷɻ���");
    }

    public void fly() {
        System.out.println("����շ��裡");
    }
}