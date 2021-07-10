package com.DTDL.demo4;

/**
 * jdk 动态代理
 *
 * @author mayanlu
 */
public class Client {

    public static void main(String[] args) {

        Subject proxy = DynProxyFactory.getInstance();
        proxy.dealTask("DBQueryTask");
    }

}