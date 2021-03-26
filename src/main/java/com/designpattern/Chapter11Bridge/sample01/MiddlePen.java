package com.designpattern.Chapter11Bridge.sample01;

public class MiddlePen extends Pen {
    public void draw(String name) {
        String penType = "�к�ë�ʻ���";
        this.color.bepaint(penType, name);
    }
}