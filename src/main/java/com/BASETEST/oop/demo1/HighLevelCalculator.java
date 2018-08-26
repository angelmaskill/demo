package com.BASETEST.oop.demo1;

import lombok.Getter;
import lombok.Setter;

public class HighLevelCalculator extends Calculator {


	@Override
	public double add(double a, double b) {
		// TODO Auto-generated method stub
		return result = a + b;
	}

	@Override
	public double minus(double a, double b) {
		// TODO Auto-generated method stub
		return result = a - b;
	}
}
