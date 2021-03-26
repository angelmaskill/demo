package com.lombokTest.demo1;

import lombok.Data;

@Data
public class User {

    public String name;

    public String password;

    public static void main(String[] args) {
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("1111");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
    }
}