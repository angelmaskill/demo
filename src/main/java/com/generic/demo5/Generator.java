package com.generic.demo5;

/**
 * 泛型接口：泛型接口与泛型类的定义及使用基本相同
 * @param <T>
 */
public interface Generator<T> {
    public T next();
}
