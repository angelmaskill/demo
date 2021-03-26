package com.designpattern.DTDL.demo3;

import com.designpattern.DTDL.demo2.UserManager;
import com.designpattern.DTDL.demo2.UserManagerImpl;

/**
 * jdk 动态代理
 *
 * @author mayanlu
 */
public class Client {
    public static void main(String[] args) {
        /**
         * logHandler能够返回代理类对象,并进行被代理对象的方法加强.
         */
        LogHandler logHandler = new LogHandler();
        /**
         * 入参传入真实的被代理对象(与demo1客户端相比,此写法更加简洁)
         * userManager:返回的一个代理类对象
         */
        UserManager userManager = (UserManager) logHandler.newProxyInstance(new UserManagerImpl());
        //UserManager userManager=new UserManagerImpl();  
        userManager.addUser("1111", "张三");
    }
} 