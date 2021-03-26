package com.xinnuo.se.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtils {
    /**
     * 获取前台提交的参数
     *
     * @return
     */
    public static Map getParameterMap(HttpServletRequest request) {
        Map parametersMap = new HashMap();
        Enumeration paramters = request.getParameterNames();
        while (paramters.hasMoreElements()) {
            String name = (String) paramters.nextElement();
            String[] values = request.getParameterValues(name);
            String value = "";
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    value += values[i] + ',';
                }
                value = value.substring(0, value.length() - 1);
            }
            parametersMap.put(name, value.trim());
        }
        return parametersMap;
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空， 为空返回 null； 不为空 type =1 去前后空格 type =2 去空格并转换大写 type = 3 去空格并转换小写
     *
     * @return
     */
    public static String stringTrimUpper(Object o, int type) {
        String string = "";
        try {
            if (isEmpty(o)) {
                return null;
            }

            if (!isEmpty(o) && type == 1) {
                return (String.valueOf(o)).trim();
            }

            if (!isEmpty(o) && type == 2) {
                return (String.valueOf(o)).trim().toUpperCase();
            }

            if (!isEmpty(o) && type == 3) {
                return (String.valueOf(o).trim().toLowerCase());
            }

        } catch (Exception ex) {

        }

        return string;
    }

    /**
     * 判断是否为数字格式不限制位数
     *
     * @param o
     * @return
     */
    public static boolean isNumber(Object o) {
        return (Pattern.compile("[0-9]*")).matcher(String.valueOf(o)).matches();
    }

    /**
     * Long类型转换
     *
     * @param str
     * @return
     */
    public static Long toLong(String str) {
        Long i = null;
        try {
            if (null != str && !"".equals(str)) {
                i = new Long(str);
            }
        } catch (Exception ex) {
        }
        return i;
    }

    /**
     * BigDecimal类型转换
     *
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str) {
        BigDecimal bd = null;
        try {
            if (null != str && !"".equals(str)) {
                bd = new BigDecimal(str);
            }
        } catch (Exception ex) {
        }
        return bd;
    }

    /**
     * Short类型转换
     *
     * @param str
     * @return
     */
    public static Short toShort(String str) {
        Short st = null;
        try {
            if (null != str && !"".equals(str)) {
                st = new Short(str);
            }
        } catch (Exception ex) {
        }
        return st;
    }

    /**
     * Integer类型转换
     *
     * @param str
     * @return
     */
    public static Integer toInteger(String str) {

        Integer i = null;
        try {
            if (null != str && !"".equals(str)) {
                i = new Integer(str);
            }
        } catch (Exception ex) {
        }
        return i;
    }

    public static int toInt(String str) {

        int i = 0;
        try {
            if (null != str && !"".equals(str)) {
                i = new Integer(str).intValue();
            }
        } catch (Exception ex) {
        }
        return i;
    }

    /**
     * Date类型转换
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static Date toDate(String time) {
        /** */
        /**
         * 字符串转换为java.util.Date 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1
         * AD at 22:10:59 PSD' yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00' yy/MM/dd
         * HH:mm:ss pm 如 '2002/1/1 17:55:00 pm' yy-MM-dd HH:mm:ss 如 '2002-1-1
         * 17:55:00' yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am'
         *
         * @param time
         *            String 字符串
         * @return Date 日期
         */
        try {
            SimpleDateFormat formatter;

            if (time.indexOf("-") > -1) {// 包含“-”
                if (time.indexOf(":") == -1) {
                    formatter = new SimpleDateFormat("yyyy-M-d");
                } else if (time.indexOf(":") == time.lastIndexOf(":")) {
                    formatter = new SimpleDateFormat("yyyy-M-d HH:mm");
                } else {
                    formatter = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
                }
            } else if (time.indexOf("/") > -1) {// 包含“-”
                if (time.indexOf(":") == -1) {
                    formatter = new SimpleDateFormat("yyyy/M/d");
                } else if (time.indexOf(":") == time.lastIndexOf(":")) {
                    formatter = new SimpleDateFormat("yyyy/M/d HH:mm");
                } else {
                    formatter = new SimpleDateFormat("yyyy/M/d HH:mm:ss");
                }
            } else if (time.indexOf(".") > -1) {// 包含“/”
                if (time.indexOf(":") == -1) {
                    formatter = new SimpleDateFormat("yyyy.M.d");
                } else if (time.indexOf(":") == time.lastIndexOf(":")) {
                    formatter = new SimpleDateFormat("yyyy.M.d HH:mm");
                } else {
                    formatter = new SimpleDateFormat("yyyy.M.d HH:mm:ss");
                }
            } else {
                formatter = new SimpleDateFormat("HH:mm");
            }

            ParsePosition pos = new ParsePosition(0);
            java.util.Date ctime = formatter.parse(time, pos);

            return ctime;
        } catch (Exception e) {

        }
        return null;
    }

    public static String toString(String[] str) {
        return toString(str, ",");
    }

    public static String toString(String[] str, String delimiter) {
        // 此值传过来的为复选框，所以保存时用","号分隔
        String newStr = "";
        if (null != str) {
            for (int i = 1; i < str.length; i++) {
                newStr = newStr + delimiter + str[i];
            }
        }
        return newStr;
    }

    /**
     * 四舍五入 number为四舍五入的数字 <p/> keta是保留小数点之后的位数，从0开始 （0，1，2,3,其他） <p/> 0: 表示整数
     * （155.5->156） 1:(0.05->0.10) 2:(0.05 ->0.10) 3：(0.005-0.010)
     * 其他:(0.00005-0.0001)
     */
    public static Double rounds(Double number, int keta) {

        // BigDecimal num = new BigDecimal(number);
        //
        // NumberFormat nbf = NumberFormat.getInstance();
        // nbf.setMinimumFractionDigits(keta + 1);

        // return Double.valueOf(nbf.format(num.setScale(keta,
        // BigDecimal.ROUND_HALF_UP).doubleValue()));

        String fmt = "";
        switch (keta) {
            case 0:
                fmt = "#0";
                break;
            case 1:
                fmt = "#0.0";
                break;
            case 2:
                fmt = "#0.00";
                break;
            case 3:
                fmt = "#0.000";
                break;

            default:
                fmt = "#0.0000";
                break;
        }

        DecimalFormat df = new DecimalFormat(fmt);

        return Double.valueOf(df.format(number));

        // BigDecimal value = new BigDecimal(nbf.format(num.setScale(keta,
        // BigDecimal.ROUND_HALF_UP).doubleValue()));
        // return value;
    }

    /**
     * 左侧补位
     *
     * @param value    原值
     * @param length   长度
     * @param padValue 填补值
     * @return
     */
    public static String leftPad(String value, int length, String padValue) {
        for (int i = value.length(); i < length; i++) {
            value = padValue + value;
        }
        return value;
    }

    /**
     * 右侧补位
     *
     * @param value    原值
     * @param length   长度
     * @param padValue 填补值
     * @return
     */
    public static String rightPad(String value, int length, String padValue) {
        for (int i = value.length(); i < length; i++) {
            value += padValue;
        }
        return value;
    }

    public static String toNvl(Object fo, Object to) {
        if (fo == null || "".endsWith(fo.toString())) {
            fo = to;
        }
        return fo.toString();
    }

    public static String getErrMsgFromE(Exception e) {
        String errMsg = "";
        if (e.getCause() == null || e.getCause().getCause() == null) {
            errMsg = e.getMessage() == null ? e.getCause().getMessage() : e.getMessage();
        } else {
            errMsg = e.getCause().getCause().getMessage();
            if (errMsg.indexOf("ORA-") != -1) {
                errMsg = errMsg.split("ORA-[0-9]*[:]")[1];
            } else {
                String reg = "";
                String shortmessage = "";
                reg = "[\n-\r]";
                Pattern p = Pattern.compile(reg);
                String message = e.getCause().getMessage();
                Matcher matcher = p.matcher(message);
                String singleLineMessage = matcher.replaceAll(": ");
                String[] arrayMessage = singleLineMessage.split(": ");
                if (arrayMessage.length > 1) {
                    shortmessage = arrayMessage[arrayMessage.length - 1];
                } else {
                    shortmessage = arrayMessage[0];
                }
                errMsg = shortmessage;
            }
        }

        return errMsg;
    }

}
