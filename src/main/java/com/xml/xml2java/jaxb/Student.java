/**
 * @(#)Student.java
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

package com.xml.xml2java.jaxb;
/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-5-27
 */
public class Student {
    
    private String id;
    private String name;
    private int age;
    public String getId() {
          return id;
   }
    public void setId(String id) {
          this. id = id;
   }
    public String getName() {
          return name;
   }
    public void setName(String name) {
          this. name = name;
   }
    public int getAge() {
          return age;
   }
    public void setAge( int age) {
          this. age = age;
   }
    public Student(String id, String name, int age) {
          super();
          this. id = id;
          this. name = name;
          this. age = age;
   }
    public Student() {
          super();
          // TODO Auto-generated constructor stub
   }
    @Override
    public String toString() {
          return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
   }
   
}
