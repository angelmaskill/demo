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
public class CustomerRefactor {
    private String _name; // 姓名
    private Vector _rentals = new Vector(); // 租借记录

    public CustomerRefactor(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

   /* public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();
            thisAmount = each.getCharge(); // 计算㆒笔租片费用
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }*/

    /*
     * 第一次重构：把计算方法单独抽取
     */
    /*private double amountFor(Rental each) { // 计算㆒笔租片费用
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
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
        return thisAmount;
    }*/
    
    /*
     * 第二次重构：修改变量的名称
     * 好的代码应该清楚表达出自己的功能，
        变量名称是代码清晰的关键
     */
    /*private double amountFor(Rental aRental) { // 计算㆒笔租片费用
        double result = 0;
        switch (aRental.getMovie().getPriceCode()) {
        case Movie.REGULAR: // 普通片
            result += 2;
            if (aRental.getDaysRented() > 2)
                result += (aRental.getDaysRented() - 2) * 1.5;
            break;
        case Movie.NEW_RELEASE: // 新片
            result += aRental.getDaysRented() * 3;
            break;
        case Movie.CHILDRENS: // 儿童片
            result += 1.5;
            if (aRental.getDaysRented() > 3)
                result += (aRental.getDaysRented() - 3) * 1.5;
            break;
        }
        return result;
    }*/
    
    /*
     * 第三次重构：把amountFor函数搬家：此函数跟customer无关。应当作为租赁对象的行为。
     * 重命名为getCharge()
     * 为了这么做，我要运用Move Method（142）
     */
    
    private double amountFor(Rental aRental) { // 计算㆒笔租片费用
       return aRental.getCharge();
    }
    
    /*
     * 第四次重构：statement thisAmount 如今变成多余了，去除局部变量
     * Replace Temp with Query（120）把thisAmount 除去。
     */
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            //double thisAmount = 0;被重构行
            Rental each = (Rental) rentals.nextElement();
            //thisAmount = each.getCharge(); // 计算㆒笔租片费用被重构行
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";//被重构行
            totalAmount += each.getCharge();//被重构行
        }
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}
