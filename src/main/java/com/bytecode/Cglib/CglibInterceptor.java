package com.bytecode.Cglib;

import java.lang.reflect.Method;

import com.bytecode.utils.CountService;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibInterceptor implements MethodInterceptor {

	final Object delegate;

	public CglibInterceptor(Object delegate) {
		this.delegate = delegate;
	}

	public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy)
			throws Throwable {
		return methodProxy.invoke(delegate, objects);
	}
	
	public static CountService createCglibDynamicProxy(final CountService delegate) throws Exception {
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(new CglibInterceptor(delegate));
		enhancer.setInterfaces(new Class[] { CountService.class });
		CountService cglibProxy = (CountService) enhancer.create();
		return cglibProxy;
	}
}