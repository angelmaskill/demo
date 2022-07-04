package com.basetest.equals;

import java.util.HashMap;
import java.util.Objects;

/**
 * 因为hashCode 相同，说明存放在同一个桶里
 * key equal , 说明在链表里是同一个对象。
 *
 * @ClassName Boy
 * @Description 验证equal /hashcode
 * @Author yanlu.myl
 * @Date 2021-07-17 17:14
 */
public class Boy {
    private int id;
    private String name;
    private String stuNo;

    public Boy(int id, String name, String stuNo) {
        this.id = id;
        this.name = name;
        this.stuNo = stuNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boy boy = (Boy) o;
        return stuNo.equals(boy.stuNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuNo);
    }

    public static void main(String[] args) {
        Boy 张三 = new Boy(1, "张三", "001");
        Boy 张三1 = new Boy(2, "张三1", "001");
        HashMap<Boy, String> map = new HashMap<>();
        map.put(张三, "张三已经放入map");
        System.out.println(map.get(张三1));
    }
}
