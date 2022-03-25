package com.generic.demo5;

/**
 * 泛型接口：泛型接口与泛型类的定义及使用基本相同
 *
 * @param <T1>
 * @param <T2>
 */
public interface IFoo<T1, T2> {
    public void setFoo1(T1 foo1);

    public void setFoo2(T2 foo2);

    public T1 getFoo1();

    public T2 getFoo2();
}