package com.DTDL.demo7;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在Collection接口中 我们的remove(Object obj)方法是删除集合中第一次出现的元素(比如集合中有多个“abc”,调用remove(“abc”)后只会删除一个元素)。重复出现的元素，就需要我们去慢慢的去删除了。
 * 这里我们使用对Collection接口进行动态代理，再调用remove(Object obj)方法时候能够删除集合中所有匹配的元素。
 *
 * @ClassName Client
 * @Description 测试代理客户端
 * @Author yanlu.myl
 * @Date 2021-07-12 13:09
 */
public class TestClient {
    public static void main(String[] args) {
        //初始化原始对象
        Collection<String> list = initList();

        //获取被代理类的class
        Class<? extends Collection> aClass = list.getClass();

        //生成代理类，只能用接口接收
        List newList = (List) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new ArrayListProxyHandler(list));

        newList.remove("a");
        System.out.println(list);
    }

    private static Collection<String> initList() {
        /*ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("ab");*/

        Collection<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "a", "ab");
        return list;
    }
}
