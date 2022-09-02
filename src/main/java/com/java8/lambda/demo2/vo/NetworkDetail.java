package com.java8.lambda.demo2.vo;

import com.java8.lambda.demo2.service.IcalcPrice;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 计算网络宽带的价格
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 9:46
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
public class NetworkDetail {
    private int bandWidthM;
}