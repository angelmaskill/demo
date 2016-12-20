package com.designpattern.DTDL.demo4;

public class Client {

	public static void main(String[] args) {

		Subject proxy = DynProxyFactory.getInstance();
		proxy.dealTask("DBQueryTask");
	}

}