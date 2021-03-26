/**
 * @(#)Client.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-7-28     Administrator    Created
 **********************************************
 */

package com.designpattern.DTDL.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 通过这种方式，被代理的对象(RealSubject)可以在运行时动态改变，需要控制的接口(Subject接口)可以在运行时改变，
 * 控制的方式(DynamicSubject类)也可以动态改变，
 * 从而实现了非常灵活的动态代理关系。
 * 参考博文：
 *  http://jiangshuiy.iteye.com/blog/1333558
 *  http://www.ibm.com/developerworks/cn/java/j-lo-proxy1/
 *
 * @author Administrator
 * @since 2015-7-28
 */
public class Client {
    public static void main(String[] args) throws Exception {

        //创建目标对象，也就是被代理对象  
        RealSubject realSubject = new RealSubject();

        //将被代理对象交给代理,handler中能够被传入真实被代理对象  
        InvocationHandler handler = new DynamicProxy(realSubject);

        /**
         Class<?> proxyClass = Proxy.getProxyClass(Subject.class.getClassLoader()  , new Class[]{Subject.class});
         Subject subject = (Subject)proxyClass.getConstructor(new Class[]{InvocationHandler.class})  .newInstance(new Object[]{handler});
         */
        /**
         * 返回代理对象，相当于上面两句
         * CLassLoader loader:类的加载器
         * Class<?> interfaces:得到全部的接口定义
         * InvocationHandler h:得到InvocationHandler接口的子类的实例  
         */
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

        //叫代理对象去doSomething()，其实在代理对象中的doSomething()中还是会  
        //用handler来调用invoke(proxy, method, args) 参数proxy为调用者subject(this)，  
        //method为doSomething()，参数为方法要传入的参数，这里没有  
        subject.sleep();
    }
}
