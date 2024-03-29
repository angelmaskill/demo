package com.copy.shallow.demo1;

class Professor0 implements Cloneable {

    @Override
    public String toString() {
        return "Professor name:" + this.name + ";age" + this.age;
    }

    String name;
    int age;

    Professor0(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}

class Student0 implements Cloneable {
    String name;// 常量对象。
    int age;
    Professor0 p;// 学生1和学生2的引用值都是一样的。

    Student0(String name, int age, Professor0 p) {
        this.name = name;
        this.age = age;
        this.p = p;
    }

    public Object clone() {
        Student0 o = null;
        try {
            o = (Student0) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }

        return o;
    }

    @Override
    public String toString() {
        return "Student name:" + this.name + "; age" + this.age + "; his teacher:" + p.toString();
    }

}

public class ShallowCopy {
    public static void main(String[] args) {
        Professor0 p = new Professor0("wangwu", 50);
        Student0 s1 = new Student0("zhangsan", 18, p);
        System.out.println(p.toString());
        System.out.println("s1 info==== " + s1.toString());
        Student0 s2 = (Student0) s1.clone();
        System.out.println("s2 info==== " + s2.toString());
        s2.p.name = "lisi";
        s2.p.age = 30;
        s2.name = "z";
        s2.age = 45;
        System.out.println("学生s1的姓名：" + s1.name + "\n学生s1教授的姓名：" + s1.p.name + "," + "\n学生s1教授的年纪" + s1.p.age);// 学生1的教授
    }
}
