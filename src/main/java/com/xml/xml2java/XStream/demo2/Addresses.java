/**
 * @(#)Addresses.java
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
 *  1     2015-5-27     Administrator    Created
 **********************************************
 */

package com.xml.xml2java.XStream.demo2;

import java.util.Iterator;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-5-27
 */
public class Addresses {
    private List<Address> listAdd;

    public Addresses(List<Address> listAdd) {
        this.listAdd = listAdd;
    }

    public List<Address> getListAdd() {
        return listAdd;
    }

    public void setListAdd(List<Address> listAdd) {
        this.listAdd = listAdd;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Iterator it = listAdd.iterator(); it.hasNext();) {
            Address add = (Address) it.next();
            sb.append(add.toString());
        }
        return "Addresses{" + "listAdd=" + sb.toString() + "}\n";
    }
}
