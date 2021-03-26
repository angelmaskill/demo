package com.designpattern.Chapter20Iterator.sample01;

public class Client {
    public static void display(Television tv) {
        TVIterator i = tv.createIterator();
        System.out.println("���ӻ�Ƶ����");
        while (!i.isLast()) {
            System.out.println(i.currentChannel().toString());
            i.next();
        }
    }

    public static void reverseDisplay(Television tv) {
        TVIterator i = tv.createIterator();
        i.setChannel(5);
        System.out.println("����������ӻ�Ƶ����");
        while (!i.isFirst()) {
            i.previous();
            System.out.println(i.currentChannel().toString());
        }
    }

    public static void main(String a[]) {
        Television tv;
        tv = (Television) XMLUtil.getBean();
        display(tv);
        System.out.println("--------------------------");
        reverseDisplay(tv);
    }
}