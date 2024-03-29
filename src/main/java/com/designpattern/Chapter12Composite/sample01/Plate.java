package com.designpattern.Chapter12Composite.sample01;

import java.util.ArrayList;

public class Plate extends MyElement {
    private ArrayList list = new ArrayList();

    public void add(MyElement element) {
        list.add(element);
    }

    public void delete(MyElement element) {
        list.remove(element);
    }

    public void eat() {
        for (Object object : list) {
            ((MyElement) object).eat();
        }
    }
}