/**
 * @(#)Test3DES.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-11-14     Administrator    Created
 **********************************************
 */

package com.enc;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Class description goes here.
 * JAVA 算法调用范例 
 * @author Administrator
 * @since 2014-11-14
 */
public class Test3DES {
    public static void main(String[] args) {

        String key = "1234567890123456";
        String password = "1108927156374039";
        System.out.println("key=" + key + ",password=" + password);
        System.out.println();
        System.out.println("----------示例开始：使用java写的算法加密解密-----------");
        try {
            String encrypt = "";
            String decrypt = "";
            byte[] bkey = EncryptUtils.GetKeyBytes(key);
            encrypt = EncryptUtils.byte2Base64(EncryptUtils.encryptMode(bkey, password.getBytes()));//base64加密
            System.out.println("用预转换密钥算加密结果=" + encrypt);
            System.out.println("加密后base64表示=" + EncryptUtils.byte2hex(Base64.decode(encrypt)));
            System.out.println("调用原始密钥算加密结果=" + EncryptUtils.Encrypt3DES(password, key));
            try {
                decrypt = new String(EncryptUtils.decryptMode(bkey, Base64.decode(encrypt)));
                System.out.println("用预转换密钥算解密结果=" + decrypt);
                System.out.println("调用原始密钥算解密结果=" + EncryptUtils.Decrypt3DES(encrypt, key));
            } catch (Exception ex) {
                System.out.println("Exception:" + ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
        System.out.println("----------示例结束：使用java写的算法加密解密-----------");
    }

}
