package com.designpattern.Chapter16Proxy.DynamicProxy;

public class RealSubjectA implements AbstractSubject
{	
	public void request()
	{
		System.out.println("真实主题类A！");
	}
}