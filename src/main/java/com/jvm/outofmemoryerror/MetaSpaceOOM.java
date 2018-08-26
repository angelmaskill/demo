package com.jvm.outofmemoryerror;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.xml.xml2java.jaxb.ClassRoom;

/**
 * <pre>
 * 场景还原:
 * 加载外部的jaxb-impl jar ,版本为: 1.1 and 2.1
 * 
 * 参数:
 * (1)查看gc:
 * -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\
 * (2)查看类加载:
 * -verbose:class -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\
 * 
 * 解释:
 * 这个问题已经作为一个bug反馈到了jdk8 网站上: https://bugs.openjdk.java.net/browse/JDK-8146539
 * Jaxb now part of JDK but if you have external jaxb-impl jar ( tried with 1.1
 * and 2.1) in the classpath then metaspace is growing whenever JAXBContext
 * invoked. Due to conflict the finalizer method throwing exception which
 * prevent the GC to collect the memory. If I remove the jaxb-impl2.1.jar from
 * the classpath then I donot see this issue.
 * 
 * 升级到JDK1.8的时候，由于jaxb已经是JDK的一部分，这个时候由于classpath中还有jaxb-impl.jar将引发jar
 * 包冲突,这种冲突导致回收方法抛出异常从而无法回收metaspace,导致jaxbContext无法被GC,移除外部jaxb-impl2.1.jar
 * 会解决问题.
 * 
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class MetaSpaceOOM {
	public static void main(String[] args) throws Exception {
		testMetaSpaceOOM();
	}

	public static void testMetaSpaceOOM() throws Exception {
		for (int i = 0; i < 10000; i++) {
			System.out.println("==============================" + i);
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><classRoom><classid>131123-1</classid><classname>bestClassRoom</classname><list><age>21</age><id>1</id><name>ggd1</name></list><list><age>21</age><id>2</id><name>ggd2</name></list><list><age>21</age><id>3</id><name>ggd3</name></list><list><age>21</age><id>4</id><name>ggd4</name></list><list><age>26</age><id>5</id><name>ggd5</name></list><list><age>21</age><id>6</id><name>ggd6</name></list><list><age>26</age><id>7</id><name>ggd7</name></list></classRoom>";

			JAXBContext jaxbContext = JAXBContext.newInstance(ClassRoom.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ClassRoom classRoom = (ClassRoom) unmarshaller.unmarshal(new StringReader(xml));

			System.out.println(classRoom.toString());
			String ss = null;
			for (int j = 0; j < 10000; j++) {
				ss = new String("my test");

			}
			System.out.println("===============" + ss);
			Thread.sleep(1000);
		}

	}
}
