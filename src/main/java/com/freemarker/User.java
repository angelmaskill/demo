/**
 * @(#)User.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-8-26     Administrator    Created
 **********************************************
 */

package com.freemarker;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-26
 */
public class User {
    private String userName;
    private String userPassword;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User(String userName, String userPassword, int age) {
        super();
        this.userName = userName;
        this.userPassword = userPassword;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
