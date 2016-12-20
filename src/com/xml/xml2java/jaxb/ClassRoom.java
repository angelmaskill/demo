/**
 * @(#)ClassRoom.java
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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClassRoom {
       private String classid;
       private String classname;
       private List<Student> list= new ArrayList<Student>();
       public String getClassid() {
             return classid;
      }
       public void setClassid(String classid) {
             this. classid = classid;
      }
       public String getClassname() {
             return classname;
      }
       public void setClassname(String classname) {
             this. classname = classname;
      }
       public List<Student> getList() {
             return list;
      }
       public void setList(List<Student> list) {
             this. list = list;
      }
       public ClassRoom(String classid, String classname, List<Student> list) {
             super();
             this. classid = classid;
             this. classname = classname;
             this. list = list;
      }
       public ClassRoom() {
             super();
             
      }
       @Override
       public String toString() {

            StringBuffer sb= new StringBuffer();
            
            sb.append( "classid="+ this. classid+ "   ");
            sb.append( "classname"+ this. classname+ " ");
             for (Student st : list) {
                  sb.append(st.toString());
            }
             return sb.toString();
      }

}