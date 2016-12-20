/**
 * @(#)CusEventListener.java
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

import java.util.EventListener;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-9-22
 */
public class CusEventListener implements EventListener {

    //事件发生后的回调方法  
    public void fireCusEvent(CusEvent e) {
        EventSourceObject eObject = (EventSourceObject) e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \"" + eObject.getName() + "\"");
    }
}