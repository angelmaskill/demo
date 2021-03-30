/**
 * @(#)City2.java Copyright Oristand.All rights reserved. This software is the XXX system.
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
 *
 * 此时，super.name返回的值是父类成员变量的值xianfan,而此时的super.value()方法是不起作用的。
 * @author Administrator
 * @since 2015-3-13
 */
public class City2 extends Country2 {
    String name;

    String value(String name) {
        name = "Hefei";//子类的name属性被初始化为Hefei
        super.value("失败");// 父类调用此方法时，没有把自己的属性改成china，仅仅是把参数给改成了china;
        System.out.println(name);//子类的属性是Hefei;
        System.out.println(super.name);//父类的属性是xiangfan
        return name;
    }

    public static void main(String[] args) {
        City2 c = new City2();
        c.value("成功");
    }
}
