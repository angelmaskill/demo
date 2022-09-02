package com.java8.lambda.demo3;

import com.java8.lambda.demo1.Numbers;
import org.junit.Test;

import java.util.function.BiFunction;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 11:30
 * @description：
 * @modified By：
 * @version:
 */
public class TestLambda {

    @Test
    public void testPlus() {
        Numbers numbers = new Numbers();
        numbers.setN1(10);
        numbers.setN2(20);
        // 写法一
        int test = test((a, b) -> a * b, numbers);
        //写法二
        int test1 = test(this::calc, numbers);
        System.out.println(test);
        System.out.println(test1);
    }

    private int test(BiFunction<Integer, Integer, Integer> method, Numbers number) {
        return method.apply(number.getN1(), number.getN2());
    }

    public Integer calc(Integer a, Integer b) {
        return a * b;
    }
}
