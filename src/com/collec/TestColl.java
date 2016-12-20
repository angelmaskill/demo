/**
 * @(#)TestColl.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-9-21     Administrator    Created
 **********************************************
 */

package com.collec;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-9-21
 */
@SuppressWarnings("rawtypes")
public class TestColl {
    @SuppressWarnings("unchecked")
	public void testEnumeration() throws Exception {
        
		Enumeration days;
        Vector dayNames = new Vector();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();
        while (days.hasMoreElements()){
           System.out.println(days.nextElement()); 
        }
    }
    
    
    @Ignore
	public void testSplitList1() {
		long start = System.currentTimeMillis();
		int totalSize = 90;
		List list = new ArrayList(totalSize);
		for (int i = 1; i <= totalSize; i++) {
			list.add(i);
		}
		int batchSize = 8;
		int parl = totalSize / batchSize + (totalSize % batchSize == 0 ? 0 : 1);
		System.out.println("parl==" + parl);
		Map<Integer, List> map = new HashMap<Integer, List>();
		for (int i = 0; i < parl; i++) {
			map.put(i, new ArrayList());
		}
		for (int i = 0; i < parl; i++) {
			if (i != parl - 1) {
				map.get(i).addAll(list.subList(i * batchSize, (i + 1) * batchSize));
			} else {
				map.get(i).addAll(list.subList(i * batchSize, list.size()));
			}
		}
		System.out.println(map);
		long end = System.currentTimeMillis();
		System.out.println("---------"+(end-start));
	}
    
	@Test
	public void testSplitList2() {
		List<List> totallist =  new ArrayList();
		long start = System.currentTimeMillis();
		List<Integer> a = new ArrayList();
		for (int i = 1; i <= 17; i++) {
			a.add(i);
		}
		int bucket = 11;
		/**
		 * 当数少,线程多,就把线程的个数置为数的个数.
		 */
		if(a.size() < bucket){
			bucket = a.size();
		}
		int size = a.size();
		int parallel = bucket + (size % bucket == 0 ? 0 : 1);
		int p = size / bucket;
		int last = size%bucket;
		System.out.println("剩余的个数last:" + last);
		System.out.println("每个桶的容量p:" + p);
		System.out.println("原始数组大小为size:" + size);
		System.out.println("将被拆分为parallel:" + parallel + "个数组\n");
		List retList = new ArrayList<>();
		
		totallist = get( parallel, p, a,last);
		long end = System.currentTimeMillis();
		System.out.println("\n---------耗时:"+(end-start)+"ms");
		System.out.println("线程个数:"+totallist.size());
	}
	
	public List get(int parallel,int p,List a,int last){
		List ret = null;
		List totallist = new ArrayList<>();
		for (int i = 0; i < parallel; i++) {
			System.out.println("第" + (i + 1) + "个桶开始遍历--------");
			ret = removeList(p, a, i);
			if(last>p){
				List sub_list=a.subList(parallel, a.size());
				if(i<sub_list.size()){
					ret.add(sub_list.get(i));
				}
			}
			iterList(ret);
			System.out.println("第" + (i + 1) + "个桶end遍历xxxxxxxx");
			totallist.add(ret);
		}
		return totallist;
	}
	
	@Test
	public void testSplitList3() throws Exception {
		int totalSize = 17;
		List list = new ArrayList(totalSize);
		for (int i = 1; i <= totalSize; i++) {
			list.add(i);
		}
		int batchSize = 1;
		int parl = totalSize / batchSize + (totalSize % batchSize == 0 ? 0 : 1);
		Map<Integer, List> map = new HashMap<Integer, List>();
		int j = 0;
		for (int i = 0; i < parl; i++) {
			map.put(i, new ArrayList());
			for (; j < list.size();j++) {
				if (j < (i + 1) * (batchSize)) {
					map.get(i).add(list.get(j));
					System.out.println(i + "桶,j:" + j);
				} else {
					break;
				}
			}
		}
	}

	private List removeList(int p, List a, int time) {
		List retList = new ArrayList<>();
		for (int i = p * time; i < ((time + 1) * p); i++) {
			/*System.out.println("p=="+p);
			System.out.println("p * time=="+p * time);
			System.out.println("((time + 1) * p)=="+((time + 1) * p));
			System.out.println("i=="+i);*/
			if (i <= a.size() - 1) {
				retList.add(a.get(i));
			}
		}
		
		
		return retList;
	}

	
	
	private void iterList(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("第" + i + "个元素的值:" + list.get(i));
		}
	}
}
