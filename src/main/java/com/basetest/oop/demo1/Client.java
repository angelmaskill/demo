package com.basetest.oop.demo1;

public class Client {
    public static void main(String[] args) {
        Calculator calcNom = new NomalCalculator();
        Calculator calcHig = new HighLevelCalculator();
        double a = calcNom.add(10, 20);
        calcNom.getResult();
        double b = calcNom.minus(10, 20);
        calcNom.getResult();

        double c = calcHig.add(10, 20);
        calcHig.getResult();
        double d = calcHig.minus(10, 20);
        calcHig.getResult();

    }
}
