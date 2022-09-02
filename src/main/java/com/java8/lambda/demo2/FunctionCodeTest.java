package com.java8.lambda.demo2;


import com.java8.lambda.demo2.service.CalcNetworkService;
import com.java8.lambda.demo2.service.CalcVmService;
import com.java8.lambda.demo2.service.IcalcPrice;
import com.java8.lambda.demo2.vo.NetworkDetail;
import com.java8.lambda.demo2.vo.PriceInfo;
import com.java8.lambda.demo2.vo.VmDetail;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-2 9:43
 * @description：
 * @modified By：
 * @version:
 */
public class FunctionCodeTest {
    public <T> PriceInfo calculatePriceInfo(List<T> resources, PriceComputer<T> priceComputer) {
        // 调用函数式接口获取计算结果
        double price = priceComputer.computePrice(resources);

        // 执行后续处理策略
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setPrice(price);
        priceInfo.setTaxRate(0.15);
        priceInfo.setTax(price * 0.15);
        priceInfo.setTotalPay(priceInfo.getPrice() + priceInfo.getTax());
        return priceInfo;
    }


    @Test
    public void test() {
        List<Object> vmDetailList = initVmLists();
        List<Object> networkDetailList = initNetWorkLists();

        // 计算虚拟机总金额
        PriceInfo vmPrice = calcList(vmDetailList);
        System.out.println("虚拟机计算结果：" + vmPrice);
        // 计算磁盘总金额
        PriceInfo netPrice = calcList(networkDetailList);
        System.out.println("磁盘计算结果：" + netPrice);

        BiFunction<PriceInfo, PriceInfo, Double> calcsum = (a, b) -> a.getTotalPay() + b.getTotalPay();
        System.out.println(calcsum.apply(vmPrice, netPrice));
    }

    /**
     * 这里提供了一种全量数据初始化的思路。
     */
    @Test
    public void testAll() {
        List<Object> initAll = initAll();
        PriceInfo priceInfo = calcList(initAll);
        System.out.println("总计：" + priceInfo);
    }


    /**
     * 核心方法，计算批量价格
     * 根据对象找到对应的计算策略。
     * @param lists
     * @return
     */
    private PriceInfo calcList(List<Object> lists) {
        PriceInfo priceInfo = calculatePriceInfo(lists, objects -> {
            double result = 0d;
            for (Object item : objects) {
                result += getResult((Class<IcalcPrice>) mapCalcMethod(item), item);
            }
            return result;
        });
        return priceInfo;
    }

    /**
     * 单个商品的价格计算
     *
     * @param claz
     * @param t
     * @return
     */
    @SneakyThrows
    private Double getResult(Class<IcalcPrice> claz, Object t) {
        IcalcPrice icalcPrice = claz.newInstance();
        return icalcPrice.calc(t);
    }

    /**
     * 根据不同的商品，找到对应商品的计算策略类。
     *
     * @param t
     * @return
     */
    private Class<? extends IcalcPrice> mapCalcMethod(Object t) {
        if (t instanceof VmDetail) {
            return CalcVmService.class;
        } else if (t instanceof NetworkDetail) {
            return CalcNetworkService.class;
        }
        return null;
    }

    /**
     * 初始化测试数据
     *
     * @return
     */
    private List<Object> initVmLists() {
        List<Object> collect = Stream.of(
                new VmDetail(2, 2, 1),
                new VmDetail(2, 2, 1))
                .collect(Collectors.toList());
        return collect;
    }

    private List<Object> initNetWorkLists() {
        List<Object> collect = Stream.of(
                new NetworkDetail(2),
                new NetworkDetail(4))
                .collect(Collectors.toList());

        return collect;
    }

    //初始化测试数据： 可以混在一起
    private List<Object> initAll() {
        List<Object> collect = Stream.of(
                new VmDetail(2, 2, 1),
                new VmDetail(2, 2, 1),
                new NetworkDetail(2),
                new NetworkDetail(4))
                .collect(Collectors.toList());
        return collect;
    }
}