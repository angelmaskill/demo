package com.para;

public class Person {
    public String name;


    public Person() {

    }

    public Person(String name) {
        super();
        this.name = name;
    }

    // 定义一个改变对象属性的方法
    public static Person changeName(Person p) {
        p.name = "Rose";

        /**
         * 在方法内,试试修改引用的地址
         */
        System.out.println("method p add1:" + p);
        System.out.println("method p value1:" + p.name);
        p = new Person("master");
        System.out.println("method p add2:" + p);
        System.out.println("method p value2:" + p.name);


        Person p3 = new Person("slave");
        p = p3;
        System.out.println("method p add3:" + p);
        System.out.println("method p value3:" + p.name);
        return p;
    }

    public static void main(String[] args) {
        // 定义一个Person对象，person是这个对象的引用
        Person person = new Person("Jack");
        // 先显示这个对象的name属性
        System.out.println(person.name);
        // 调用changeName(Person p)方法
        System.out.println("main obj add1:" + person);
        person = changeName(person);
        System.out.println("main obj add2:" + person);
        // 再显示这个对象的name属性，看是否发生了变化
        System.out.println(person.name);
    }
}
