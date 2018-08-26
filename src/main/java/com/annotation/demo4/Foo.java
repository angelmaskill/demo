package com.annotation.demo4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

//测试类Foo
public class Foo {

	@Timer
	public void algo1() {
		ArrayList<Integer> l = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			l.add(1);
		}
	}

	@Timer
	public void algo2() {
		LinkedList<Integer> l = new LinkedList<>();
		for (int i = 0; i < 10000000; i++) {
			l.add(1);
		}
	}

	public void algo3() {
		Vector<Integer> v = new Vector<>();
		for (int i = 0; i < 10000000; i++) {
			v.add(1);
		}
	}

	public static void main(String[] foo) {
		TimerUtil tu = new TimerUtil();
		tu.getTime();
	}
}