package com.duoduo.commonbase.utils;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * View相关的工具类
 */

public class ViewUtils {

    public static void showView(View view) {
        if (view != null && view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hideView(View view) {
        if (view != null && view.getVisibility() != View.GONE) {
            view.setVisibility(View.GONE);
        }
    }

}
