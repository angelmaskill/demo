package com.xml.dom4j.xpath;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jReadExmple {

    public static void main(String[] args) {
        try {
            // 获取解析完后的解析信息
            HashMap<String, String> hashMap;
            Dom4jReadExmple drb = new Dom4jReadExmple();
            // 利用XPath操作XML文件，获取想要的属性值
            hashMap = new HashMap<String, String>();
            drb.getSelectedNodeValue("src/com/xml/studentInfo.xml", hashMap);
            System.out.println("崔卫兵-age:" + hashMap.get("崔卫兵-age"));
            System.out.println("cwb-age:" + hashMap.get("cwb-age"));
            System.out.println("cwb-college:" + hashMap.get("cwb-college"));
            System.out.println("cwb-college-leader:" + hashMap.get("cwb-college-leader"));
            System.out.println("lxx-college:" + hashMap.get("lxx-college"));
            System.out.println("lxx-college-leader:" + hashMap.get("lxx-college-leader"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getSelectedNodeValue(String filename, HashMap<String, String> hm) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(filename));
            // 获取学生姓名为"崔卫兵"的年龄
            List list = document.selectNodes("/students/student[name='崔卫兵']/@age");
            Iterator iter = list.iterator();
            if (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                hm.put("崔卫兵-" + attribute.getName(), attribute.getValue());
            } else {
                hm.put("崔卫兵-age", "20");
            }

            // 获取学生姓名为"崔卫兵"的年龄
            list = document.selectNodes("/students/student[name='cwb']/@age");
            iter = list.iterator();
            if (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                hm.put("cwb-" + attribute.getName(), attribute.getValue());
            } else {
                hm.put("cwb-age", "20");
            }

            // 获取学生姓名为"cwb"所在的学院名称
            list = document.selectNodes("/students/student[name='cwb']/college");
            iter = list.iterator();
            if (iter.hasNext()) {
                Element element = (Element) iter.next();
                String name = element.getName();
                String value = element.getText();
                hm.put("cwb-" + name, value);
            }

            // 获取学生姓名为"cwb"所在学院的领导
            list = document.selectNodes("/students/student[name='cwb']/college/@leader");
            iter = list.iterator();
            if (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                hm.put("cwb-college-" + attribute.getName(), attribute.getValue());
            } else {
                hm.put("cwb-college-leader", "leader");
            }

            // 获取学生姓名为"lxx"所在的学院名称
            list = document.selectNodes("/students/student[name='lxx']/college");
            iter = list.iterator();
            if (iter.hasNext()) {
                Element element = (Element) iter.next();
                String name = element.getName();
                String value = element.getText();
                hm.put("lxx-" + name, value);
            }

            // 获取学生姓名为"lxx"所在学院的领导
            list = document.selectNodes("/students/student[name='lxx']/college/@leader");
            iter = list.iterator();
            if (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                hm.put("lxx-college-" + attribute.getName(), attribute.getValue());
            } else {
                hm.put("lxx-college-leader", "leader");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
