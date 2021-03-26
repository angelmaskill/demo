package com.designpattern.Chapter22Memoto.sample01;


public class Client {
    public static void main(String a[]) {
        UserInfoDTO user = new UserInfoDTO();
        Caretaker c = new Caretaker();

        user.setAccount("zhangsan");
        user.setPassword("123456");
        user.setTelNo("13000000000");
        System.out.println("״̬һ��");
        user.show();
        c.setMemento(user.saveMemento());//���汸��¼
        System.out.println("---------------------------");

        user.setPassword("111111");
        user.setTelNo("13100001111");
        System.out.println("״̬����");
        user.show();
        System.out.println("---------------------------");

        user.restoreMemento(c.getMemento());//�ӱ���¼�лָ�
        System.out.println("�ص�״̬һ��");
        user.show();
        System.out.println("---------------------------");
    }
}