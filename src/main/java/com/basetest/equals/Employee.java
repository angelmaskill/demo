package com.basetest.equals;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 对于 compareTo 和 equals 两个方法我们可以总结为：compareTo 是判断元素在排序中的位置是否相等，equals 是判断元素是否相等，
 * 既然一个决定排序位置，一个决定相等，所以我们非常有必要确保当排序位置相同时，其 equals 也应该相等。
 * </pre>
 *
 * @author mayanlu
 */
public class Employee implements Comparable<Employee> {

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int age;

    public Employee(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (age != other.age)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public int compareTo(Employee student) {
        return this.age - student.age;
    }

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("1", "chenssy1", 24));
        list.add(new Employee("2", "chenssy1", 26));

        Collections.sort(list); // 排序

        Employee student = new Employee("2", "chenssy1", 26);

        // 检索student在list中的位置
        int index1 = list.indexOf(student);
        int index2 = Collections.binarySearch(list, student);

        System.out.println("index1 = " + index1);
        System.out.println("index2 = " + index2);
    }

}