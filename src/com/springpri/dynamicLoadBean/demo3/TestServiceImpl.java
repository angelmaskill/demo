package com.springpri.dynamicLoadBean.demo3;

import lombok.Getter;
import lombok.Setter;

public class TestServiceImpl implements TestService {

	@Getter
	@Setter
	private TestDao testDao;

	@Override
	public void SayHello() {
		// TODO Auto-generated method stub
		this.getTestDao().doSomething();
	}

}
