package com.xml.dom4j.xpath;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseXml {

    private String filePath;

    public Document document;

    public ParseXml() {
    }

    public ParseXml(String filePath) throws Exception {
        this.filePath = filePath;
        this.load(this.filePath);
    }

    public void load(String filestr) throws Exception {
        File file = new File(filestr);
        if (file.exists()) {//判断传入是个文件路径
            SAXReader saxReader = new SAXReader();
            try {
                document = saxReader.read(file);
            } catch (DocumentException e) {
                throw new Exception("文件加载异常");
            }
        } else {//如果不是文件路径，判断是否是xml字符串
            try {
                document = DocumentHelper.parseText(filestr);
            } catch (DocumentException e) {
                throw new Exception("文件格式异常");
            }
        }
    }

    public Node getElementObject(String elementPath) {
        return (Node) document.selectSingleNode(elementPath);
    }

    @SuppressWarnings("unchecked")
    public List<Node> getElementObjects(String elementPath) {
        return document.selectNodes(elementPath);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getChildrenInfoByElement(Element element) {
        Map<String, String> map = new HashMap<String, String>();
        List<Element> children = element.elements();
        for (Element e : children) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    public boolean isExist(String elementPath) {
        boolean flag = false;
        Node element = this.getElementObject(elementPath);
        if (element != null)
            flag = true;
        return flag;
    }

    public String getElementText(String elementPath) {
        Node element = this.getElementObject(elementPath);
        if (element != null) {
            return element.getText().trim();
        } else {
            return null;
        }
    }

    /**
     * 对指定的节点增加属性和文本
     *
     * @param elmt
     * @param name
     * @param value
     * @param text
     * @return
     */
    public Element addAttribute(Element elmt, String name, String value) {
        elmt.addAttribute(name, value);
        return elmt;
    }

    /**
     * 修改指定节点的属性和文本
     *
     * @param elmt
     * @param name
     * @param value
     * @param text
     * @return
     */
    public Element setAttribute(Element elmt, String name, String value) {
        Attribute attribute = elmt.attribute(name);
        //attribute.setName(name);
        attribute.setValue(value);
        List list = new ArrayList();
        list.add(attribute);
        elmt.setAttributes(list);
        return elmt;
    }

    /**
     * 删除指定节点的指定属性
     *
     * @param elmt
     * @param name
     * @return
     */
    public Element removeAttribute(Element elmt, String name) {
        elmt.remove(elmt.attribute(name));
        return elmt;
    }

    /**
     * 将文档输出到文件保存，可指定格式化输出,可指定字符编码。
     *
     * @param document
     * @param outputFile
     */
    public void saveDocument(Document doc, String outputPath) {
        // 输出文件  
        File outputFile = new File(outputPath);
        try {
            // 美化格式  
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 指定XML编码,不指定的话，默认为UTF-8  
            format.setEncoding("UTF-8");
            XMLWriter output = new XMLWriter(new FileWriter(outputFile), format);
            output.write(doc);
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        ParseXml px = new ParseXml("src/com/xml/studentInfo.xml");
        String info = px.getElementText("/students/student[name='崔卫兵']/@age");
        info = px.getElementText("/students/student[@age=45]/notes");
        info = px.getElementText("/students/student[last()-2]/notes");
        System.out.println(info);
    }

}
