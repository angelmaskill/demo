/**
 * @(#)EventSourceObject.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-9-22
 */
public class EventSourceObject {
    private String name;
    //监听器容器  
    private Set<CusEventListener> listener;

    public EventSourceObject() {
        this.listener = new HashSet<CusEventListener>();
        this.name = "defaultname";
    }

    //给事件源注册监听器  
    public void addCusListener(CusEventListener cel) {
        this.listener.add(cel);
    }

    //当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）  
    protected void notifies() {
        CusEventListener cel = null;
        Iterator<CusEventListener> iterator = this.listener.iterator();
        while (iterator.hasNext()) {
            cel = iterator.next();
            cel.fireCusEvent(new CusEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    //模拟事件触发器，当成员变量name的值发生变化时，触发事件。  
    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifies();
        }
    }
}