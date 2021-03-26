package com.designpattern.Chapter14Facade.sample01;

public class Light {
    private String position;

    public Light(String position) {
        this.position = position;
    }

    public void on() {
        System.out.println(this.position + "�ƴ򿪣�");
    }

    public void off() {
        System.out.println(this.position + "�ƹرգ�");
    }
}