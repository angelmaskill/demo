/**
 * @(#)TestStatic.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-12-3     Administrator    Created
 **********************************************
 */

package com.grammar;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-12-3
 */
public class TestStatic {
    public static void main(String[] args) {
        People p1 = new Student();
        System.out.print(p1);
        p1.speak();
    }
}
