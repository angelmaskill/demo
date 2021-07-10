/**
 * @(#)Subject.java Copyright Oristand.All rights reserved.
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

package com.DTDL.demo1;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-7-28
 */
public interface Subject {

    /**
     * 抽象角色：声明真实对象和代理对象的共同接口 
     */
    public void doSomething();

    public void sleep();
}
