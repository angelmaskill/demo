/**
 * @(#)PropertyEditor.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-10-21     Administrator    Created
 **********************************************
 */

package com.property;

import com.xinnuo.se.util.PathUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-10-21
 */
public class PropertyEditor {
    private static String path = "";
    private static Properties prop = new Properties();// 属性集合对象


    @BeforeClass
    public static void testGetPath() throws Exception {
        PropertyEditor pe = new PropertyEditor();
        path = PathUtil.getPackagePath(pe);
        System.out.println("getPackagePath :" + path);
        path = PathUtil.getProjectRootPath();
        System.out.println("getProjectRootPath :" + path);
        path = PathUtil.getRootClassPath();
        System.out.println("getRootClassPath :" + path);
        path = PathUtil.getRootPath();
        System.out.println("getRootPath :" + path);
        path = PathUtil.getWebInfPath();
        System.out.println("getWebInfPath :" + path);
        path = PathUtil.getWebRootPath();
        System.out.println("getWebRootPath :" + path);
        path = PathUtil.getClassPath(pe);
        System.out.println("getClassPath :" + path);
        path = path + "db.properties";
        System.out.println("path  ===" + path);

        /*
         * 加载Properties 对象
         */
        FileInputStream fis = new FileInputStream(path);// 属性文件输入流
        prop.load(fis);// 将属性文件流装载到Properties对象中
        fis.close();// 关闭流

    }

    @Test
    public void testGet() throws Exception {
        // 获取属性值，sitename已在文件中定义
        System.out.println("获取属性值：base_oracle_url=" + prop.getProperty("base_oracle_url"));
        // 获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件
        System.out.println("获取属性值：log_oracle_url=" + prop.getProperty("log_oracle_url", "中国"));
    }

    @Test
    public void testSetPro() throws Exception {
        try {
            // 修改sitename的属性值
            prop.setProperty("base_oracle_url", "sb");
            // 添加一个新的属性studio
            prop.setProperty("log_oracle_url", "sb2");
            // 文件输出流
            FileOutputStream fos = new FileOutputStream(path);
            // 将Properties集合保存到流中
            prop.store(fos, "Copyright (c) Boxcode Studio");
            fos.close();// 关闭流

            System.out.println("修改之后：" + prop.get("base_oracle_url"));
            System.out.println("修改之后：" + prop.get("log_oracle_url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
