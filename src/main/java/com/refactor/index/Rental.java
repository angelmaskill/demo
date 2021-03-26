/**
 * @(#)Rental.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
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

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-22
 */
public class Rental {
    private Movie _movie; // 影片
    private int _daysRented; // 租期

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    double getCharge() { // 计算㆒笔租片费用
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR: // 普通片
                result += 2;
                if (getDaysRented() > 2)
                    result += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE: // 新片
                result += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS: // 儿童片
                result += 1.5;
                if (getDaysRented() > 3)
                    result += (getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }
}
