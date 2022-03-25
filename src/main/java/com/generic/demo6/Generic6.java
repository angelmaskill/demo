package com.generic.demo6;

/**
 * 通配符:当具体类型不确定的时候，这个通配符就是 ?
 * 当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-24 20:07
 * @description：
 * @modified By：
 * @version:
 */
public class Generic6 {
    public static void showvalue(Generic<Number> obj){
        System.out.println(obj.getKey());
    }
    public static void showvalue2(Generic<?> obj){
        System.out.println(obj.getKey());
    }

    public static void main(String[] args) {
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        //showvalue(gInteger); showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
        showvalue(gNumber);

        //使用通配符，以下两个都能编译通过
        showvalue2(gInteger);
        showvalue2(gNumber);
    }
}
