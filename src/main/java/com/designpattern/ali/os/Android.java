package com.designpattern.ali.os;

public class Android extends PhoneWithOs{


	
	public Android() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Android(double price) {
		super(price);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void display() {
		// TODO Auto-generated method stub
		this.interPhone.display();
		System.out.println("加载的是安卓系统");
	}

}
