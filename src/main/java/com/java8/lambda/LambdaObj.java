package com.java8.lambda;

import java.util.List;

/**
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/6/5 12:41 下午
 * @Modified By:
 */
public class LambdaObj<T> {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void runByData(LambdaInterface lambdaInterface) {
        list.forEach(i -> System.out.println(lambdaInterface.apply(i)));
    }
}
