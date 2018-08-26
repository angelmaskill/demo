package com.redispri.demo7.test;

import org.junit.Test;

import com.redispri.demo7.util.PlatformUtils;

public class PlatformTest {
	
	@Test
	public void test() {
		System.out.println(PlatformUtils.MACAddress());
	}
	
}
