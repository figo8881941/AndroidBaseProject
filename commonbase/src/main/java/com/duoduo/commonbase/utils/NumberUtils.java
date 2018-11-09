package com.duoduo.commonbase.utils;

import java.math.BigDecimal;

/**
 * 数字相关的工具类
 */
public class NumberUtils {

    /**
     * 格式化小数，保留一定位数的小数点
     *
     * @param scale
     * @param value
     * @return
     */
    public static double formatDoubleToScale(int scale, double value) {
        BigDecimal bg = new BigDecimal(value);
        double result = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }
}
