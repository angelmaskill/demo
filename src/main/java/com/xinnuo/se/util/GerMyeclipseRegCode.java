/**
 * @(#)GerMyeclipseRegCode.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-2-28     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;
/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-2-28
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GerMyeclipseRegCode extends JFrame {

    public GerMyeclipseRegCode() {

        JLabel label = new JLabel("注册名：");
        JLabel label2 = new JLabel("注册码：");
        JLabel label3 = new JLabel("            使用方法：输入任意注册名，点击确定，复制注册码进入MyEclipse6.5Blue");
        JLabel label4 = new JLabel("                         点击MyEclipse菜单->Subscription Information");
        JButton button = new JButton("确定");
        button.setMnemonic(KeyEvent.VK_I);
        final JTextField textField = new JTextField(25);
        final JTextField textField2 = new JTextField(25);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 100));
        // 添加事件处理  
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                String id = "";
                String num = "999";// 许可证数量  
                id = textField.getText();
                textField2.setText(getSerial(id, "100", num, true));
            }

        });
        panel.add(label);
        panel.add(textField);
        panel.add(label2);
        panel.add(textField2);
        panel.add(button);
        panel.add(label3);
        panel.add(label4);
        this.getContentPane().add(panel);

        // 设置窗体属性  

        // this.pack();  
        setSize(500, 200);
        setTitle("MyEclipse6.5注册机(xsky)");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // this.show();  
    }

    public static void main(String[] args) {
        GerMyeclipseRegCode mainWindow = new GerMyeclipseRegCode();
    }

    public static String getSerial(String userId, String version, String licenseNum, boolean selected) {
        Calendar cal = Calendar.getInstance();
        cal.add(1, 3);
        cal.add(6, -1);
        NumberFormat nf = new DecimalFormat("000");
        licenseNum = nf.format(Integer.valueOf(licenseNum));
        String verTime = selected ? (new StringBuffer("-")).append(
                (new SimpleDateFormat("yyMMdd")).format(cal.getTime())).append("0").toString() : "-0912310";
        String type = "YE3MB-";
        String need = (new StringBuffer(String.valueOf(userId.substring(0, 1)))).append(type).append(version).append(
                licenseNum).append(verTime).toString();
        String dx = (new StringBuffer(String.valueOf(need)))
                .append(userId).toString();
        int suf = decode(dx);
        String code = (new StringBuffer(String.valueOf(need))).append(String.valueOf(suf)).toString();
        return change(code);
    }

    private static int decode(String s) {
        int i = 0;
        char ac[] = s.toCharArray();
        int j = 0;
        for (int k = ac.length; j < k; j++)
            i = 31 * i + ac[j];

        return Math.abs(i);
    }

    private static String change(String s) {
        byte abyte0[] = s.getBytes();
        char ac[] = new char[s.length()];
        int i = 0;
        for (int k = abyte0.length; i < k; i++) {
            int j = abyte0[i];
            if (j >= 48 && j <= 57)
                j = ((j - 48) + 5) % 10 + 48;
            else if (j >= 65 && j <= 90)
                j = ((j - 65) + 13) % 26 + 65;
            else if (j >= 97 && j <= 122)
                j = ((j - 97) + 13) % 26 + 97;
            ac[i] = (char) j;
        }

        return String.valueOf(ac);
    }
}  

