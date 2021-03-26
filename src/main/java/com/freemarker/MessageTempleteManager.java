/**
 * @(#)MessageTempleteManager.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-7-23     Administrator    Created
 **********************************************
 */

package com.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-7-23
 */

public class MessageTempleteManager {
    private static MessageTempleteManager tplm = null;
    private Configuration cfg = null;

    private MessageTempleteManager() {
        cfg = new Configuration();
        try {
            cfg.setClassForTemplateLoading(this.getClass(), "/com/freemarker/");
        } catch (Exception e) {

        }
    }

    private static Template getTemplate(String name) throws IOException {
        if (tplm == null) {
            tplm = new MessageTempleteManager();
        }
        return tplm.cfg.getTemplate(name, "UTF-8");
    }

    public static String process(String templatefile, Map param) throws IOException, TemplateException {
        Template template = MessageTempleteManager.getTemplate(templatefile);
        StringWriter sw = new StringWriter();
        template.process(param, sw);
        return sw.toString();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("path", "111111");

        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("name", "张三");
        userMap.put("age", "1");
        userMap.put("sex", "男");
        userMap.put("class", "2班");

        Map<String, String> userMap2 = new HashMap<String, String>();
        userMap2.put("name", "小红");
        userMap2.put("age", "2");
        userMap2.put("sex", "女");
        userMap2.put("class", "2班");
        List userList = new LinkedList();
        userList.add(userMap2);
        userList.add(userMap2);
        param.put("userList", userList);
        System.out.println(MessageTempleteManager.process("test.ftl", param));

    }
}
