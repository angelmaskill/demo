package com.basetest.optional.demo1;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-7 9:54
 * @description：
 * @modified By：
 * @version:
 */
public class Content {
    private String id;
    private String value;

    public Content(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}