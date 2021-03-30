package com.basetest.ENUM.demo5;

/**
 * 用法：使用接口组织枚举
 *
 * @author mayanlu
 */
public interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}