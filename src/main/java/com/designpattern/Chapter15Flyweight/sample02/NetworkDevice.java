package com.designpattern.Chapter15Flyweight.sample02;

public interface NetworkDevice {
    public String getType();

    public void use(Port port);
}