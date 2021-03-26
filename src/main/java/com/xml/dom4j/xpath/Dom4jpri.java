/**
 * @(#)Dom4jpri.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-10-10     Administrator    Created
 **********************************************
 */

package com.xml.dom4j.xpath;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-10-10
 */
public class Dom4jpri {
    @Ignore
    public void test1() throws Exception {
        ParseXml px = new ParseXml("src/com/xml/studentInfo.xml");
        String info = px.getElementText("/students/student[name='崔卫兵']/@age");
        info = px.getElementText("/students/student[@age=45]/notes");
        info = px.getElementText("/students/student[last()-2]/notes");
        System.out.println(info);
    }

    @Test
    public void test2() throws Exception {
        ParseXml px = new ParseXml("errpar.xml");
        List<Node> list = px.getElementObjects("/NDEML/body/bodyItem[@itemId]");
        Iterator iter = list.iterator();
        String key = "";
        String msg = "";

        while (iter.hasNext()) {
            StringBuffer sb = new StringBuffer();
            Element element = (Element) iter.next();
            String name = element.getName();
            String value = element.getText();
            // System.out.println("节点名：" + name);
            // 获取属性值
            Attribute attribute = element.attribute("itemId");
            name = attribute.getName();
            value = attribute.getValue();
            // System.out.println("属性名：" + name + ";属性值" + value);
            // 获取子节点
            List<Node> listson = px.getElementObjects("/NDEML/body/bodyItem[@itemId='" + value + "']/data");
            Iterator iterson = listson.iterator();
            key = value.split("_")[0];
            if (iterson.hasNext()) {
                Element elementson = (Element) iterson.next();
                name = elementson.getName();
                value = elementson.getText();
                // System.out.println("节点名：" + name);
                List<Node> lists2 = elementson.elements();
                Iterator iterson2 = lists2.iterator();
                while (iterson2.hasNext()) {
                    Element elementson2 = (Element) iterson2.next();
                    name = elementson2.getName();
                    value = elementson2.getText();
                    // System.out.println(name + ":" + value);
                    sb.append(name + "" + value + ";");
                }

            }
            System.out.println(key + ":" + sb.toString());
        }
    }

}
