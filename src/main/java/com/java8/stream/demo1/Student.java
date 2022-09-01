package com.java8.stream.demo1;

/**
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/6/5 11:36 上午
 * @Modified By:
 */
public class Student {
    //姓名
    private String name;
    //考试分数
    private Integer score;
    //班级
    private int cls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public Student(String name, Integer score, int cls) {
        this.name = name;
        this.score = score;
        this.cls = cls;
    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", score=" + score + ", cls=" + cls + '}';
    }

    public String modify(String name, int score, int cls) {
        String oldstr = this.toString();
        this.setName(name);
        this.setScore(score);
        this.setCls(cls);
        return "old:" + oldstr + ";new:" + this.toString();
    }

    public String show(String str) {
        return str;
    }
}
