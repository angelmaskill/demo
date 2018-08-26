package com.designpattern.ali.os;

public class Yunos extends PhoneWithOs{

	public Yunos(double price) {
		super(price);
		// TODO Auto-generated constructor stub
	}
	

	public Yunos() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void display() {
				this.interPhone.display();
				System.out.println("加载的是yun系统");
	}

}
