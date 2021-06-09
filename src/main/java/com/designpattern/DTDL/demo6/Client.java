package com.designpattern.DTDL.demo6;

/**
 * @ClassName Client
 * @Description TODO
 * @Author yanlu.myl
 * @Date 2021-06-09 17:00
 */
public class Client {
    public static void main(String[] args) {
        // 实现 InvocationHandler 类, 就代表定义了增强的逻辑
        MapperProxy proxy = new MapperProxy();

        // 返回增强接口的实例对象
        UserMapper mapper = proxy.newInstance(UserMapper.class);
        User user = mapper.getUserById(1001);

        System.out.println("ID:" + user.getId());
        System.out.println("Name:" + user.getName());
        System.out.println("Age:" + user.getAge());

        System.out.println(mapper.toString());
    }
}
