package com.generic.demo1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * <pre>
 * 测试:
 * 		1. 泛型方法:你可以写一个泛型方法，该方法在调用时可以接收不同类型的参数。
 * 		2. 有界的类型参数:一个操作数字的方法可能只希望接受Number或者Number子类的实例。这就是有界类型参数的目的。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class GenericMethodTest {
	public static void main(String args[]) {
//		testPrint();
		testLimit();
	}

	// 泛型方法 printArray
	public static <E> void printArray(E[] inputArray) {
		// 输出数组元素
		for (E element : inputArray) {
			System.out.printf("%s ", element);
		}
		System.out.println();
	}

	/**
	 * <pre>
	 * 入参,返回值是泛型. 
	 * 传入参数必须是能够比较的对象. 
	 * 比较三个值并返回最大值
	 * </pre>
	 */
	public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
		T max = x; // 假设x是初始最大值
		if (y.compareTo(max) > 0) {
			max = y; // y 更大
		}
		if (z.compareTo(max) > 0) {
			max = z; // 现在 z 更大
		}
		return max; // 返回最大对象
	}

	/**
	 * 泛型方法的定义.
	 */
	private static void testPrint() {
		// 创建不同类型数组： Integer, Double 和 Character
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

		System.out.println("整型数组元素为:");
		printArray(intArray); // 传递一个整型数组

		System.out.println("\n双精度型数组元素为:");
		printArray(doubleArray); // 传递一个双精度型数组

		System.out.println("\n字符型数组元素为:");
		printArray(charArray); // 传递一个字符型数组
	}

	/**
	 * <pre>
	 * 有界的类型参数:
	 * 		1. 可能有时候，你会想限制那些被允许传递到一个类型参数的类型种类范围。例如，一个操作数字的方法可能只希望接受Number或者Number子类的实例。这就是有界类型参数的目的。
	 * 		2. 要声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界。
	 * </pre>
	 */
	private static void testLimit() {
		System.out.printf("%d, %d 和 %d 中最大的数为 %d\n\n", 3, 4, 5, maximum(3, 4, 5));
		System.out.printf("%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n", 6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));
		System.out.printf("%s, %s 和 %s 中最大的数为 %s\n", "pear", "apple", "orange", maximum("pear", "apple", "orange"));
	}
}