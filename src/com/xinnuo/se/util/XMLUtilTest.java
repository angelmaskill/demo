package com.xinnuo.se.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XMLUtilTest {
	

	@Before
	public void setUp() throws Exception {
		Word w = new Word();
		w.setId("12321dsad");
		w.setContent("scsdc");
		
		Word w1 = new Word();
		w1.setId("12321dsadas11");
		w1.setContent("scsdcv21s");
		
		XMLUtil util = new XMLUtil();
		try{
			util.create("Wiki","test.xml");
			util.add(w);
			util.add(w1);
			util.save();
		}finally{
			util.close();
		}
	}

	@After
	public void tearDown() throws Exception {
		//new File("test.xml").delete();
	}
	
	@Test
	public void testCDATA() throws Exception{
		Word w1 = new Word();
		w1.setId("中文");
		w1.setContent("<中文内容/>");
		
		XMLUtil util = new XMLUtil();
		try{
			util.setFieldNameOfCDATA("content");
			util.open("test.xml");
			util.add(w1);
			util.save();
			assertEquals(w1.getContent(),((Word)util.getObjects(new Word(), "//Word[@id='"+w1.getId()+"']").get(0)).getContent());
		}finally{
			util.close();
		}
	}
	
	@Test
	public void update() throws Exception{
		Word w1 = new Word();
		w1.setId("12321dsadas11");
		w1.setContent("benben");
		
		XMLUtil util = new XMLUtil();
		try{
			util.open("test.xml");
			util.update(w1, "id");
			util.save();
			assertEquals(w1.getContent(),((Word)util.getObjects(new Word(), "//Word[@id='"+w1.getId()+"']").get(0)).getContent());
		}finally{
			util.close();
		}
		
	}
	
	@Test
	public void search() throws Exception {
		XMLUtil util = new XMLUtil();
		try {
			util.open("test.xml");
			assertEquals("scsdcv21s",((Word)util.getObjects(new Word(), "//Word[contains(@content,'v21')]").get(0)).getContent());
			assertEquals(2,util.getObjects(new Word(), "//Word[contains(@content,'cs')]").size());
			assertEquals("scsdc",((Word)util.getObjects(new Word()).get(0)).getContent());
			assertEquals("scsdcv21s",((Word)util.getObjects(new Word()).get(1)).getContent());
		} finally{
			util.close();
		}
	}
   
	@Test
	public void searchByKey() throws Exception {
		XMLUtil util = new XMLUtil();
		Word w = new Word();
		w.setId("12321dsadas11");
		try {
			util.open("test.xml");
			assertEquals("scsdcv21s",((Word)util.getObjectByKey(w, "id").get(0)).getContent());
		} finally{
			util.close();
		}
	}
}
