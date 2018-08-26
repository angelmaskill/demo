package com.exit.test2;

public class GracefulShutdownTest {
	public static void listenKillCommand() {
		// 为当前进程添加一个回调钩子，kill -15的情况下 会默认的调用该方法
		Runtime.getRuntime().addShutdownHook(new ListenKillThread());
	}

	public static void main(String[] args) {
		listenKillCommand();
	}
}
