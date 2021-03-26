package com.designpattern.Chapter16Proxy.sample01;

public class RealPermission implements AbstractPermission {
    public void modifyUserInfo() {
        System.out.println("�޸��û���Ϣ��");
    }

    public void viewNote() {
    }

    public void publishNote() {
        System.out.println("����������");
    }

    public void modifyNote() {
        System.out.println("�޸ķ������ݣ�");
    }

    public void setLevel(int level) {
    }
}