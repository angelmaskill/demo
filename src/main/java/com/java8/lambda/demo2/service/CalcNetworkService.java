package com.java8.lambda.demo2.service;

import com.java8.lambda.demo2.vo.NetworkDetail;
import org.apache.poi.ss.formula.functions.T;

import java.util.function.Function;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 10:25
 * @description：
 * @modified By：
 * @version:
 */
public class CalcNetworkService implements IcalcPrice {

    @Override
    public double calc(Object obj) {
        return 20 * ((NetworkDetail)obj).getBandWidthM();
    }
}
