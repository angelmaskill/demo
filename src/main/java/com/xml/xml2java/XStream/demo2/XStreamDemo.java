/**
 * @(#)XStreamDemo.java Copyright Oristand.All rights reserved.
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

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 @需要的jar包如下：
 @xpp3_min-1.1.4c.jar
 @xstream-1.3.jar

  * Created by IntelliJ IDEA.
  * File: demo3.java
  * User: leizhimin
  * Date: 2008-3-4 14:44:03
 */
public class XStreamDemo {
    public static void main(String args[]) {
        testBean2XML();
    }

    /**
     * 生成一个Persons对象 
     *
     * @return Persons对象
     */
    public static Persons getPersons() {
        Address add1 = new Address("type1", "郑州市经三路财富广场1");
        Address add2 = new Address("type2", "郑州市经三路财富广场2");
        List<Address> addlist1 = new ArrayList<Address>();
        addlist1.add(add1);
        addlist1.add(add2);

        Address add3 = new Address("type3", "郑州市经三路财富广场3");
        Address add4 = new Address("type4", "郑州市经三路财富广场4");
        List<Address> addlist2 = new ArrayList<Address>();
        addlist2.add(add3);
        addlist2.add(add4);

        Addresses addes1 = new Addresses(addlist1);
        Addresses addes2 = new Addresses(addlist2);
        Person person1 = new Person(addes1, "6666554", "lavasoft", "man");
        Person person2 = new Person(addes2, "7777754", "yutian", "man");

        List<Person> listPerson = new ArrayList<Person>();
        listPerson.add(person1);
        listPerson.add(person2);
        Persons persons = new Persons(listPerson, "001");
        return persons;
    }

    /**
     * 利用XStream在Java对象和XML之间相互转换 
     */
    public static void testBean2XML() {
        System.out.println("将Java对象转换为xml！\n");
        Persons persons = getPersons();
        XStream xstream = new XStream();
        xstream.alias("address", Address.class);
        xstream.alias("addresses", Addresses.class);
        xstream.alias("person", Person.class);
        xstream.alias("persons", Persons.class);
        String xml = xstream.toXML(persons);
        System.out.println(xml);

        System.out.println("\n将xml转换为Java对象！");
        Persons cre_person = (Persons) xstream.fromXML(xml);
        System.out.println(cre_person.toString());
    }
}