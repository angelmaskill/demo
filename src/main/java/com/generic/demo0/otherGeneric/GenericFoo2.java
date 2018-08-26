package com.generic.demo0.otherGeneric;

public class GenericFoo2<T1, T2> {
	private T1 foo1;
	private T2 foo2;

	public void setFoo1(T1 foo1) {
		this.foo1 = foo1;
	}

	public T1 getFoo1() {
		return foo1;
	}

	public void setFoo2(T2 foo2) {
		this.foo2 = foo2;
	}

	public T2 getFoo2() {
		return foo2;
	}

	public static void main(String[] args) {
		GenericFoo2<Integer, Boolean> foo = new GenericFoo2<Integer, Boolean>();
	}
}
