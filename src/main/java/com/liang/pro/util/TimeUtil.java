package com.liang.pro.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获取时间段的所有日期
 */
public class TimeUtil {

    /**
     * 获取两个日期之间的所有日期
     * 注意返回的日期 的时分秒 之前使用的时候日期是对的死活查不出来 就是因为一个时间是00：00：00 另一个是13：00：00
     * 只有后面的是正确的
     * @param startTime
     *            开始日期
     * @param endTime
     *            结束日期
     * @return
     */
    public static List<Date> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<Date> days = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) { //当start在end之前的话 返回true
                //先加上13小时 将查询出的00：00：00 改为正确的13：00：00
//                tempStart.add(Calendar.HOUR, 13);
                days.add(tempStart.getTime());
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
}
