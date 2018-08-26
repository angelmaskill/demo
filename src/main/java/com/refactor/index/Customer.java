/**
 * @(#)Customer.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-10-22     Administrator    Created
 **********************************************
 */

package com.refactor.index;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2014-10-22
 */
public class Customer {
    private String _name; // 姓名
    private Vector _rentals = new Vector(); // 租借记录

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0; // 总消费金额
        int frequentRenterPoints = 0; // 常客积点
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement(); // 取得㆒笔租借记录
            // determine amounts for each line
            switch (each.getMovie().getPriceCode()) { // 取得影片出租价格
            case Movie.REGULAR: // 普通片
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE: // 新片
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS: // 儿童片
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
            }
            // add frequent renter points（累加常客积点）
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1))
                frequentRenterPoints++;
            // show figures for this rental（显示此笔租借数据）
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines（结尾打印）
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}
