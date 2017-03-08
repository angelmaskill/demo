package com.jvm.classloader.demo8;

public class Sample {

	public Sample() {
		System.out.println("sample is loaded by " + this.getClass().getClassLoader());
		Dog dog = new Dog();
	}
}