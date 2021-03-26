package com.xml.xml2java.jaxb;


/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-5-27     Administrator    Created
 **********************************************
 */

import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-5-27
 */

/*
 * Unmarshaller 类使客户端应用程序能够将 XML 数据转换为 Java 内容对象树。
    备注：marshal(序列化、排列、整理)
    Marshaller 类使客户端应用程序能够将 Java 内容树转换回 XML 数据。

    JAXB（Java Architecture for XML Binding) 是一个业界的标准，是一项可以根据XML Schema产生Java类的技术。
    该过程中，JAXB也提供了将XML实例文档反向生成Java对象树的方法，并能将Java对象树的内容重新写到XML实例文档。
    从另一方面来讲，JAXB提供了快速而简便的方法将XML模式绑定到Java表示，从而使得Java开发者在Java应用程序中能方便地结合XML数据和处理函数。
    JAXBContext 类提供到 JAXB API 的客户端入口点。它提供了管理实现 JAXB 绑定框架操作所需的 XML/Java 绑定信息的抽象，这些操作包括：解组、编组和验证。
        
*/


public class MyTest extends TestCase {

    /**
     * @此方法把java对象实例转成xml
     */
    /*public void testJaxbToXml(){
      
      try {
        
        Student s1=new Student("1", "ggd1", 21);
        Student s2=new Student("2", "ggd2", 21);
        Student s3=new Student("3", "ggd3", 21);
        Student s4=new Student("4", "ggd4", 21);
        Student s5=new Student("5", "ggd5", 26);
        Student s6=new Student("6", "ggd6", 21);
        Student s7=new Student("7", "ggd7", 26);
        
        List<Student> list=new ArrayList<Student>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
         
        ClassRoom classRoom=new ClassRoom("131123-1", "bestClassRoom", list);
        
        JAXBContext ct=JAXBContext.newInstance(ClassRoom.class);
        Marshaller marshaller=    ct.createMarshaller();
        marshaller.marshal(classRoom, System.out);
        
      } catch (JAXBException e) {
        e.printStackTrace();
      }
      
    }*/


    /**
     * @此方法，把xml转成对象实例
     */
    public void testjaxbToObject() {
        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><classRoom><classid>131123-1</classid><classname" +
                    ">bestClassRoom</classname><list><age>21</age><id>1</id><name>ggd1</name></list><list><age>21</age><id>2</id><name>ggd2</name" +
                    "></list><list><age>21</age><id>3</id><name>ggd3</name></list><list><age>21</age><id>4</id><name>ggd4</name></list><list><age" +
                    ">26</age><id>5</id><name>ggd5</name></list><list><age>21</age><id>6</id><name>ggd6</name></list><list><age>26</age><id>7</id" +
                    "><name>ggd7</name></list></classRoom>";

            JAXBContext jaxbContext = JAXBContext.newInstance(ClassRoom.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ClassRoom classRoom = (ClassRoom) unmarshaller.unmarshal(new StringReader(xml));

            System.out.println(classRoom.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
