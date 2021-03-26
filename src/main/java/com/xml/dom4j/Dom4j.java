/**
 * @(#)Dom4j.java Copyright Oristand.All rights reserved. This software is the XXX system.
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

package com.xml.dom4j;

import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-11-4
 */
public class Dom4j extends TestCase {
    public void testGerXml() throws IOException {
        // 创建文档并设置文档的根元素节点 ：第一种方式
        // Document document = DocumentHelper.createDocument();
        // Element root = DocumentHelper.createElement("student");
        // document.setRootElement(root);

        // 创建文档并设置文档的根元素节点 ：第二种方式
        Element root = DocumentHelper.createElement("student");// 创建父节点
        Document document = DocumentHelper.createDocument(root);
        root.addAttribute("name", "zhangsan");

        Element helloElement = root.addElement("hello");// 创建子节点
        Element worldElement = root.addElement("world");// 创建子节点

        helloElement.setText("hello");// 设置子节点值
        worldElement.setText("world");
        helloElement.addAttribute("age", "20");// 给子节点设置属性

        XMLWriter xmlWriter = new XMLWriter();// XML输出工具
        xmlWriter.write(document);

        OutputFormat format = new OutputFormat("    ", true);
        XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("student2.xml"), format);
        xmlWriter2.write(document);
        XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student3.xml"), format);
        xmlWriter3.write(document);
        xmlWriter3.close();
    }

    public void testReadXMLByDom4j() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File("student2.xml"));//创建输入流
        Element root = doc.getRootElement();//获取根节点
        System.out.println("root element: " + root.getName());//获取根节点名称
        List childList = root.elements();//获取根节点下子节点集合
        System.out.println(childList.size());//输出子节点个数
        List childList2 = root.elements("hello");//获取hello子节点
        System.out.println(childList2.size());
        Element first = root.element("hello");
        System.out.println(first.attributeValue("age"));
        for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
            Element e = (Element) iter.next();

            System.out.println(e.attributeValue("age"));
        }
        System.out.println("---------------------------");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document document = db.parse(new File("student2.xml"));
        DOMReader domReader = new DOMReader();
        // 将JAXP的Document转换为dom4j的Document
        Document d = domReader.read(document);
        Element rootElement = d.getRootElement();
        System.out.println(rootElement.getName());

    }

}
