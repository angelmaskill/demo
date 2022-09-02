package com.java8.lambda.demo1;

import org.apache.poi.ss.formula.functions.T;

/**
 * @ClassName LambdaInterface
 * @Description 声明 lambda 接口
 * @Author yanlu.myl
 * @Date 2021-05-31 11:40
 */
@FunctionalInterface
public interface LambdaInterface<T> {
    public T apply(T t);
}
