/**
 * @(#)ClientTest.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-8-26     Administrator    Created
 **********************************************
 */

package com.freemarker;

import freemarker.template.TemplateException;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-26
 */
public class ClientTest extends TestCase {
    public static List<User> initUserList() {

        User user1 = new User();
        user1.setUserName("张三");
        user1.setUserPassword("123");
        user1.setAge(20);

        User user2 = new User();
        user2.setUserName("李四");
        user2.setUserPassword("123");
        user2.setAge(22);

        User user3 = new User();
        user3.setUserName("王五");
        user3.setUserPassword("123");
        user3.setAge(21);
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }

    public static void main(String[] args) throws IOException, TemplateException {
        List<User> list = ClientTest.initUserList();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("userList", list);
        System.out.println(MessageTempleteManager.process("test1.ftl", root));

    }

    public void testNumber() throws Exception {
        System.out.println(MessageTempleteManager.process("test2.ftl", null));
    }


}
