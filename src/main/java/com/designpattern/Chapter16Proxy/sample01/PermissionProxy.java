package com.designpattern.Chapter16Proxy.sample01;

public class PermissionProxy implements AbstractPermission {
    private RealPermission permission = new RealPermission();
    private int level = 0;

    public void modifyUserInfo() {
        if (0 == level) {
            System.out.println("�Բ�����û�и�Ȩ��!");
        } else if (1 == level) {
            permission.modifyUserInfo();
        }
    }

    public void viewNote() {
        System.out.println("�鿴���ӣ�");
    }

    public void publishNote() {
        if (0 == level) {
            System.out.println("�Բ�����û�и�Ȩ��!");
        } else if (1 == level) {
            permission.publishNote();
        }
    }

    public void modifyNote() {
        if (0 == level) {
            System.out.println("�Բ�����û�и�Ȩ��!");
        } else if (1 == level) {
            permission.modifyNote();
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }
}