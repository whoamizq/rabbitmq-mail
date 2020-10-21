package com.whoamizq.rabbitmqmail.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author: whoamizq
 * @description: 格式化时间
 * @date: 13:50 2020/10/19
 **/
@Slf4j
public class JodaTimeUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * @author: whoamizq
     * @description: date类型--》string类型
     * @date: 12:05 2020/10/19
     * @param: [date]
     * @return: java.lang.String
     **/
    public static String dateToStr(Date date) {
        return dateToStr(date, STANDARD_FORMAT);
    }

    /**
     * date类型 ----> string类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if (date == null) {
            date = new Date();
        }
        format = StringUtils.isBlank(format) ? STANDARD_FORMAT : format;
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

    /**
     * @author: whoamizq
     * @description: string类型 --> Date类型
     * @date: 11:15 2020/10/21
     * @param: [timeStr]
     * @return: java.util.Date
     **/
    public static Date strToDate(String timeStr) {
        return strToDate(timeStr, STANDARD_FORMAT);
    }

    public static Date strToDate(String timeStr, String format) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }
        format = StringUtils.isBlank(format) ? STANDARD_FORMAT : format;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime;
        try {
            dateTime = dateTimeFormatter.parseDateTime(timeStr);
        } catch (Exception e) {
            log.error("strToDate error:{} timeStr: {}", e, timeStr);
            return null;
        }
        return dateTime.toDate();
    }

    /**
     * @author: whoamizq
     * @description: 判断日期是否过期(与当前时刻比较)
     * @date: 11:23 2020/10/21
     * @param: [date]
     * @return: java.lang.Boolean
     **/
    public static Boolean isTimeExpired(Date date) {
        return isBeforeNow(dateToStr(date));
    }

    public static Boolean isTimeExpired(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return true;
        }

        return isBeforeNow(timeStr);
    }

    /**
     * @author: whoamizq
     * @description: 判断日期是否在当前时刻之前
     * @date: 11:46 2020/10/21
     * @param: [timeStr]
     * @return: java.lang.Boolean
     **/
    private static Boolean isBeforeNow(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime;
        try {
            dateTime = DateTime.parse(timeStr, formatter);
        } catch (Exception e) {
            log.error("isBeforeNow error:{} timeStr: {}", e, timeStr);
            return null;
        }
        return dateTime.isBeforeNow();
    }

    /**
     * 日期加分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusMinutes(Date date, int minutes) {
        return plusOrMinusMinutes(date, minutes, 0);
    }

    /**
     * 日期减分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date minusMinutes(Date date, int minutes) {
        return plusOrMinusMinutes(date, minutes, 1);
    }

    /**
     * 加减分钟
     *
     * @param date
     * @param minutes
     * @param type    0:加，1：减
     * @return
     */
    private static Date plusOrMinusMinutes(Date date, int minutes, int type) {
        if (null == date) {
            date = new Date();
        }

        DateTime dateTime = new DateTime(date);
        if (type == 0) {
            dateTime = dateTime.plusMinutes(minutes);
        } else {
            dateTime = dateTime.minusMinutes(minutes);
        }

        return dateTime.toDate();
    }

    /**
     * @author: whoamizq
     * @description: 日期加天数
     * @date: 11:53 2020/10/21
     * @param: [date, days]
     * @return: java.util.Date
     **/
    public static Date plusDays(Date date, int days) {
        return plusOrMinusDays(date, days, 0);
    }

    /**
     * @author: whoamizq
     * @description: 日期减天数
     * @date: 11:55 2020/10/21
     * @param: [date, days]
     * @return: java.util.Date
     **/
    public static Date minusDays(Date date, int days) {
        return plusOrMinusDays(date, days, 1);
    }

    /**
     * 加减天数
     *
     * @param date
     * @param days
     * @param type
     * @return
     */
    private static Date plusOrMinusDays(Date date, int days, int type) {
        if (null == date) {
            return null;
        }

        DateTime dateTime = new DateTime(date);
        if (type == 0) {
            dateTime = dateTime.plusDays(days);
        } else {
            dateTime = dateTime.minusDays(days);
        }
        return dateTime.toDate();
    }

    /**
     * @author: whoamizq
     * @description: 日期加月份
     * @date: 12:01 2020/10/21
     * @param: [date, mouths]
     * @return: java.util.Date
     **/
    public static Date plusMouths(Date date, int mouths) {
        return plusOrMinusMouths(date, mouths, 0);
    }

    /**
     * @author: whoamizq
     * @description: 日期减月份
     * @date: 12:04 2020/10/21
     * @param: [date, mouths]
     * @return: java.util.Date
     **/
    public static Date minusMouths(Date date, int mouths) {
        return plusOrMinusMouths(date, mouths, 1);
    }

    /**
     * 加减月份
     *
     * @param date
     * @param mouths
     * @param type   0:加,1:减
     * @return
     */
    private static Date plusOrMinusMouths(Date date, int mouths, int type) {
        if (null == date) {
            return null;
        }

        DateTime dateTime = new DateTime(date);
        if (type == 0) {
            dateTime = dateTime.plusMonths(mouths);
        } else {
            dateTime = dateTime.minusMonths(mouths);
        }
        return dateTime.toDate();
    }

    /**
     * @author: whoamizq
     * @description: 判断target是否在开始和结束时间之间
     * @date: 12:08 2020/10/21
     * @param: [target, startTime, endTime]
     * @return: java.lang.Boolean
     **/
    public static Boolean isBetweenStartAndEndTime(Date target, Date startTime, Date endTime) {
        if (null == target || null == startTime || null == endTime) {
            return false;
        }
        DateTime dateTime = new DateTime(target);
        return dateTime.isAfter(startTime.getTime()) && dateTime.isBefore(endTime.getTime());
    }
}
