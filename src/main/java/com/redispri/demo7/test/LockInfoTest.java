package com.redispri.demo7.test;
import org.junit.Test;

import com.redispri.demo7.util.LockInfo;

public class LockInfoTest {
	
	@Test
	public void test() {
		LockInfo li = new LockInfo();
		
		li.setCount(1);
		li.setExpires(Long.MAX_VALUE);
		li.setMac("127.0.0.1");
		li.setJvmPid(11);
		li.setThreadId(Thread.currentThread().getId());
		
		System.out.println(li.toString());
	}
	
}
