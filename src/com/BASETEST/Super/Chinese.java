/**
 * @(#)Chinese.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
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
 * 
 * super后加参数的是用来调用父类中具有相同形式的构造函数，如1和2处。
 * this后加参数则调用的是当前具有相同参数的构造函数
 * @author Administrator
 * @since 2015-3-13
 */
public class Chinese extends Person {
    Chinese() {
        super(); // 调用父类构造函数（1）
        prt("A chinese.");// (4)
    }

    Chinese(String name) {
        super(name);// 调用父类具有相同形参的构造函数（2）
        prt("his name is:" + name);
    }

    Chinese(String name, int age) {
        this(name);// 调用当前具有相同形参的构造函数（3）
        prt("his age is:" + age);
    }

    public static void main(String[] args) {
        Chinese cn = new Chinese();
        cn = new Chinese("kevin");
        cn = new Chinese("kevin", 22);
    }
}
