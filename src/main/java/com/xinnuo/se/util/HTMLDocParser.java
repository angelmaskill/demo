/*    */ package com.xinnuo.se.util;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import org.apache.lucene.demo.html.HTMLParser;
/*    */ 
/*    */ public class HTMLDocParser
/*    */ {
/*    */   private String htmlPath;
/*    */   private HTMLParser htmlParser;
/*    */ 
/*    */   public HTMLDocParser(String htmlPath)
/*    */   {
/* 23 */     this.htmlPath = htmlPath;
/* 24 */     initHtmlParser();
/*    */   }
/*    */ 
/*    */   private void initHtmlParser() {
/* 28 */     InputStream inputStream = null;
/*    */     try {
/* 30 */       inputStream = new FileInputStream(this.htmlPath);
/*    */     } catch (FileNotFoundException e) {
/* 32 */       e.printStackTrace();
/*    */     }
/* 34 */     if (inputStream != null)
/*    */       try {
/* 36 */         this.htmlParser = new HTMLParser(new InputStreamReader(inputStream, "utf-8"));
/*    */       } catch (UnsupportedEncodingException e) {
/* 38 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public String getTitle()
/*    */   {
/* 48 */     if (this.htmlParser != null) {
/*    */       try {
/* 50 */         return this.htmlParser.getTitle();
/*    */       } catch (IOException e) {
/* 52 */         e.printStackTrace();
/*    */       } catch (InterruptedException e) {
/* 54 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 57 */     return "";
/*    */   }
/*    */ 
/*    */   public Reader getContent()
/*    */   {
/* 65 */     if (this.htmlParser != null) {
/*    */       try {
/* 67 */         return this.htmlParser.getReader();
/*    */       } catch (IOException e) {
/* 69 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 72 */     return null;
/*    */   }
/*    */ 
/*    */   public String getPath() {
/* 76 */     return this.htmlPath;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\桌面\lucene\lucene-test\WEB-INF\classes\
 * Qualified Name:     com.xinnuo.se.util.HTMLDocParser
 * JD-Core Version:    0.6.0
 */