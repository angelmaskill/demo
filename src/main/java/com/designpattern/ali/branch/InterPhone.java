package com.designpattern.ali.branch;

/**
 * 手机
 *
 * @author AngelMa
 */
public abstract class InterPhone {
    private double price;

    public InterPhone() {
        super();
    }

    public InterPhone(double price) {
        super();
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    ;

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {

    }

    ;
}
