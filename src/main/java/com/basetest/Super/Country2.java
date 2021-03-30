/**
 * @(#)Country2.java Copyright Oristand.All rights reserved.
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
public class Country2 {
    String name = "xiangfan";

    String value(String name) {
        name = "China";//没有改变父类的属性,这个name是参数，不是父类的属性。
        return name;
    }
}
