package com.duoduo.main.classify.home.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * HeaderView
 */
public class ClassifySubHomeHeaderView extends LinearLayout {

    public ClassifySubHomeHeaderView(Context context) {
        super(context);
    }

    public ClassifySubHomeHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClassifySubHomeHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClassifySubHomeHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
