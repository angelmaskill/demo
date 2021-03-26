package com.designpattern.ali.calcPric;

import com.designpattern.ali.branch.Iphone;
import com.designpattern.ali.branch.Meizu;
import com.designpattern.ali.charge.Icharge;
import com.designpattern.ali.charge.Liantong;
import com.designpattern.ali.os.PhoneWithOs;
import com.designpattern.ali.os.Yunos;

public class Calculator {
    private Icharge charge;

    public Icharge getCharge() {
        return charge;
    }

    public void setCharge(Icharge charge) {
        this.charge = charge;
    }

    public void calc(PhoneWithOs phone) {
        if (phone instanceof Yunos && phone.interPhone instanceof Meizu) {
            phone.setPrice(phone.getPrice() * (1 - 0.01));
            System.out.println("魅族,加载 OS 系统,立减1%");
        }

        if (phone.interPhone instanceof Iphone) {
            phone.setPrice(phone.getPrice() - 5);
            System.out.println("苹果手机将优惠5元");
        }

        if (charge instanceof Liantong) {
            phone.setPrice(phone.getPrice() * (1 - 0.02));
            System.out.println("中国电信充值则所有类型的手机优惠2%");
        }

    }
}
