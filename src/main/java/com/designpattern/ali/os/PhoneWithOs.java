package com.designpattern.ali.os;

import com.designpattern.ali.branch.InterPhone;

public abstract class PhoneWithOs extends InterPhone {
	
	public PhoneWithOs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhoneWithOs(double price) {
		super(price);
	}

	public InterPhone interPhone;

	public void loados(InterPhone interPhone) {
		// TODO Auto-generated method stub
		this.interPhone = interPhone;
	}

}
