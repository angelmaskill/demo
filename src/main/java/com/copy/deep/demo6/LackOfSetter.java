package com.copy.deep.demo6;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/29 17:50
 * @Modified By:
 */
public class LackOfSetter {
    private int id;
    private String name;

    public LackOfSetter() {
    }

    public LackOfSetter(int id, String name) {
        this.id = id;
        this.name = name;
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
}
