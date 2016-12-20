package com.springpri.multDatabase;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	//IUserDao userDao = (IUserDao) ac.getBean("userDao");

	@Test
	public void testSave() {
		// hibernate创建实体
		DataSourceContextHolder.setDataSourceType(DataSourceConst.Admin);// 设置为另一个数据源
		//User user = new User();
		//user.setName("wzhhh");
		//user.setPassword("hhh");
		//userDao.save(user);// 使用dao保存实体

		DataSourceContextHolder.setDataSourceType(DataSourceConst.User);// 设置为另一个数据源
		//userDao.save(user);// 使用dao保存实体到另一个库中

	}

}