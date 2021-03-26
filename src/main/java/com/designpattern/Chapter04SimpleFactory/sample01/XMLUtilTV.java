package com.designpattern.Chapter04SimpleFactory.sample01;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtilTV {
    //�÷������ڴ�XML�����ļ�����ȡƷ�����ƣ������ظ�Ʒ������
    public static String getBrandName() {
        try {
            //�����ĵ�����
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("configTV.xml"));

            //��ȡ����Ʒ�����Ƶ��ı��ڵ�
            NodeList nl = doc.getElementsByTagName("brandName");
            Node classNode = nl.item(0).getFirstChild();
            String brandName = classNode.getNodeValue().trim();
            return brandName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
