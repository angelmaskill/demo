package com.mina.utils;

public class StackPrint {
	public static void main(String[] args) {
		printStack();
		printStack2();
	}

	public static void printStack() {
		StringBuilder sb = new StringBuilder("");
		Exception e = new Exception("");
		StackTraceElement[] trace = e.getStackTrace();
		for (int i = 0; i < trace.length; i++)
			sb.append("\tat " + trace[i] + "\n");
		System.out.println(("who invoke me\n" + sb.toString()));
	}

	public static void printStack2() {
		Throwable ex = new Throwable();
		StackTraceElement[] stackElements = ex.getStackTrace();

		if (stackElements != null) {
			for (int i = 0; i < stackElements.length; i++) {
				System.out.println(stackElements[i].getClassName());// 返回类的完全限定名，该类包含由该堆栈跟踪元素所表示的执行点。
				System.out.println(stackElements[i].getFileName());// 返回源文件名，该文件包含由该堆栈跟踪元素所表示的执行点。
				System.out.println(stackElements[i].getLineNumber());// 返回源行的行号，该行包含由该堆栈该跟踪元素所表示的执行点。
				System.out.println(stackElements[i].getMethodName());// 返回方法名，此方法包含由该堆栈跟踪元素所表示的执行点。
				System.out.println("-------------第" + i + "级调用-------------------");
			}
		}
	}
}
