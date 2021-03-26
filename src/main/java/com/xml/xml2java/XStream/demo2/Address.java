/**
 * @(#)Address.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
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

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-5-27
 */

public class Address {
    private String addType;
    private String place;

    public Address(String addType, String place) {
        this.addType = addType;
        this.place = place;
    }

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String toString() {
        return "Address{" + "addType='" + addType + '\'' + ", place='" + place + '\'' + "}\n";
    }
}
