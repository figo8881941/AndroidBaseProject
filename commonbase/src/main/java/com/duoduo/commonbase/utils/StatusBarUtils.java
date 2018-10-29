package com.duoduo.commonbase.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 状态栏工具类
 */
public class StatusBarUtils {

    //状态栏高度
    private static int sStatusBarHeight;

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        if (sStatusBarHeight <= 0 && context != null) {
            Resources resources = context.getResources();
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                sStatusBarHeight = resources.getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sStatusBarHeight;
    }

    /**
     * 获取需要适配的状态栏高度，如果是KITKAT一下返回0
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeightFit(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ? getStatusBarHeight(context) : 0;
    }


    /**
     * 设置透明状态栏
     *
     * @param activity
     * @param isWhiteIcon 是否白色风格状态栏
     */
    public static void changeStatusBarTran(Activity activity, boolean isWhiteIcon) {
        if (activity == null) {
            return;
        }
        Window window = activity.getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.0及以上
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (isWhiteIcon) {
                    window.getDecorView()
                            .setSystemUiVisibility(
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                } else {
                    window.getDecorView()
                            .setSystemUiVisibility(
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                }
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //4.4及以上
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }
}
