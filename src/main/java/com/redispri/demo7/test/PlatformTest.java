package com.redispri.demo7.test;

import com.redispri.demo7.util.PlatformUtils;
import org.junit.Test;

public class PlatformTest {

    @Test
    public void test() {
        System.out.println(PlatformUtils.MACAddress());
    }

}
