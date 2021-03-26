package com.designpattern.Chapter26TemplateMethod.sample01;

public abstract class BankTemplateMethod {
    public void takeNumber() {
        System.out.println("ȡ���Ŷӡ�");
    }

    public abstract void transact();

    public void evaluate() {
        System.out.println("�������֡�");
    }

    public void process() {
        this.takeNumber();
        this.transact();
        this.evaluate();
    }
} 