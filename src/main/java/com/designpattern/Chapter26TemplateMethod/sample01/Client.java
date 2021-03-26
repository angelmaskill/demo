package com.designpattern.Chapter26TemplateMethod.sample01;

public class Client {
    public static void main(String a[]) {
        BankTemplateMethod bank;
        bank = (BankTemplateMethod) XMLUtil.getBean();
        bank.process();
        System.out.println("---------------------------------------");
    }
}
