package com.duoduo.commonbase.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * activity相关工具类
 */
public class ActivityUtils {

    /**
     * 设置activity全屏
     *
     * @param activity
     */
    public static void changeActivityToFullScreen(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
