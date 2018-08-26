package com.designpattern.ali.charge;

import com.designpattern.ali.branch.InterPhone;

public class Yidong implements Icharge{

	private double price;
	
	public Yidong(double price) {
		super();
		this.price = price;
	}

	/**
	 * 移动充值没有优惠
	 */
	@Override
	public void charge(InterPhone phone) {
		this.price= phone.getPrice();;
	}

}
