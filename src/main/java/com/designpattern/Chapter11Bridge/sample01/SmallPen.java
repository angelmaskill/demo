package com.designpattern.Chapter11Bridge.sample01;

public class SmallPen extends Pen {
    public void draw(String name) {
        String penType = "С��ë�ʻ���";
        this.color.bepaint(penType, name);
    }
}