package com.back.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {


    /**
     * @Description 得到格式化的当前时间字符串（通用方法）
     * @param format，String类型，格式样式
     * 格式样式有："yyyyMMdd"，"yyyyMMddHHmm"，"yyyyMMddHHmmss"(24小时制)，"yyyy-MM-dd HH:mm:ss"，
     * 			 "yyyy年MM月dd日"，"yyyy年MM月dd日 HH时mm分ss秒"，"yyyy年MM月dd日 HH:mm:ss"，
     *           "yyyyMMddhhmmss"(12小时制)。。。
     * @return String
     * @author KFC，2007.5.9
     */
    public static String getCurDateTime(String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String curDateTime = formatter.format(cal.getTime());
        return curDateTime;
    }


    /**
     * @Description 得到格式化的当前时间字符串（特定方法）：返回格式"yyyy-MM-dd HH:mm:ss"
     * @return String
     * @author KFC，2007.6.1
     */
    public static String getCurDate_yyyyMMddHHmmssRe() {
        return getCurDateTime("yyyy-MM-dd HH:mm:ss");
    }
}
