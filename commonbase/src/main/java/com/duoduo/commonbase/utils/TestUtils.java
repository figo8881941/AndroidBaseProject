package com.duoduo.commonbase.utils;

/**
 * 测试工具类
 */
public class TestUtils {

    private static boolean DEBUG = false;

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void setDebug(boolean DEBUG) {
        TestUtils.DEBUG = DEBUG;
    }
}
