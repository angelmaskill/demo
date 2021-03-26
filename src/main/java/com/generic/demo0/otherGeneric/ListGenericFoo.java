package com.generic.demo0.otherGeneric;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ListGenericFoo<T extends List> {
    private T[] fooArray;

    public void setFooArray(T[] fooArray) {
        this.fooArray = fooArray;
    }

    public T[] getFooArray() {
        return fooArray;
    }

    public static void main(String[] args) {
        ListGenericFoo<LinkedList> foo1 = new ListGenericFoo<LinkedList>();
        ListGenericFoo<ArrayList> foo2 = new ListGenericFoo<ArrayList>();
        // 以下会报错:
//		ListGenericFoo<HashMap> foo3 = new ListGenericFoo<HashMap>();
    }
}