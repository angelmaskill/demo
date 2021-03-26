/**
 * @(#)TestMap.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-12-1     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap是有序的。
 *
 * @author Administrator
 * @since 2014-12-1
 */
public class TestMap {
    public static void main(String args[]) {
        System.out.println("*************************LinkedHashMap*************");
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(6, "apple");
        map.put(3, "banana");
        map.put(2, "pear");

        for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
            Object key = it.next();
            System.out.println(key + "=" + map.get(key));
        }

        System.out.println("*************************HashMap*************");
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(6, "apple");
        map1.put(3, "banana");
        map1.put(2, "pear");

        for (Iterator it = map1.keySet().iterator(); it.hasNext(); ) {
            Object key = it.next();
            System.out.println(key + "=" + map1.get(key));
        }
    }
}
