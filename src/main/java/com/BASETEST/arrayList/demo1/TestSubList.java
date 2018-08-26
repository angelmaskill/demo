package com.BASETEST.arrayList.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:测试SubList
 * 
 * <pre>
 *   JDK中，List接口有一个实例方法List<E> subList(int fromIndex, int toIndex)，其作用是返回一个以fromIndex为起始索引（包含），以toIndex为终止索引（不包含）的子列表（List）。 
 *   但值得注意的是，
 *   (1) 返回的这个子列表的幕后其实还是原列表；
 *   (2) 也就是说，修改这个子列表，将导致原列表也发生改变；反之亦然。
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class TestSubList {

	public static void main(String[] args) {
		testEqual();
	}

	private static void testOperate() {
		List<Integer> test = new ArrayList<Integer>();
		// init list
		for (int i = 0; i < 5; i++) {
			test.add(i); // auto boxing
		}
		// display the list
		System.out.print("the orginal list: ");
		for (int i = 0; i < test.size(); i++) {
			System.out.print(test.get(i) + " ");
		}
		System.out.println();

		// sub list
		List<Integer> sub = test.subList(1, 3); // sub list contains elements:
												// 1, 2
		System.out.print("the sublist :");
		for (int i = 0; i < sub.size(); i++) {
			System.out.print(sub.get(i) + " ");
		}
		System.out.println();
		sub.remove(1); // remove element “2” from sub list

		// display the list again
		System.out.print("the orginal list after sublist modified: ");
		for (int i = 0; i < test.size(); i++) {
			System.out.print(test.get(i) + " ");
		}
		System.out.println();
	}

	private static void testEqual() {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);

		// 通过构造函数新建一个包含list1的列表 list2
		List<Integer> list2 = new ArrayList<Integer>(list1);

		// 通过subList生成一个与list1一样的列表 list3
		List<Integer> list3 = list1.subList(0, list1.size());

		// 修改list3
		list3.add(3);

		System.out.println("list1 == list2：" + list1.equals(list2));
		System.out.println("list1 == list3：" + list1.equals(list3));
	}
}