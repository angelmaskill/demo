package com.BASETEST.date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/30 10:15
 * @Modified By:
 */
public class CalendarTest {
    @DataProvider(name = "user")
    public Object[][] createUser(Method m) {
        return new Object[][]{
                {"2018-8-31 21:59:06", new String[]{"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"}}
        };
    }

    /**
     * setMinimalDaysInFirstWeek :  一周的最小天数是几天.
     * setFirstDayOfWeek:           一周从星期几开始.周日是1,周一是2.
     * 比如设置的是周日开始计周, 如果周日前的几天不够最小天数,那么这几天就不能算一周.
     *
     * @param weeks
     */
    @Test(dataProvider = "user")
    public void testSetFirstDayOfWeek(String datestr, String[] weeks) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bizTime = sdf.parse(datestr);
        Calendar cale = Calendar.getInstance();
        cale.setTime(bizTime);

        System.out.println(datestr);
        System.out.println("FirstDayOfWeek/tMinimalDaysInFirstWeek/tweek");
        for (int i = 1; i <= 7; i++) {
            cale.setFirstDayOfWeek(i);//1为星期天,7为星期六
            for (int j = 1; j <= 7; j++) {
                cale.setMinimalDaysInFirstWeek(j);
                System.out.println(
                        weeks[(i - 1)] + "/t" + j + "/t" + cale.get(Calendar.WEEK_OF_MONTH));
            }
        }
    }
}
