package com.designpattern.Chapter04SimpleFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESEncrypt {
    public static void main(String args[]) {
        String codeStringBegin = "Sunny Liu";  //Ҫ���ܵ�����
        String codeStringEnd = null;             //���ܺ������
        String decodeString = null;              //���Ľ��ܺ�õ�������
        String cipherType = "DESede"; //�����㷨���ͣ�������ΪDES��DESede��AES���ַ���
        int keyLength = 112; //������Կ����
        try {
            //��ȡ��Կ������
            KeyGenerator keyGen = KeyGenerator.getInstance(cipherType);
            //��ʼ����Կ����������ͬ�ļ����㷨����Կ���ȿ��ܲ�ͬ
            keyGen.init(keyLength);
            //������Կ
            SecretKey key = keyGen.generateKey();

            //�õ���Կ�ֽ���
            byte[] keyByte = key.getEncoded();
            //�����Կ���ֽ���
            System.out.println("��Կ�ǣ�");
            for (int i = 0; i < keyByte.length; i++) {
                System.out.print(keyByte[i] + ",");
            }
            System.out.println("");
            //����������
            Cipher cp = Cipher.getInstance(cipherType);
            //��ʼ��������
            cp.init(Cipher.ENCRYPT_MODE, key);
            System.out.println("Ҫ���ܵ��ַ����ǣ�" + codeStringBegin);
            byte[] codeStringByte = codeStringBegin.getBytes("UTF8");
            System.out.println("Ҫ���ܵ��ַ�����Ӧ���ֽ����ǣ�");
            for (int i = 0; i < codeStringByte.length; i++) {
                System.out.print(codeStringByte[i] + ",");
            }
            System.out.println("");
            //��ʼ����
            byte[] codeStringByteEnd = cp.doFinal(codeStringByte);
            System.out.println("���ܺ���ַ�����Ӧ���ֽ����ǣ�");
            for (int i = 0; i < codeStringByteEnd.length; i++) {
                System.out.print(codeStringByteEnd[i] + ",");
            }
            System.out.println("");
            codeStringEnd = new String(codeStringByteEnd);
            System.out.println("���ܺ���ַ����ǣ�" + codeStringEnd);
            System.out.println("");
            //���³�ʼ��������
            cp.init(Cipher.DECRYPT_MODE, key);
            //��ʼ����
            byte[] decodeStringByteEnd = cp.doFinal(codeStringByteEnd);
            System.out.println("���ܺ���ַ�����Ӧ���ֽ����ǣ�");
            for (int i = 0; i < decodeStringByteEnd.length; i++) {
                System.out.print(decodeStringByteEnd[i] + ",");
            }
            System.out.println("");
            decodeString = new String(decodeStringByteEnd);
            System.out.println("���ܺ���ַ����ǣ�" + decodeString);
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
