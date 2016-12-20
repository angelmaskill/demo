/**
 * @(#)TestHunSin.java
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
 *  1     2015-8-12     Administrator    Created
 **********************************************
 */

package com.designpattern.single;

import java.util.HashSet;
import java.util.Set;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2015-8-12
 */
public class TestHunSin {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            HunSingleApp hs = new HunSingleApp();
            Thread th = new Thread(hs);
            th.start();
        }
        for (int i = 0; i < 10000; i++) {
            System.out.println("=============" + i);
        }

    }
}
