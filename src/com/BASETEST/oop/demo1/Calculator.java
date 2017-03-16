package com.BASETEST.oop.demo1;

import lombok.Getter;
import lombok.Setter;

public abstract class Calculator implements Add, Minus {
	@Getter
	@Setter
	protected double a;
	@Getter
	@Setter
	protected double b;

	@Getter
	@Setter
	protected double result;

	
	protected void getResult() {
		System.out.println( result);
	}

	@Override
	public double add(double a, double b) {
		return 0d;
	}

	@Override
	public double minus(double a, double b) {
		return 0d;
	}
}
