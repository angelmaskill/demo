package com.BASETEST.string;

import org.junit.Test;

public class StringTest {
    @Test
    public void testGetString() {
        // TODO Auto-generated method stub
        String str = "A099";
        char[] chars = str.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char cha = chars[i];
            if ((int) cha >= 48 && (int) cha <= 57) {
                index = i;
                break;
            }
        }
        String code = str.substring(0, index);
        String num = str.substring(index, str.length());
        System.out.println(code + (Integer.valueOf(num) + 1));
    }
}
