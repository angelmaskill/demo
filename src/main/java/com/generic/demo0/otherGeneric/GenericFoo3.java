package com.generic.demo0.otherGeneric;

public class GenericFoo3<T> {
	/**
	 * 注意您可以使用泛型机制来声明一个数组，例如下面这样是可行的：
	 */
	private T[] fooArray;

	/**
	 * 但是您不可以使用泛型来建立阵列的实例，例如以下是不可行的：
	 * 
	 * @param fooArray
	 */
//	private T[] fooArray = new T[10]; // 不可以使用泛型建立数组实例

	public void setFooArray(T[] fooArray) {
		this.fooArray = fooArray;
	}

	public T[] getFooArray() {
		return fooArray;
	}

	public static void main(String[] args) {
		String[] strs = { "caterpillar", "momor", "bush" };
		GenericFoo3<String> foo = new GenericFoo3<String>();
		foo.setFooArray(strs);
		strs = foo.getFooArray();
	}
}