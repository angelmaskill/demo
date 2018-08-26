/**
 * @(#)Temp.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-1-8     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2014-1-8
 */
public class Temp {
    public static void main(String[] args) throws Exception {
        /*
         * String a ="1|2"; String[] b=a.split("\\|"); if(b.length>1){
         * System.out.println(b[1]); }
         */
        /*String a = "201310210000";
        String b = "201311070000";
        Date aa = convertDateStringToSQLServerDate(convertInputToDateString(a));
        Date bb = convertDateStringToSQLServerDate(convertInputToDateString(b));
        if (aa.getYear() == bb.getYear()) {
            System.out.println("true");
        }*/
        String  dateStr="201404010000";
        convertInputToDateString2(dateStr);
    }

    public static Date convertDateStringToSQLServerDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return sdf.parse(dateString);
    }

    public static String convertInputToDateString(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        Date date = sdf.parse(dateStr);
        sdf.applyPattern("yyyy-MM-dd hh:mm");
        return sdf.format(date);
    }
    
    public static void  convertInputToDateString2(String dateStr) throws ParseException {
        dateStr="201404010000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        Date date = sdf.parse(dateStr);
        Date dateNow =new Date();
        if(dateNow.before(date)){
            System.out.println("1");//出院时间
        }else{
            System.out.println("2");//入院时间
        }
        System.out.println("政策: "+date);
        System.out.println("当前："+dateNow);
    }
}
