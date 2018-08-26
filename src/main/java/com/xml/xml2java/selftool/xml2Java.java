/**
 * @(#)xml2Java.java
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
 *  1     2015-5-27     Administrator    Created
 **********************************************
 */

package com.xml.xml2java.selftool;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class xml2Java {

    /**
     * @param args
     */
    public static Document reader(String fileName) throws DocumentException {

        SAXReader reader = new SAXReader();
        reader.setEncoding("gbk");
        Document document = reader.read(fileName);
        return document;
    }

    public static Element getRootElement(Document document) {
        Element root = document.getRootElement();
        return root;
    }

    public static void treeWalk(Element element,String aft) {
        if (!element.isTextOnly()) {
            element2java(element,aft);
        }
        for (Iterator iterator = element.elementIterator(); iterator.hasNext();) {
            Element elem = (Element) iterator.next();
            treeWalk(elem,aft);

        }
    }

    public static void element2java(Element element,String aft) {
        try {
            FileOutputStream fos = null;
            BufferedWriter bw = null;
            FileOutputStream fos2 = null;
            BufferedWriter bw2 = null;
            String elementName = "";
            StringBuffer bufferProperty = new StringBuffer("");
            StringBuffer bufferJava = new StringBuffer("");
            StringBuffer bufferGetterSetter = new StringBuffer("");
            StringBuffer dbCreateBuffer = new StringBuffer("");
            String type = "";
            String tableNameString = "";
            elementName = StringUtil.getUnduplicateProperty(element);
            elementName = StringUtil.firstCharUpper(elementName);
            fos = new FileOutputStream("c:\\jump\\ger\\java\\" + elementName + ".java");
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            tableNameString = "PY_" + StringUtil.getTableNameByClassName(elementName);
            fos2 = new FileOutputStream("c:\\jump\\ger\\db\\" + tableNameString + ".sql");
            bw2 = new BufferedWriter(new OutputStreamWriter(fos2));

            bufferJava.append("import java.util.List;").append("\n").append("public class " + elementName).append("\n").append("{ ").append("\n");
            dbCreateBuffer.append(" CREATE TABLE " + tableNameString).append("\n").append("(").append("\n").append("id VARCHAR2(30)")// 输出主键
                    .append(",\n").append("parent_id VARCHAR2(30)");// 输出主键
            for (Iterator iterator = element.attributeIterator(); iterator.hasNext();) {
                Attribute att = (Attribute) iterator.next();
                String attName = StringUtil.replaceSideLine(att.getName());
                type = "String";
                bufferProperty.append(StringUtil.getDefineString(type, attName));
                bufferGetterSetter.append(StringUtil.getSetterGetterString(type, attName));
                dbCreateBuffer.append(",\n").append(StringUtil.getColumeNameByPropName(attName) + " VARCHAR2(1024)");

            }
            for (Iterator iterator = element.elementIterator(); iterator.hasNext();) {
                Element element2 = (Element) iterator.next();
                String elemName = StringUtil.getUnduplicateProperty(element2);

                if (element2.isTextOnly()) {
                    elemName = StringUtil.replaceSideLine(elemName);
                    if (elemName.equals("photo")) {
                        dbCreateBuffer.append(",\n").append(StringUtil.getColumeNameByPropName(elemName) + " CLOB ");
                    } else {
                        dbCreateBuffer.append(",\n").append(StringUtil.getColumeNameByPropName(elemName) + " VARCHAR2(1024)  ");
                    }

                    type = "String";
                } else {
                    elemName = StringUtil.firstCharUpper(elemName);
                    type = "List<" + elemName + "> ";
                }
                bufferProperty.append(StringUtil.getDefineString(type, elemName));
                bufferGetterSetter.append(StringUtil.getSetterGetterString(type, elemName));
            }
            bufferJava.append(bufferProperty);
            bufferJava.append(bufferGetterSetter);
            bufferJava.append("}");
            //dbCreateBuffer.deleteCharAt(dbCreateBuffer.lastIndexOf(","));
            dbCreateBuffer.append("\n").append(");").append("\n").append(
                    " ALTER TABLE " + tableNameString + " ADD (CONSTRAINT " + StringUtil.getPKName() + " PRIMARY KEY (id));").append("\n").append(
                    " CREATE INDEX " + StringUtil.getIDXName() + " ON " + tableNameString + "(parent_id);");
            //System.out.println("1");
            //System.out.println(dbCreateBuffer.toString());
            bw.write(bufferJava.toString());
            bw2.write(dbCreateBuffer.toString());
            bw.close();
            bw2.close();
            fos.close();
            fos2.close();

        } catch (Exception e) {
            // TODO: handle exception
          e.printStackTrace();
        }

    }

    /**
     * 以下两个路径的文件必须存在
     * @c:\\jump\\ger\\java\\
     * @c:\\jump\\ger\\db\\
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Document document = reader("D:/workspace/count-task/src/main/java/config/data.xml");
            Element root = getRootElement(document);
            String aft= "_da";
            treeWalk(root,aft);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}