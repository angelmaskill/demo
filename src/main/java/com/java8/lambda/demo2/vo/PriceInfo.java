package com.java8.lambda.demo2.vo;

import lombok.Data;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 9:44
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class PriceInfo {
    private double price;
    private double tax;
    private double taxRate;
    private double totalPay;
}