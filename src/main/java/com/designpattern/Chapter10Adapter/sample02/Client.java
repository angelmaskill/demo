package com.designpattern.Chapter10Adapter.sample02;

public class Client {
    public static void main(String args[]) {
        DataOperation dao = (DataOperation) XMLUtil.getBean();
        dao.setPassword("sunnyLiu");
        String ps = dao.getPassword();
        String es = dao.doEncrypt(6, ps);
        System.out.println("����Ϊ��" + ps);
        System.out.println("����Ϊ��" + es);
    }
}


