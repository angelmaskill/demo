package com.DTDL.demo7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * 增强 arraylist 的remove方法
 *
 * @ClassName Handler
 * @Description arrayList remove 增强类
 * @Author yanlu.myl
 * @Date 2021-07-12 11:07
 */
public class ArrayListProxyHandler implements InvocationHandler {
    private Collection<String> target;

    public ArrayListProxyHandler(Collection<String> target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("remove")) {
            Iterator<String> iterator = target.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.equals(args[0])) {
                    iterator.remove();
                }
            }
        }
        return method.invoke(target, args);
    }
}
