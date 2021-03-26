/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mina.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Tools {
    public static Logger logger = Logger.getLogger(Tools.class);

    public static String turnISNLength(String data, int len) {
        for (int i = data.length(); i < len; i = i + 2) {
            data = "20" + data;
        }
        return data;
    }

    /*
     * CRC16
     */
    public static String CRC16(String str) {

        byte[] data = null;
        /*
         * CRC
         */
        char crc_lo[] = {0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
                0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01,
                0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
                0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00,
                0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80,
                0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00,
                0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
                0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81,
                0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00,
                0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
                0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81,
                0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01,
                0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
                0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
                0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
                0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81,
                0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00,
                0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
                0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
                0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40};

        /* CRC 高位字节值表 */
        char crc_hi[] = {0x00, 0xC0, 0xC1, 0x01, 0xC3, 0x03, 0x02, 0xC2, 0xC6,
                0x06, 0x07, 0xC7, 0x05, 0xC5, 0xC4, 0x04, 0xCC, 0x0C, 0x0D,
                0xCD, 0x0F, 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB, 0x0B, 0xC9,
                0x09, 0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB, 0xDA,
                0x1A, 0x1E, 0xDE, 0xDF, 0x1F, 0xDD, 0x1D, 0x1C, 0xDC, 0x14,
                0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13,
                0xD3, 0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33,
                0xF3, 0xF2, 0x32, 0x36, 0xF6, 0xF7, 0x37, 0xF5, 0x35, 0x34,
                0xF4, 0x3C, 0xFC, 0xFD, 0x3D, 0xFF, 0x3F, 0x3E, 0xFE, 0xFA,
                0x3A, 0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38, 0x28, 0xE8, 0xE9,
                0x29, 0xEB, 0x2B, 0x2A, 0xEA, 0xEE, 0x2E, 0x2F, 0xEF, 0x2D,
                0xED, 0xEC, 0x2C, 0xE4, 0x24, 0x25, 0xE5, 0x27, 0xE7, 0xE6,
                0x26, 0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0, 0xA0,
                0x60, 0x61, 0xA1, 0x63, 0xA3, 0xA2, 0x62, 0x66, 0xA6, 0xA7,
                0x67, 0xA5, 0x65, 0x64, 0xA4, 0x6C, 0xAC, 0xAD, 0x6D, 0xAF,
                0x6F, 0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8,
                0x68, 0x78, 0xB8, 0xB9, 0x79, 0xBB, 0x7B, 0x7A, 0xBA, 0xBE,
                0x7E, 0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4, 0x74, 0x75,
                0xB5, 0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1,
                0x71, 0x70, 0xB0, 0x50, 0x90, 0x91, 0x51, 0x93, 0x53, 0x52,
                0x92, 0x96, 0x56, 0x57, 0x97, 0x55, 0x95, 0x94, 0x54, 0x9C,
                0x5C, 0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E, 0x5A, 0x9A, 0x9B,
                0x5B, 0x99, 0x59, 0x58, 0x98, 0x88, 0x48, 0x49, 0x89, 0x4B,
                0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D, 0x4D, 0x4C,
                0x8C, 0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82,
                0x42, 0x43, 0x83, 0x41, 0x81, 0x80, 0x40};

        // short crc_x = 0;

        char crch = 0x00; // ----init
        char crcl = 0x00;
        int i, index;
        data = HexString2Bytes(str);
        for (i = 0; i < str.length() / 2; i++) {
            index = crcl ^ data[i];
            if (index < 0) {
                index += 256;
            }
            crcl = (char) (crch ^ crc_lo[index]);
            crch = crc_hi[index];
        }
        String sh = Integer.toHexString(crch);
        String sl = Integer.toHexString(crcl);
        if (Integer.toHexString(crch).length() == 1)
            sh = "0" + Integer.toHexString(crch);
        if (Integer.toHexString(crcl).length() == 1)
            sl = "0" + Integer.toHexString(crcl);
        // sprintf(data, "H,L=%02X,%02X", crch, crcl);
        // [color=#FF0000]这里我如何实现上面注释掉的这句话？？[/color]
        return (sl + sh).toUpperCase();

    }

    private static int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }

        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }

        return (c - '0') & 0x0f;
    }

    public static String tunTime(String date) {
        String ttime = "";
        int year = (int) ((Integer.parseInt(date.substring(2, 4)) << 26));
        int month = (int) ((Integer.parseInt(date.substring(4, 6)) << 22));
        int day = (int) ((Integer.parseInt(date.substring(6, 8)) << 17));
        int hour = (int) ((Integer.parseInt(date.substring(8, 10)) << 12));
        int minute = (int) ((Integer.parseInt(date.substring(10, 12)) << 6));
        int second = (int) ((Integer.parseInt(date.substring(12, 14))));
        ttime = Long.toHexString(year + month + day + hour + minute + second);// +
        ttime = Tools.turnLength(ttime, 8);
        return ttime;
    }

    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;

        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);

            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }

        return b;
    }

    /*
     *
     */
    public static long getTimeDifference(Date newTime, Date oldTime) {
        return (newTime.getTime() - oldTime.getTime()) / 1000;
    }

    /*
     *
     */
    public static String dateToStr(Date date, int type) {
        SimpleDateFormat format;
        if (type == 0) {
            format = new SimpleDateFormat("yyyyMMdd");
        } else if (type == 1) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else if (type == 3) {
            format = new SimpleDateFormat("yyyyMMddHHmmss");
        } else if (type == 4) {
            format = new SimpleDateFormat("yyMMddHHmmss");
        } else if (type == 5) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            format = new SimpleDateFormat("HH:mm:ss");
        }

        return format.format(date);
    }

    /*
     *
     */
    public static Date strToDate(String str, int type) {
        SimpleDateFormat format = null;
        if (type == 0) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (type == 1) {

        }
        try {
            return format.parse(str);
        } catch (ParseException ex) {
            logger.warn("Tools strToDate exception:" + ex);
        }
        return (new Date());
    }

    /*
     **/
    public static String turnLength(String str, int len) {

        for (int i = str.length(); i < len; i++) {
            str = "0" + str;
        }
        return str;
    }

    /*
     *
     *
     */
    public static String addDateDays(int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.DATE, days);
        Date date = new Date(calendar.getTime().getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //
    public static String TurnBCDTime(int Dth) {
        String strturnST;
        //
        GregorianCalendar calendar = new GregorianCalendar();
        strturnST = Integer.toHexString(calendar.YEAR * 2 ^ 26 + calendar.MONTH
                * 2 ^ 22 + Dth * 2 ^ 17 + calendar.HOUR * 2 ^ 12
                + calendar.MINUTE * 2 ^ 6 + calendar.SECOND);
        for (int i = 0; i < strturnST.length(); i++) {
            strturnST = "0" + strturnST;
        }
        return strturnST;
    }

    /*
     *字符串轉換為十六進制的ascii碼
     *  */
    public static String TurnISN(String str) {
        String str1 = "";
        byte[] b = null;
        try {
            b = str.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            System.out.println("异常信息(ControllerReport TurnISN)"
                    + e.getMessage());
        }
        for (int i = 0, max = b.length; i < max; i++) {
            str1 += Integer.toHexString(b[i] & 0xff);

        }
        // System.out.println("]");
        return str1.toUpperCase();
    }

    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: turnDataLen
     * @Description: TODO(转换长度 ， 不够的话后面补空格ascii的十六进制)
     * @author zhaopengfei@timaes.com
     * @date 2015-2-6下午03:10:58
     */
    public static String turnDataLen(String str, int len) {
        if (str.length() < len) {
            for (int i = 0; i < len; i++) {
                str += "20";
                if (str.length() == len) {
                    break;
                }
            }
        }
        return str;
    }

    public static String TurnSim(String Siminfo) {
        int a = Siminfo.length();
        for (int i = 1; i <= a; i++) {
            if (Siminfo.substring(0, 1).equals("F")) {
                Siminfo = Siminfo.substring(1, Siminfo.length());
            } else {
                break;
            }
        }
        int b = Siminfo.length();
        for (int i = 0; i < b; i++) {
            if (Siminfo.substring(i, i + 1).equals("F")) {
                Siminfo = Siminfo.substring(0, i);
                break;
            }
        }

        return Siminfo;
    }

    public static String AscToStr(String Ascinfo) {
        Integer ICOUNT;
        String astr = "";
        ICOUNT = Ascinfo.length() / 2 - 1;
        for (int i = 0; i <= ICOUNT; i++) {
            int a = Integer.valueOf(Ascinfo.substring(i * 2, i * 2 + 2), 16) & 0xFF;
            astr = astr + (char) a;
        }

        return astr;
    }

    public static String TurnLineNo(String StrLineNo) {
        Integer LineNo = 0;

        for (int i = 1; i <= StrLineNo.length(); i++) {
            LineNo += AscTLineNo(StrLineNo.charAt(i - 1))
                    * bcd((StrLineNo.length() - i) * 6);

        }

        return turnLength(Integer.toHexString(LineNo), 8);
    }

    private static Integer AscTLineNo(char StrLineNo) {
        StringBuffer b = new StringBuffer();
        int a = StrLineNo;
        if (a >= 65 && a <= 90) {
            return a - 49;
        } else if (a >= 48 && a <= 57)
            return Integer.valueOf(b.append((char) a).toString()) + 1;
        return null;
    }

    private static int bcd(int a) {
        int b = 1;
        for (int i = 0; i < a; i++) {
            b = b * 2;
        }
        return b;
    }

    /**
     * @param bts
     * @return String
     */
    public static String bytes2Hex(byte[] bts) {
        StringBuilder strBuild = new StringBuilder();

        for (int i = 0; i < bts.length; i++) {
            strBuild.append(String.format("%02x", (bts[i] & 0xFF)));
        }
        return strBuild.toString();
    }

    public static String hex2Ascii(String hex) {
        String result = "";
        int len = hex.length() / 2;
        for (int i = 0; i < len; i++) {
            int tmp = Integer.valueOf(hex.substring(2 * i, 2 * i + 2), 16);
            result += (char) tmp;
        }
        return result;
    }

    /*
     * 转换IP
     */
    public static String TurnIP(String IP) {
        String strIP = "";
        for (int i = 1; i < IP.length(); i++) {
            int aip = IP.substring(i - 1, i).charAt(0);
            strIP += Integer.toHexString(aip);
        }
        return strIP;
    }

    public static ArrayList<Integer> getArraylistFromString(String message) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        StringTokenizer str = new StringTokenizer(message, ";");
        int pid;
        while (str.hasMoreTokens()) {
            pid = Integer.valueOf(str.nextToken());
            list.add(pid);
        }
        return list;
    }

    public static String HexToBit(String hex) {
        String bit = "";
        try {
            bit = turnLength(Integer.toBinaryString(Integer.valueOf(hex, 16)),
                    8);
        } catch (Exception ex) {
            logger.warn("HexToBit:" + hex);
        } finally {
            return bit;
        }
    }

    public static String DecToBin(int num) {
        String strBin;
        strBin = Integer.toBinaryString(num);
        int length = strBin.length();
        int blen = length % 4;

        for (int i = 0, count = 4 - blen; i < count; i++) {
            strBin = "0" + strBin;
        }
        return strBin;
    }

    //
    public static int BinaryToTen(String str) {
        return Integer.valueOf(str, 2);
    }

    public static String AddCRC(String str) {
        int result = 0;
        for (int i = 0, len = str.length(); i < len; i = i + 2) {
            result = result + Integer.valueOf(str.substring(i, i + 2), 16);
        }
        String data = Integer.toHexString(result);
        if (data.length() > 2) {
            data = data.substring(data.length() - 2, data.length());
        }
        return Tools.turnLength(data, 2);
    }

    public static void main(String args[]) {
        // "1AE6"+""+
        String str = "12" + "01" + "00" + "0000" + "71" + "313200"
                + "0D030F090A0B" + "11" + "FFFFFFFF" + "FFFFFFFF" + "0020"
                + "0020" + "00" + "313200" + "313200" + "313200" + "000001"
                + "313200" + "313200";
        str = "1AE6" + turnLength(Integer.toHexString(str.length() / 2), 4)
                + str + getXOR(str);
        System.out.println(str);
        System.out.println(Tools.AscToStr(str));
        System.out.println(getXOR("414243"));
        System.out.println(Tools.getStringFromHex("BBA6412D3132333435"));
    }

    /*
     *
     */

    public static String getStringFromHex(String str1) {
        try {
            str1 = new String(HexString2Bytes(str1), "gbk");
        } catch (UnsupportedEncodingException ex) {
            // Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null,
            // ex);
        }
        return str1;
    }

    public static String getXOR(String data) {
        int a = Integer.valueOf(data.substring(0, 2), 16);

        for (int i = 2, len = data.length(); i < len; i = i + 2) {
            a = a ^ Integer.valueOf(data.substring(i, i + 2), 16);
        }
        return turnLength(Integer.toHexString(a), 2);
    }


    public static String turnTime(String bcdTime) {
        StringBuffer time = new StringBuffer();
        String year = bcdTime.substring(0, 4);
        time.append(year).append("-");
        String month = bcdTime.substring(4, 6);
        time.append(month).append("-");
        String day = bcdTime.substring(6, 8);
        time.append(day).append(" ");
        String hh = bcdTime.substring(8, 10);
        time.append(hh).append(":");
        String min = bcdTime.substring(10, 12);
        time.append(min).append(":");
        String ss = bcdTime.substring(12, 14);
        time.append(ss);
        return time.toString();
    }
}
