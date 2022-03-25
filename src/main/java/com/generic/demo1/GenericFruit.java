package com.generic.demo1;

/**
 * 类中的泛型方法
 *
 * <pre>
 * 对于一个static的方法而已，无法访问泛型类型的参数。如果static方法要使用泛型能力，就必须使其成为泛型方法。
 * </pre>
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-3-25 10:22
 * @description：
 * @modified By：
 * @version:
 */
public class GenericFruit {
    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * <pre>
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
       "StaticGenerator cannot be refrenced from static context"
     *
     * </pre>
     */
    public static <T> void show(T t){

    }

    static class Fruit {
        @Override
        public String toString() {
            return "fruit";
        }
    }

    static class Apple extends Fruit {
        @Override
        public String toString() {
            return "apple";
        }
    }

    static class Person {
        @Override
        public String toString() {
            return "Person";
        }
    }

    static class GenerateTest<T> {
        public void show_1(T t) {
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t) {
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t) {
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();

        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        //apple是Fruit的子类，所以这里可以
        generateTest.show_1(apple);
        //编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //generateTest.show_1(person);

        //使用这两个方法都可以成功
        generateTest.show_2(apple);
        generateTest.show_2(person);

        //使用这两个方法也都可以成功
        generateTest.show_3(apple);
        generateTest.show_3(person);
    }
}
