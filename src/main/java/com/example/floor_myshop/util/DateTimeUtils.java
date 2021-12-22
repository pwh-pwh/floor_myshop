package com.example.floor_myshop.util;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class DateTimeUtils {

    public static final String DEFAULT_FORMAT_PATTERN = "yyyy/MM/dd hh:mm:ss";

    public static String toStrFromDate(Date date){
        return DateTime.of(date).toString("yyyy/MM/dd hh:mm:ss");
    }

    public static long toLongFromDate(Date date){
        return date.getTime();
    }

    public static Date toDateFromLong(long time){
        return new Date(time);
    }

    public static String toStrFromLong(long time){
        return toStrFromDate(toDateFromLong(time));
    }

    public static Date toDateFromStr(String timeStr){
        return DateTime.of(timeStr,"yyyy/MM/dd hh:mm:ss").toJdkDate();
    }

    public static long toLongFromStr(String timeStr){
        return toLongFromDate(toDateFromStr(timeStr));
    }


    public static LocalDateTime toLocalDateTimeFromLong(long time){
        return LocalDateTimeUtil.of(time);
    }

    public static LocalDateTime toLocalDateTimeFromDate(Date date){
        return LocalDateTimeUtil.of(date);
    }

    public static Date toDateFromLocalDateTime(LocalDateTime localDateTime){
        Date date = Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }

}
