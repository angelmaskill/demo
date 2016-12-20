package com.file;

/**
 * @(#)TestFile.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-7-27     Administrator    Created
 **********************************************
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-7-27
 */
public class TestFile {
    public static void main(String[] args) throws IOException {   
        // 1.获取当前目录   
        // 当前目录的URI   
        System.out.println(Thread.currentThread().getContextClassLoader().   
                getResource(""));   
        System.out.println(ClassLoader.getSystemResource(""));   
        // 当前目录绝对路径   
        System.out.println(new File("").getAbsolutePath());   
        System.out.println(new File("").getCanonicalPath());   
        System.out.println(System.getProperty("user.dir"));   
           
        // 2.文件的创建/删除   
        String pwd=System.getProperty("user.dir");   
        File f = new File(pwd, "1.txt");   
        if (f.exists()) {   
            f.delete();   
            System.out.println(pwd + "\\1.txt 存在，已删除。");   
        } else {   
            f.createNewFile();   
            System.out.println(pwd + "\\1.txt 不存在，已建立。");   
        }   
                   
        // 3.目录的创建/删除   
        pwd = pwd + "\\Sub"; // 将要建立的目录路径   
        File d = new File(pwd); // 建立代表Sub目录的File对象，并得到它的一个引用   
        if (d.exists()) { // 检查Sub目录是否存在   
            d.delete();   
            System.out.println("Sub目录存在，已删除");   
        } else {   
            d.mkdir(); // 建立Sub目录   
            System.out.println("Sub目录不存在，已建立");   
        }          
           
        // 4.获取目录下的所有子目录和文件   
        File d1=new File(System.getProperty("user.dir")); // 建立当前目录中文件的File对象    
        File list[]=d1.listFiles(); // 取得代表目录中所有文件的File对象数组    
        System.out.println(pwd+" 目录下的内容:");   
        for(int i=0;i<list.length;i++)   
            if(list[i].isDirectory()){   
                String tmp=String.format("%s       <DIR>   %s",   
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(   
                        new Date(list[i].lastModified())).toString(),list[i].getName());   
                System.out.println(tmp);   
            }   
            else if(list[i].isFile()){   
                String tmp=String.format("%s %5s bytes   %s",   
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(   
                        new Date(list[i].lastModified())).toString(),list[i].length(),   
                        list[i].getName());   
                System.out.println(tmp);   
                   
            }   
  
        // 5.写文件   
        pwd=System.getProperty("user.dir");   
        FileWriter fw = new FileWriter(pwd + "\\2.txt");   
        fw.write("aaaa");   
        fw.write("bbbb"+System.getProperty("line.separator"));   
        fw.write("cccc");          
        fw.close();   
           
        fw = new FileWriter(pwd + "\\2.txt", true); // append=true   
        BufferedWriter bw=new BufferedWriter(fw);    
        bw.write("dddd");   
        bw.newLine();   
        bw.write("eeee");   
        bw.close();        
           
           
        // 6.读文件   
        FileReader fr = new FileReader(pwd + "\\2.txt");   
        BufferedReader br = new BufferedReader(fr);   
        String Line = br.readLine();   
        while (Line != null) {   
            System.out.println(Line);   
            Line = br.readLine();   
        }   
        br.close();   
        fr.close();   
           
        readFromConsole();   
           
    }   
       
    // 从控制台读入   
    public static void readFromConsole() {   
        try {   
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));   
            System.out.print("input sth:");   
            String temp = in.readLine();   
            System.out.print("your input is: \""+temp+"\"");   
        } catch (IOException ioe) {   
            ioe.printStackTrace();   
        }   
    }   
       
    // 简单的文件复制   
    public void copyFile(String src,String dest) throws IOException{   
        FileInputStream in=new FileInputStream(src);   
        File file=new File(dest);   
        if(!file.exists())   
            file.createNewFile();   
        FileOutputStream out=new FileOutputStream(file);   
        int c;   
        byte buffer[]=new byte[1024];   
        while((c=in.read(buffer))!=-1){   
            for(int i=0;i<c;i++)   
                out.write(buffer[i]);           
        }   
        in.close();   
        out.close();   
    }   
  
    // 删除非空文件夹   
    public static boolean delDir(String path) throws IOException{   
        if(path == null || path == "")   
            return false;   
           
        File rootDir=new File(path);   
        File[] fileLst=rootDir.listFiles();   
        for(int i=0; i<fileLst.length; i++) {   
            if(fileLst[i].isDirectory()){   
                if(!delDir(fileLst[i].toString()))   
                    return false;   
                System.out.println("dir: "+fileLst[i]+" is deleted.");   
            }              
            else if(fileLst[i].isFile()){   
                if(!fileLst[i].delete())   
                    return false;   
                System.out.println("file: "+fileLst[i]+" is deleted.");   
            }   
        }   
        if(!rootDir.delete())   
            return false;   
        return true;   
    }   
}
