package com.duoduo.main.classify.home.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * HeaderView
 */
public class ClassifySubHomeHeaderView extends LinearLayout {

    private ArrayList<Banner> banners = new ArrayList<Banner>();

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

    public void stopBannerAutoPlay() {
        findBanners();
        for (Banner banner : banners) {
            banner.stopAutoPlay();
        }
    }

    public void startBannerAutoPlay() {
        findBanners();
        for (Banner banner : banners) {
            banner.startAutoPlay();
        }
    }

    private void findBanners() {
        banners.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Banner) {
                banners.add((Banner) child);
            }
        }
    }
}
