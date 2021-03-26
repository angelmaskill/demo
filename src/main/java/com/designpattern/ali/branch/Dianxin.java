package com.designpattern.ali.branch;

public class Dianxin extends InterPhone {

    public Dianxin(double price) {
        super(price);
    }


    public Dianxin() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public void display() {
        // TODO Auto-generated method stub
        System.out.println("电信手机开机了");
    }


}
