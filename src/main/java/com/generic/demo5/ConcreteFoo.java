package com.generic.demo5;

/**
 * 泛型接口的实现类-未传入泛型实参例子
 *
 * <pre>
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 *      即：class ConcreteFoo<T1, T2> implements IFoo<T1, T2>{
 *      如果不声明泛型，如：class ConcreteFoo implements IFoo<T1, T2>，编译器会报错："Unknown class"
 *
 * </pre>
 *
 * @param <T1>
 * @param <T2>
 * @author mayanlu
 */
public class ConcreteFoo<T1, T2> implements IFoo<T1, T2> {
    private T1 foo1;
    private T2 foo2;

    @Override
    public void setFoo1(T1 foo1) {
        this.foo1 = foo1;
    }

    @Override
    public T1 getFoo1() {
        return foo1;
    }

    @Override
    public void setFoo2(T2 foo2) {
        this.foo2 = foo2;
    }

    @Override
    public T2 getFoo2() {
        return foo2;
    }
}