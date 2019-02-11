package com.duoduo.commonbase.utils;

import java.math.BigDecimal;

/**
 * 数据工具类
 */

public class DataUtils {

    /**
     * 判断字符串是否是数字的方法
     * @param str
     * @return
     */
    public static boolean isDigitString(String str) {
        if (str == null ) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 格式化单位
     * @param size
     */
    public static String getFormatSize(double size, int scale) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(scale, BigDecimal.ROUND_HALF_UP) .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(scale, BigDecimal.ROUND_HALF_UP) .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    public static int findMaxInt(int[] ints) {
        int max = ints[0];
        for (int value : ints) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    //获取两位数的字符串
    public static String getStringFromDouble(double value) {
        return String.format("%.02f", value);
    }

    //获取1位数的字符串
    public static String getOneStringFromDouble(double value) {

        return String.format("%.01f", value);
    }

    //获取0位数的字符串
    public static String getZeroStringFromDouble(double value) {

        return String.format("%.0f", value);
    }


    /**
     * 小数点处理，保留一个小数点
     */
    public static double tranDoubleOne(double data) {
        BigDecimal b = new BigDecimal(data);
        //保留2位小数
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
