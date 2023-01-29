package com.spi.pri;

import java.util.ServiceLoader;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2023-1-29 13:48
 * @description：
 * @modified By：
 * @version:
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<Ishout> shouts = ServiceLoader.load(Ishout.class);
        for (Ishout s : shouts) {
            s.shout();
        }
    }
}
