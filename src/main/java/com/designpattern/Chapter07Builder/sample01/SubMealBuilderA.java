package com.designpattern.Chapter07Builder.sample01;

public class SubMealBuilderA extends MealBuilder {
    public void buildFood() {
        meal.setFood("һ�����ȱ�");
    }

    public void buildDrink() {
        meal.setDrink("һ������");
    }
}