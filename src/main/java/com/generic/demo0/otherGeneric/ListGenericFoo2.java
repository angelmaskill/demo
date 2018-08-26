package com.generic.demo0.otherGeneric;

import java.util.ArrayList;
import java.util.List;

public class ListGenericFoo2<T extends List<String>> {
	private T[] fooArray;

	public void setFooArray(T[] fooArray) {
		this.fooArray = fooArray;
	}

	public T[] getFooArray() {
		return fooArray;
	}

	public static void main(String[] args) {
		ListGenericFoo2<ArrayList<String>> foo = new ListGenericFoo2<ArrayList<String>>();
	}
}
