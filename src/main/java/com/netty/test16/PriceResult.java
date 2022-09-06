package com.netty.test16;

import lombok.Data;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-5 11:55
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class PriceResult {
    private int price;
    private int discounts;
    private int realPrice;
    private String platform;

    public PriceResult(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return
                "【平台：" + platform +
                        ", 原价：" + price +
                        ", 折扣：" + discounts +
                        ", 实付价：" + realPrice +
                        "】";
    }
}