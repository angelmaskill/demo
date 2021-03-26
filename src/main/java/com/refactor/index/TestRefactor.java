/**
 * @(#)Test.java Copyright Oristand.All rights reserved. This software is the XXX system.
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

import junit.framework.TestCase;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-22
 */
public class TestRefactor extends TestCase {
    int _quantity = 0;
    int _itemPrice = 0;

    public void testReplaceTempWithQuery() {

        // final int basePrice = _quantity * _itemPrice;//第一次优化
        //final int basePrice = basePrice();//第二次优化
        /*double discountFactor;
        if (basePrice() > 1000)
            discountFactor = 0.95;
        else
            discountFactor = 0.98;*///第三次优化
        System.out.println(basePrice() * discountFactor());

    }

    /*
     * 第一次优化：把临时变量声明为final，检查他们是否的确只被赋值一次
     * 首先我把赋值（assignment）动作的右侧表达式提炼出来：
     */
    private int basePrice() {
        return _quantity * _itemPrice;

    }

    private double discountFactor() {
        if (basePrice() > 1000)
            return 0.95;
        else
            return 0.98;
    }
}
