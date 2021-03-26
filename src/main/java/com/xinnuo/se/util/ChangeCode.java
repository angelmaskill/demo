package com.xinnuo.se.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;

public class ChangeCode {
    public static String utf8ToBase64(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }

        String st = Base64.encode(str.getBytes("gbk"));
        return st;
    }

    public static String base64ToUtf8(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        String st = new String(Base64.decode(str), "utf-8");
        return st;
    }

    public static String utf8ToGBK(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }

        String st = new String(str.getBytes("utf-8"), "GBK");
        return st;
    }

    public static String base64ToGBK(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }

        String st = new String(Base64.decode(str), "GBK");
        return st;
    }

    public static void main(String[] args) {
        try {
            System.out.println(utf8ToBase64("4548500041741"));
            System.out.println(base64ToUtf8("NDU0ODUwMDA0MTc0MQ=="));
            System.out.println(utf8ToBase64("王玉玥"));
            System.out.println(base64ToGBK("zfXT8ato"));

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}