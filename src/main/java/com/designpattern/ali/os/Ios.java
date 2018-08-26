package com.designpattern.ali.os;

public class Ios extends PhoneWithOs {

	
	
	public Ios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ios(double price) {
		super(price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		this.interPhone.display();
		System.out.println("加载的是ios系统");
	}

}
