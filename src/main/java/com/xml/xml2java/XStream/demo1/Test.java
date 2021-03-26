package com.xml.xml2java.XStream.demo1;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author leiwei
 * <p>
 * 将 java 对象转换成 xml 或 将 xml 转换成 java 对象
 * <p>
 * 需要使用到 xstream - 1.3.jar 、 xpp3_min - 1.1.4c.jar
 */
public class Test {

    public static Persons getPersons() {
        // 构建 person 对象，动态的可以从数据库中取出 
        Person p = new Person(1, "leiwei", 22);
        Person p2 = new Person(2, "jinlei", 22);

        // 把对象放入 person 集合中 
        List<Person> listPerson = new ArrayList<Person>();
        listPerson.add(p);
        listPerson.add(p2);
        // 构造出一个 persons 对象 
        Persons persons = new Persons(listPerson);

        return persons;
    }

    public static void main(String[] args) {

        // 取得 persons 对象 
        Persons persons = getPersons();

        // 给每一个实体类取一个别名，作为当前实体的根标签 
        XStream stream = new XStream();
        stream.alias("persons", Persons.class);
        stream.alias("person", Person.class);

        // 将 java 对象转换成 xml 
        String obj2xml = stream.toXML(persons);
        System.out.println(obj2xml.toString());

        // 将 xml 转换成 java 对象 
        Persons xml2obj = (Persons) stream.fromXML(obj2xml);

        for (Iterator iterator = xml2obj.getListPerson().iterator(); iterator.hasNext(); ) {
            Person person = (Person) iterator.next();

            System.out.println(person.getIdCard() + "--" + person.getName() + "--" + person.getAge());
        }

    }
}
