/**
 * @(#)HunSingleApp.java Copyright Oristand.All rights reserved. This software is the XXX system.
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

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-12
 */
public class HunSingleApp implements Runnable {


    public void run() {
        HungrySingleton hs = HungrySingleton.getInstance();
        System.out.println("创建实例的引用：" + hs);

    }


}
