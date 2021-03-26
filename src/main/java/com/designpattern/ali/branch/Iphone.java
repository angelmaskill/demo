package com.designpattern.ali.branch;

public class Iphone extends InterPhone {


    public Iphone() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Iphone(double price) {
        super(price);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        System.out.println("苹果手机开机了");
    }

}
