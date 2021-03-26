package com.designpattern.Chapter11Bridge.sample01;

public class Black implements Color {
    public void bepaint(String penType, String name) {
        System.out.println(penType + "��ɫ��" + name + ".");
    }
}