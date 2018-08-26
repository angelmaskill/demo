package com.reflect.demo2;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 功能:测试反射的性能
 *  进行预热之后,调用方法10^7 次,差别不大: 
 *  Reflecting instantiation took:602ms
 * 	Normal instaniation took: 985ms
 * 
 * 性能慢的原因:
 *  1. 本该装载期间的事情都是放在运行期做的
 * 	2. 编译器没法对反射相关的代码做优化
 * </pre>
 * 
 * @author mayanlu
 *
 */
class B {
}

public class TestReflect {
	public static long timeDiff(long old) {
		return System.currentTimeMillis() - old;
	}

	public static long timeDiff2(long old) {
		return System.nanoTime() - old;
	}

	public static void main(String args[]) throws Exception {
//		testRefNoWarm();
		testRefWarm();
	}

	private static void testRefNoWarm() throws InstantiationException, IllegalAccessException {
		long numTrials = (long) Math.pow(10, 7);
		System.out.println("total times: " + numTrials);
		long millis;
		millis = System.currentTimeMillis();
		for (int i = 0; i < numTrials; i++) {
			new B();
		}
		System.out.println("Normal instaniation took: " + timeDiff(millis) + "ms");
		millis = System.currentTimeMillis();
		Class<B> c = B.class;
		for (int i = 0; i < numTrials; i++) {
			c.newInstance();
		}
		System.out.println("Reflecting instantiation took:" + timeDiff(millis) + "ms");
	}

	private static void testRefWarm() throws InstantiationException, IllegalAccessException {
		int numTrials = (int) Math.pow(10, 7);
		B[] bees = new B[numTrials];
		Class<B> c = B.class;
		for (int i = 0; i < numTrials; i++) {
			bees[i] = c.newInstance();
		}
		for (int i = 0; i < numTrials; i++) {
			bees[i] = new B();
		}
		long nanos;
		nanos = System.nanoTime();
		for (int i = 0; i < numTrials; i++) {
			bees[i] = c.newInstance();
		}
		System.out.println("Reflecting instantiation took:" + TimeUnit.NANOSECONDS.toMillis(timeDiff2(nanos)) + "ms");
		nanos = System.nanoTime();
		for (int i = 0; i < numTrials; i++) {
			bees[i] = new B();
		}
		System.out.println("Normal instaniation took: " + TimeUnit.NANOSECONDS.toMillis(timeDiff2(nanos)) + "ms");
	}
}
