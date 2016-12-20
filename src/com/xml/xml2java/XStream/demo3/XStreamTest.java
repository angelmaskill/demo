/**
 * @(#)XStreamTest.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-5-27     Administrator    Created
 **********************************************
 */

package com.xml.xml2java.XStream.demo3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

/**
 * Class description goes here.
 * @其中，实例化时需要xstream-[version].jar and xpp3-[version].jar在classpath中。
 * @XPP3是一个非常快的XML拉式转换器工具。如果你不想包含这个依赖，你可以使用标准的JAXP DOM转换器来代替。
 * 
 * @author Administrator
 * @since 2015-5-27
 */
public class XStreamTest {
    public static void main(String[] args) {
        Policy policy = createTestPolicy();
        XStream xstream = new XStream();
        //xstream.alias("policy", Policy.class);
        //xstream.alias("item", Item.class);
        String xml = xstream.toXML(policy);
        System.out.println(xml);

        Policy newPolicy = (Policy) xstream.fromXML(xml);
        System.out.println(xstream.toXML(newPolicy));
    }

    private static Policy createTestPolicy() {
        Policy policy = new Policy();
        policy.setPolicyNo("测试保单号");
        List items = new ArrayList();
        policy.setItems(items);
        Item item = new Item();
        item.setItemCode("001");
        item.setItemCode("基本信息");
        items.add(item);
        item = new Item();
        item.setItemCode("002");
        item.setItemCode("扩展信息");
        items.add(item);
        return policy;
    }
}

class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String itemCode;
    private String itemName;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}

class Policy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String policyNo;
    private List items;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
