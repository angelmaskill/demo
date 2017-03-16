package com.generic.demo2;

/**
 * <pre>
 * 泛型类:
 * 		1.泛型类的声明和非泛型类的声明类似，除了在类名后面添加了类型参数声明部分。
 * 		2.和泛型方法一样，泛型类的类型参数声明部分也包含一个或多个类型参数，参数间用逗号隔开。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class GenericClassTest<T> {
	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public static void main(String[] args) {
		GenericClassTest<Integer> integerBox = new GenericClassTest<Integer>();
		GenericClassTest<String> stringBox = new GenericClassTest<String>();

		integerBox.add(new Integer(10));
		stringBox.add(new String("菜鸟教程"));

		System.out.printf("整型值为 :%d\n\n", integerBox.get());
		System.out.printf("字符串为 :%s\n", stringBox.get());
	}
}
