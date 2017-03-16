package com.designpattern.ali.charge;

import com.designpattern.ali.branch.InterPhone;

public class Liantong implements Icharge{
	private double price;
	
	
	public Liantong() {
		super();
	}

	/**
	 * 充值有优惠,所有手机立减百分之二
	 */
	@Override
	public void charge(InterPhone phone) {
		this.price= price*(1-0.02);
		
	}
}
