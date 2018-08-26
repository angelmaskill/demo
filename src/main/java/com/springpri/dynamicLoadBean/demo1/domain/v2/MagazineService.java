package com.springpri.dynamicLoadBean.demo1.domain.v2;

import lombok.Getter;
import lombok.Setter;

public class MagazineService {
	@Getter
	@Setter
	private String name;

	public void print() {
		System.out.println("打印杂志服务生效,版本:v2----" + this.getName());
	}

	public String print(String a, String b) {
		System.out.println("打印杂志服务生效,版本:v2----入参:" + a + ";" + b + ";para:" + this.getName());
		return "OK";
	}
}
