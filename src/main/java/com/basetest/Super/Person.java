/**
 * @(#)Person.java Copyright Oristand.All rights reserved.
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

package com.basetest.Super;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-3-13
 */
public class Person {
    public static void prt(String s) {
        System.out.println(s);
    }

    Person() {
        prt("A Person.");
    }

    Person(String name) {
        prt("A person name is:" + name);
    }
}
