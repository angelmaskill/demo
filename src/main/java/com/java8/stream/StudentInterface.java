package com.java8.stream;

/**
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/6/6 11:32 上午
 * @Modified By:
 */
@FunctionalInterface
public interface StudentInterface<T> {
    String modify(T stu, String name, Integer score, Integer cls);
}
