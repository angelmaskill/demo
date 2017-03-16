package com.BASETEST.ENUM.demo3;

import lombok.Getter;
import lombok.Setter;

/**
 * 向枚举中添加新方法
 * @author mayanlu
 *
 */
public enum Color {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
	// 成员变量
	@Getter @Setter private String name;
	@Getter @Setter private int index;

	// 构造方法
	private Color(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (Color c : Color.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

}