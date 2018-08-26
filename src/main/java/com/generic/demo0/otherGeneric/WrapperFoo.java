package com.generic.demo0.otherGeneric;

import com.generic.demo0.generic.GenericFoo;

/**
 * 泛型类引用泛型类.
 * 
 * @author mayanlu
 *
 * @param <T>
 */
public class WrapperFoo<T> {
	private GenericFoo<T> foo;

	public void setFoo(GenericFoo<T> foo) {
		this.foo = foo;
	}

	public GenericFoo<T> getFoo() {
		return foo;
	}

	public static void main(String[] args) {
		GenericFoo<Integer> foo = new GenericFoo<Integer>();
		foo.setFoo(new Integer(10));
		WrapperFoo<Integer> wrapper = new WrapperFoo<Integer>();
		wrapper.setFoo(foo);
	}
}