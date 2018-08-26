package com.generic.demo3;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 类型通配符:
 * 		1.一般是使用?代替具体的类型参数。
 * 		2.例如 List<?> 在逻辑上是List<String>>,List<Integer> 等所有List<具体类型实参>的父类。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class GenericTest {
	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();

		name.add("icon");
		age.add(18);
		number.add(314);

//		getData(name);
//		getData(age);
//		getData(number);

//		getUperNumber(name);//1  	不允许传递string类型的参数.
		getUperNumber(age);// 2
		getUperNumber(number);// 3

	}

	/**
	 * 因为getDate()方法的参数是List类型的，所以name，age，number都可以作为这个方法的实参，这就是通配符的作用
	 * 
	 * @param data
	 */
	public static void getData(List<?> data) {
		System.out.println("data :" + data.get(0));
	}

	/**
	 * 如此定义就是通配符泛型值接受Number及其下层子类类型。
	 * 
	 * @param data
	 */
	public static void getUperNumber(List<? extends Number> data) {
		System.out.println("data :" + data.get(0));
	}
}
