package com.duoduo.commonbase.utils;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Textview工具类
 */
public class TextViewUtils {

    /**
     * 设置字体为指定值的粗体
     *
     * @param textView
     */
    public static void setTextStyleAndStrokeWidth(TextView textView, Paint.Style style, int level) {
        if (level < 0) {
            level = 0;
        }
        textView.getPaint().setStyle(style);
        textView.getPaint().setStrokeWidth(level);
    }
}
