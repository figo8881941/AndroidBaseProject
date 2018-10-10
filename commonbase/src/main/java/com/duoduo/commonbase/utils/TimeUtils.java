package com.duoduo.commonbase.utils;

import java.util.TimeZone;

/**
 * 时间工具类
 */
public class TimeUtils {


    /**
     * 获取当前时区
     *
     * @return
     */
    public static String getCurrentTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        String strTz = tz.getDisplayName(false, TimeZone.SHORT);
        return strTz;
    }

    /**
     * 获取当前时区id
     *
     * @return
     */
    public static String getCurrentTimeZoneID() {
        TimeZone tz = TimeZone.getDefault();
        return tz.getID();
    }
}
