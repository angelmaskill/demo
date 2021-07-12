package com.DTDL.demo6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName MapperProxy
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-06-09 17:00
 */
public class MapperProxy implements InvocationHandler {

    /**
     * @param clz 传入被代理对象的类型
     * @param <T>
     * @return 返回增强之后的接口实例对象
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Class<T> clz) {
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), //接口类的ClassLoader
                new Class[]{clz},//需要实现的接口数组，至少需要传入一个接口进去
                this);//用来处理接口方法调用的InvocationHandler实例。
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                // 诸如hashCode()、toString()、equals()等方法，将target指向当前对象this
                return method.invoke(this, args);
            } catch (Throwable t) {
            }
        }
        // 投鞭断流
        return new User((Integer) args[0], "zhangsan", 18);
    }
}
