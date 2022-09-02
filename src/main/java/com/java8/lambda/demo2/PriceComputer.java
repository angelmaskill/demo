package com.java8.lambda.demo2;

import java.util.List;

/**
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 9:45
 * @description：
 * @modified By：
 * @version:
 */
@FunctionalInterface
public interface PriceComputer<T> {
    double computePrice(List<T> objects);
}