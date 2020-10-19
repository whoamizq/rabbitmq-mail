package com.whoamizq.rabbitmqmail.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

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
        return dateToStr(date,STANDARD_FORMAT);
    }

    /**
     * date类型 ----> string类型
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date,String format){
        if (date == null){
            date = new Date();
        }
        format = StringUtils.isBlank(format) ? STANDARD_FORMAT : format;
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }


}
