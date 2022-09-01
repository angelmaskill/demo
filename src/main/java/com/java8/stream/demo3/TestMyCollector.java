package com.java8.stream.demo3;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * 演示自定义收集器的使用
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-1 11:00
 * @description：
 * @modified By：
 * @version:
 */
public class TestMyCollector {
    @Test
    public void testMyCollector() {
        Integer result = Stream.of(new Score(1), new Score(2), new Score(3), new Score(4))
                .collect(new MyCollector<>(Score::getScore));
        System.out.println(result);
    }
}
