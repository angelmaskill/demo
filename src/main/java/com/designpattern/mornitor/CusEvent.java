/**
 * @(#)CusEvent.java
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

import java.util.EventObject;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-9-22
 */
public class CusEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    private Object source;//事件源  

    public CusEvent(Object source) {
        super(source);
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
