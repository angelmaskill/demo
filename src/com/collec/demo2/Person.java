package com.collec.demo2;

public class Person {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name, int age, String sex, String ct) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.ct = ct;
	}

	private String name, sex, ct;
	private int age;

}
