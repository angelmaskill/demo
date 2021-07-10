package com.DTDL.demo4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 类应用关系：代理对象---》handler --》目标对象 生成动态代理对象的工厂.
 */
public class DynProxyFactory {

    public static Subject getInstance() {

        /*创建原始对象*/
        Subject realSubject = new RealSubject();

        /*handler 中需要拿到原始对象，才能进行逻辑增强*/
        InvocationHandler handler = new SubjectInvocationHandler(realSubject);

        /*代理对象会使用 handler 的能力，最终实现能力增强*/
        Subject proxy = null;
        proxy = (Subject)Proxy.newProxyInstance(realSubject.getClass()
            .getClassLoader(), realSubject.getClass()
            .getInterfaces(), handler);
        return proxy;
    }
}