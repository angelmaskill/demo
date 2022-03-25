package com.generic.demo2;

/**
 * <pre>
 * 泛型类:
 * 		1.泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型 。
 * 	    2.泛型类的声明和非泛型类的声明类似，除了在类名后面添加了类型参数声明部分。
 * 		3.和泛型方法一样，泛型类的类型参数声明部分也包含一个或多个类型参数，参数间用逗号隔开。
 *
 * 注意：
 *      1. 泛型的类型参数只能是类类型，不能是简单类型。
 *      2. 不能对确切的泛型类型使用instanceof操作。 比如：if(ex_num instanceof Generic<Number>)
 * </pre>
 *
 * @author mayanlu
 */
public class GenericClassTest<T> {
    //t 这个成员变量的类型为T,T的类型由外部指定
    private T t;

    public void add(T t) {
        this.t = t;
    }

    //方法 get 的返回值类型为T，T的类型由外部指定
    public T get() {
        return t;
    }

    public GenericClassTest() {
    }

    //泛型构造方法形参t 的类型也为T，T的类型由外部指定
    public GenericClassTest(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        //传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用
        GenericClassTest<Integer> integerBox = new GenericClassTest<Integer>();
        GenericClassTest<String> stringBox = new GenericClassTest<String>();

        integerBox.add(new Integer(10));
        stringBox.add(new String("菜鸟教程"));

        System.out.printf("整型值为 :%d\n\n", integerBox.get());
        System.out.printf("字符串为 :%s\n", stringBox.get());

        //如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
        GenericClassTest a1 = new GenericClassTest(1);
        GenericClassTest a2 = new GenericClassTest("sss");
        GenericClassTest a3 = new GenericClassTest(15.1f);
        GenericClassTest a4 = new GenericClassTest(false);
        System.out.println(a1.get());
        System.out.println(a2.get());
        System.out.println(a3.get());
        System.out.println(a4.get());
    }
}
