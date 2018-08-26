package com.bytecode.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.bytecode.utils.CountService;

public class JdkHandler implements InvocationHandler {

	final Object delegate;

	public  JdkHandler(Object delegate) {
		this.delegate = delegate;
	}

	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		return method.invoke(delegate, objects);
	}
	
	public static CountService createJdkDynamicProxy(final CountService delegate) {
		CountService jdkProxy = (CountService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { CountService.class }, new JdkHandler(delegate));
		return jdkProxy;
	}

}