package com.designpattern.Chapter05FactoryMethod.sample01;

public class HaierTVFactory implements TVFactory {
    public TV produceTV() {
        System.out.println("�������ӻ����������������ӻ���");
        return new HaierTV();
    }
}