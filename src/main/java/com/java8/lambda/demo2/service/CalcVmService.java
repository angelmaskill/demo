package com.java8.lambda.demo2.service;

import com.java8.lambda.demo2.vo.VmDetail;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 10:25
 * @description：
 * @modified By：
 * @version:
 */
public class CalcVmService implements IcalcPrice {

    @Override
    public double calc(Object obj) {
        VmDetail vmDetail = (VmDetail) obj;
        double result = 100 * vmDetail.getCpuCores() + 10 * vmDetail.getDiskSizeG() + 50 * vmDetail.getMemSizeG();
        return result;
    }
}