/**
 * @(#)Jdom.java
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
 *  1     2014-11-4     Administrator    Created
 **********************************************
 */

package com.xml.jdom.mypri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2014-11-4
 */
public class Jdom extends TestCase {
    public void testGerXMLbyJdom() throws IOException {
        Document document = new Document();
        Element root = new Element("联系人列表").setAttribute(new Attribute("公司", "A集团"));
        document.addContent(root);
        Element contactPerson = new Element("联系人");
        root.addContent(contactPerson);
        contactPerson.addContent(new Element("姓名").setText("张三")).addContent(new Element("公司").setText("A公司")).addContent(
                new Element("电话").setText("021-55556666")).addContent(
                new Element("地址").addContent(new Element("街道").setText("5街")).addContent(new Element("城市").setText("上海")).addContent(
                        new Element("省份").setText("上海市")));
        XMLOutputter output = new XMLOutputter(Format.getPrettyFormat().setIndent("    ").setEncoding("gbk"));
        output.output(document, new FileWriter("contact.xml"));
    }

    public void testGerXmlByJdom() throws Exception {
        Document document = new Document();
        Element root = new Element("root");
        document.addContent(root);
        Comment comment = new Comment("This is my comments");
        root.addContent(comment);
        Element e = new Element("hello");
        e.setAttribute("sohu", "www.sohu.com");
        root.addContent(e);
        Element e2 = new Element("world");
        Attribute attr = new Attribute("test", "hehe");
        e2.setAttribute(attr);
        e.addContent(e2);
        e2.addContent(new Element("aaa").setAttribute("a", "b").setAttribute("x", "y").setAttribute("gg", "hh").setText("text content"));
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        //format.setEncoding("gbk");

        XMLOutputter out = new XMLOutputter(format);
        out.output(document, new FileWriter("jdom.xml"));
    }

    /*
     * 读取xml，移除某个节点，输出到新的XML文件
     */
    public void testReadWriteByJdom() throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File("contact.xml"));
        Element element = doc.getRootElement();
        System.out.println(element.getName());
        Element hello = element.getChild("联系人");
        System.out.println(hello.getText());
        List list = hello.getAttributes();
        for (int i = 0; i < list.size(); i++) {
            Attribute attr = (Attribute) list.get(i);
            String attrName = attr.getName();
            String attrValue = attr.getValue();
            System.out.println(attrName + "=" + attrValue);
        }
        hello.removeChild("姓名");
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat().setIndent("    "));
        out.output(doc, new FileOutputStream("contact2.xml"));

    }

}
