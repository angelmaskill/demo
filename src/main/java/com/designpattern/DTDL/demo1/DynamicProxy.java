/**
 * @(#)DynamicProxy.java Copyright Oristand.All rights reserved.
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
import java.lang.reflect.Method;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-7-28
 */
public class DynamicProxy implements InvocationHandler {
    /**
     * object:被代理的真实对象
     */
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    /**
     * proxy:被代理的对象
     * method:要调用的方法
     * args:方法调用时所需要的参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Invoke ! method :开始记录日志功能" + method);

        //我们可以再代理方法调用前后添加功能  
        Object result = method.invoke(object, args);

        System.out.println("object : " + object + "\tresult : " + result + "\targs : " + args);
        System.out.println("After Invoke !记录日志完成");
        return result;
    }

}
