package com.bytecode.test;

import com.bytecode.Cglib.demo1.CGLibProxy;
import com.bytecode.domains.UserManager;
import com.bytecode.domains.UserManagerImpl;
import com.bytecode.jdk.demo1.JDKProxy;

public class TestCglibAndJdk {
	public static void main(String[] args) {

		UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
		System.out.println("-----------CGLibProxy-------------");
		userManager.addUser("tom", "root");
		System.out.println("-----------JDKProxy-------------");
		JDKProxy jdkPrpxy = new JDKProxy();
		UserManager userManagerJDK = (UserManager) jdkPrpxy.newProxy(new UserManagerImpl());
		userManagerJDK.addUser("tom", "root");
	}
}
