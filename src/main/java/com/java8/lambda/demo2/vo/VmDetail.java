package com.java8.lambda.demo2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 计算虚拟机的价格
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 9:45
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
public class VmDetail{
    private int cpuCores;
    private int memSizeG;
    private int diskSizeG;


}