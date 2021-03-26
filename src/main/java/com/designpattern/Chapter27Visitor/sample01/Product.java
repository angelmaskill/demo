package com.designpattern.Chapter27Visitor.sample01;

public interface Product {
    void accept(Visitor visitor);
}