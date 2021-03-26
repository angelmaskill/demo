package com.redispri.demo8.test;

import com.redispri.demo8.redislockframework.CacheLock;
import com.redispri.demo8.redislockframework.LockedObject;


public interface SeckillInterface {
    @CacheLock(lockedPrefix = "TEST_PREFIX")
    public void secKill(String arg1, @LockedObject Long arg2);
}
