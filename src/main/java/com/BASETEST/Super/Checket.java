/**
 * @(#)Checket.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-3-13     Administrator    Created
 **********************************************
 */

package com.BASETEST.Super;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-3-13
 */
public class Checket extends Base {

    public Checket() {
        //super();//先执行父亲的构造函数，后执行自己的构造函数;不写默认执行父亲的构造函数
        System.out.println("Checket");
    }

    public static void main(String[] args) {
        Checket c = new Checket();
    }
}
