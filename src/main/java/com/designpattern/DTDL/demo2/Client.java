package com.designpattern.DTDL.demo2;

/**
 * 静态代理的实现.
 *
 * @author mayanlu
 */
public class Client {

    public static void main(String[] args) {
        //UserManager userManager=new UserManagerImpl();  
        UserManager userManager = new UserManagerImplProxy(new UserManagerImpl());
        userManager.addUser("1111", "张三");
    }
} 