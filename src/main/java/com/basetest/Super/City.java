/**
 * @(#)City.java Copyright Oristand.All rights reserved. This software is the XXX system.
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

package com.basetest.Super;

/**
 * 在Java中，有时还会遇到子类中的成员变量或方法与超类（有时也称父类）中的成员变量或方法同名。
 * 因为子类中的成员变量或方法名优先级高，所以子类中的同名成员变量或方法就隐藏了超类的成员变量或方法，
 * 但是我们如果想要使用超类中的这个成员变量或方法，就需要用到super.
 *
 * @author Administrator
 * @since 2015-3-13
 */


public class City extends Country {
    String name;

    void value() {
        name = "Hefei";
        super.value();// 调用了父类的方法，把父类的属性初始化。不调用此方法时，super.name返回的是父类的成员变量尚未被初始化的值
        System.out.println(name);//这里调用的是子类中的属性，已经被赋值Hefei
        System.out.println(super.name);//父类的value方法已经把父类属性给覆盖为China。
    }

    public static void main(String[] args) {
        City c = new City();
        c.value();
    }
}
