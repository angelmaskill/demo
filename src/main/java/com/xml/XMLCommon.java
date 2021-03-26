/**
 * @(#)XMLCommon.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-11-4     Administrator    Created
 **********************************************
 */

package com.xml;

import junit.framework.TestCase;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-11-4
 */
public class XMLCommon extends TestCase {
    /*public void testConvertListToXML() {
        
         * 造数据
         
        Map map1 =new HashMap();
        map1.put("e201", "a1");
        map1.put("e202", "a2");
        map1.put("e203", "a3");
        
        Map map2 =new HashMap();
        map2.put("e201", "b1");
        map2.put("e202", "b2");
        map2.put("e203", "b3");
        
        List ls =new ArrayList();
        ls.add(map1);
        ls.add(map2);
        
         * 开始测试
         
        StringBuffer sb = new StringBuffer();
        sb.append("<").append("list").append(">");

        Iterator it = ls.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            java.util.Map map = (Map) it.next();
            Set<String> keys = map.keySet();
            sb.append("<item").append(" id=\"").append(index).append("\">");
            for (String key : keys) {
                sb.append("<" + key + ">");
                sb.append(map.get(key) == null ? "" : map.get(key));
                sb.append("</" + key + ">");
            }
            sb.append("</item>");
        }

        sb.append("</").append("list").append(">");
        System.out.println(sb.toString());
    }*/
    
   /* public void testMap2XML(){
            Map map1 =new HashMap();
            map1.put("e201", "a1");
            map1.put("e202", "a2");
            map1.put("e203", "a3");
            
            Set<String> keys = map1.keySet();
            StringBuffer sb = new StringBuffer();

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sb.append("<businessdata>");

            for (String key : keys) {
                sb.append("<" + key + ">");
                sb.append(map1.get(key) == null ? "" : map1.get(key));
                sb.append("</" + key + ">");
            }

            sb.append("</businessdata>");
            System.out.println(sb.toString());
    }*/
}
