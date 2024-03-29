package com.redispri.demo7.util;

import com.redispri.demo7.Lock;
import com.redispri.demo7.Releasable;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * 模拟分布式环境中的ID生成
 *
 * @author lixiaohui
 * @date 2016年9月19日 下午9:18:40
 */
public class IDGenerator implements Releasable {

    private static BigInteger id = BigInteger.valueOf(0);

    private final Lock lock;

    private static final BigInteger INCREMENT = BigInteger.valueOf(1);

    public IDGenerator(Lock lock) {
        this.lock = lock;
    }

    public String getAndIncrement() {
        if (lock.tryLock(3, TimeUnit.SECONDS)) {
            try {
                // TODO 这里获取到锁, 访问临界区资源
				/*try {
					lock.tryLock(3, TimeUnit.SECONDS);
				} finally {
					lock.unlock();
				}*/

                return getAndIncrement0();
            } finally {
                lock.unlock();
            }
        }
        return null;
        //return getAndIncrement0();
    }

    public void release() {
        lock.release();
    }

    private String getAndIncrement0() {
        String s = id.toString();
        id = id.add(INCREMENT);
        return s;
    }
}