/**
 * @(#)Persons.java
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

package com.xml.xml2java.XStream.demo1;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-5-27
 */

import java.util.Iterator;
import java.util.List;

public class Persons {

    private List<Person> listPerson;

    public Persons() {
    }

    public Persons(List<Person> listPerson) {
        super();
        this.listPerson = listPerson;
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        for (Iterator it = listPerson.iterator(); it.hasNext();) {
            Person p = (Person) it.next();
            sb.append(it.toString());
        }
        return "persons{" + "listPerson=" + sb.toString() + "}\n";

    }
}
