package com.netty.test10.entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartNetty {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("com/netty/test10/spring.xml");
    }
}
