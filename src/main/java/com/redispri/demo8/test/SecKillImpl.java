package com.redispri.demo8.test;

import java.util.HashMap;
import java.util.Map;

public class SecKillImpl implements SeckillInterface {
    static Map<Long, Long> inventory;

    static {
        inventory = new HashMap<Long, Long>();
        inventory.put(10000001L, 10000l);
        inventory.put(10000002L, 10000l);
    }

    public void secKill(String arg1, Long arg2) {
        //最简单的秒杀，这里仅作为demo示例
        reduceInventory(arg2);
    }

    //模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
    public Long reduceInventory(Long commodityId) {
        inventory.put(commodityId, inventory.get(commodityId) - 1);
        return inventory.get(commodityId);
    }

}
