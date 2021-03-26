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

package com.xml.xml2java.XStream.demo1;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-5-27
 */

public class Person {

    private int idCard;
    private String name;
    private int age;

    public Person() {
    }

    public Person(int idCard, String name, int age) {
        super();
        this.idCard = idCard;
        this.name = name;
        this.age = age;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "person{" + "idCard='" + idCard + '\'' + ", name='" + name + '\'' + ", age='" + age + '\'' + "}\n";
    }
}
