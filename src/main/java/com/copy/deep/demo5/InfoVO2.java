package com.copy.deep.demo5;

public class InfoVO2 {
    @Override
    public String toString() {
        return this.getId() + this.getDate() + this.getId();
    }

    private int id;
    private String date;
    private Integer gender;


    public InfoVO2() {
        super();
        // TODO Auto-generated constructor stub
    }

    public InfoVO2(int id, String date, Integer gender) {
        super();
        this.id = id;
        this.date = date;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

}   