package com.generic.demo0.generic;

/**
 * 泛型类:
 * 
 * <pre>
 * 相当于以下代码:
 *  	public class GenericFoo<T extends Object> {
 *     	//....
 * 		}
 * </pre>
 * 
 * @author mayanlu
 *
 * @param <T>
 */
public class GenericFoo<T> {
	private T foo;

	public void setFoo(T foo) {
		this.foo = foo;
	}

	public T getFoo() {
		return foo;
	}
}
