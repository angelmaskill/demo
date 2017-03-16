package com.collec.demo5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * 比较集合的稳定性和有序性.
 *  	1.无序:不会自动排序
 * 		2.稳定:读出顺序和插入顺序一致.
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class OrderTest {
	public static void main(String[] args) {

		System.out.println("放入顺序为：a:aaa c:ccc b:bbb d:ddd ");
		System.out.println("HashMap 排序测试");
		Map map = new HashMap();
		map.put("a", "aaa");
		map.put("c", "ccc");
		map.put("b", "bbb");
		map.put("d", "ddd");
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			System.out.println("key:" + key + "         value:" + map.get(key));
		}
		System.out.println("HashMap 进出无次序 ； key大小无次序 ");
		System.out.println("***********************************************************");

		System.out.println("Hashtable 排序测试");
		Hashtable tab = new Hashtable();
		tab.put("a", "aaa");
		tab.put("c", "ccc");
		tab.put("b", "bbb");
		tab.put("d", "ddd");
		Iterator iterator_1 = tab.keySet().iterator();
		while (iterator_1.hasNext()) {
			Object key = iterator_1.next();
			System.out.println("key :" + key + "             value:" + tab.get(key));
		}
		System.out.println("Hashtable 进出无次序 ； key大小无次序 ");
		System.out.println("***********************************************************");

		System.out.println("TreeMap 排序测试");
		TreeMap tmp = new TreeMap();
		tmp.put("a", "aaa");
		tmp.put("c", "ccc");
		tmp.put("b", "bbb");
		tmp.put("d", "ddd");
		Iterator iterator_2 = tmp.keySet().iterator();
		while (iterator_2.hasNext()) {
			Object key = iterator_2.next();
			System.out.println("key:" + key + "          value:" + tmp.get(key));
		}
		System.out.println("TreeMap 进出无次序 ； key大小从小到大 ");
		System.out.println("***********************************************************");

		System.out.println("LinkedHashMap 排序测试");
		LinkedHashMap linkedHashMap = new LinkedHashMap();
		linkedHashMap.put("a", "aaa");
		linkedHashMap.put("c", "ccc");
		linkedHashMap.put("b", "bbb");
		linkedHashMap.put("d", "ddd");
		Iterator iterator2 = linkedHashMap.keySet().iterator();
		while (iterator2.hasNext()) {
			Object key = iterator2.next();
			System.out.println("key:" + key + "          value:" + linkedHashMap.get(key));
		}
		System.out.println("LinkedHashMap 先进先出； key大小无次序 ");
		System.out.println();
		System.out.println("***********************************************************");
		/**
		 * ArrayList 特点: 无序:不会自动排序 稳定:读出顺序和插入顺序一致.
		 */
		System.out.println("ArrayList 排序测试");
		System.out.println("放入顺序为：aaa ccc bbb ddd");
		ArrayList arrayList = new ArrayList();
		arrayList.add("aaa");
		arrayList.add("ccc");
		arrayList.add("bbb");
		arrayList.add("ddd");
		System.out.println("ArrayList 先进先出；值大小无次序 ");
		// Collections.sort(arrayList);
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}
		System.out.println("***********************************************************");
	}
}
