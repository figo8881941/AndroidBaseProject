package com.duoduo.androidbaseproject.application;

import android.app.Application;

/**
 * 主进程Application代理类
 */

public class MainProcessApplicationProxy extends DefaultApplicationProxy {

    public MainProcessApplicationProxy(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
