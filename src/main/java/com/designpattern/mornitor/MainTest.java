/**
 * @(#)MainTest.java
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
 *  1     2015-9-22     Administrator    Created
 **********************************************
 */

package com.designpattern.mornitor;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-9-22
 */
public class MainTest {
    /** 
     * @param args 
     */
    public static void main(String[] args) {
        EventSourceObject object = new EventSourceObject();
        
        CusEventListener cl=new CusEventListener(); 
        //注册监听器  
        object.addCusListener(cl);
        //触发事件  
        object.setName("eric");
    }
}
