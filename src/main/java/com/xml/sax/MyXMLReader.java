/**
 * @(#)MyXMLReader.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-10-9     Administrator    Created
 **********************************************
 */

package com.xml.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2015-10-9
 */
public class MyXMLReader extends DefaultHandler{
    java.util.Stack tags = new java.util.Stack();

    public MyXMLReader() {
        super();
    }

    

    public void characters(char ch[], int start, int length) throws SAXException {
        String tag = (String) tags.peek();
        if (tag.equals("NO")) {
            System.out.print("车牌号码：" + new String(ch, start, length));
        }
        if (tag.equals("ADDR")) {
            System.out.println("地址:" + new String(ch, start, length));
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        tags.push(qName);
    }
    
    public static void main(String args[]) {
        long lasting = System.currentTimeMillis();
        try {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser sp = sf.newSAXParser();
            MyXMLReader reader = new MyXMLReader();
            sp.parse(new InputSource( "src/com/xml/sax/data_10k.xml"), reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + "毫秒");
    }
}
