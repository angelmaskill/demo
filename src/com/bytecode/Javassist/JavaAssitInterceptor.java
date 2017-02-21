package com.bytecode.Javassist;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.bytecode.utils.CountService;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public  class JavaAssitInterceptor implements MethodHandler {

	final Object delegate;

	public JavaAssitInterceptor(Object delegate) {
		this.delegate = delegate;
	}

	public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Throwable {
		return m.invoke(delegate, args);
	}
	
	public static CountService createJavassistDynamicProxy(final CountService delegate) throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[] { CountService.class });
		Class<?> proxyClass = proxyFactory.createClass();
		CountService javassistProxy = (CountService) proxyClass.newInstance();
		((ProxyObject) javassistProxy).setHandler(new JavaAssitInterceptor(delegate));
		return javassistProxy;
	}

	public static CountService createJavassistBytecodeDynamicProxy(CountService delegate) throws Exception {
		ClassPool mPool = new ClassPool(true);
		CtClass mCtc = mPool.makeClass(CountService.class.getName() + "JavaassistProxy");
		mCtc.addInterface(mPool.get(CountService.class.getName()));
		mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
		mCtc.addField(CtField.make("public " + CountService.class.getName() + " delegate;", mCtc));
		mCtc.addMethod(CtNewMethod.make("public int count() { return delegate.count(); }", mCtc));
		Class<?> pc = mCtc.toClass();
		CountService bytecodeProxy = (CountService) pc.newInstance();
		Field filed = bytecodeProxy.getClass().getField("delegate");
		filed.set(bytecodeProxy, delegate);
		return bytecodeProxy;
	}
	
}