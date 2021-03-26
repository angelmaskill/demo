/**
 * @(#)RealSubject.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-7-28     Administrator    Created
 **********************************************
 */

package com.designpattern.DTDL.demo1;

/**
 ** 真实对象：定义目标操作  
 *
 * @author Administrator
 * @since 2015-7-28
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("干活");
    }

    @Override
    public void sleep() {
        // TODO Auto-generated method stub
        System.out.println("睡觉");
    }

}
