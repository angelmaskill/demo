/**
 * @(#)Person.java Copyright Oristand.All rights reserved.
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
public class Person {
    private String name;
    private String sex;
    private String tel;
    private Addresses addes;

    public Person(Addresses addes, String name, String sex, String tel) {
        this.addes = addes;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public Addresses getAddes() {
        return addes;
    }

    public void setAddes(Addresses addes) {
        this.addes = addes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String toString() {
        return "Person{" + "addes=" + addes.toString() + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", tel='" + tel + '\'' + "}\n";
    }
}
