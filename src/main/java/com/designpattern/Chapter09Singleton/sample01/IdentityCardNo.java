package com.designpattern.Chapter09Singleton.sample01;

public class IdentityCardNo {
    private static IdentityCardNo instance = null;
    private String no;

    private IdentityCardNo() {
    }

    public static IdentityCardNo getInstance() {
        if (instance == null) {
            System.out.println("��һ�ΰ������֤�������º��룡");
            instance = new IdentityCardNo();
            instance.setIdentityCardNo("No400011112222");
        } else {
            System.out.println("�ظ��������֤����ȡ�ɺ��룡");
        }
        return instance;
    }

    private void setIdentityCardNo(String no) {
        this.no = no;
    }

    public String getIdentityCardNo() {
        return this.no;
    }

}