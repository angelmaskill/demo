package com.BASETEST.ENUM.demo2;

/**
 * <pre>
 * 用法:作为switch
 * JDK1.6之前的switch语句只支持int,char,enum类型，使用枚举，能让我们的代码可读性更强。
 * 
 * </pre>
 * 
 * @author mayanlu
 *
 */
enum Signal {
	GREEN, YELLOW, RED
}

public class TrafficLight {
	Signal color = Signal.RED;

	public void change() {
		switch (color) {
		case RED:
			color = Signal.GREEN;
			break;
		case YELLOW:
			color = Signal.RED;
			break;
		case GREEN:
			color = Signal.YELLOW;
			break;
		}
	}
}