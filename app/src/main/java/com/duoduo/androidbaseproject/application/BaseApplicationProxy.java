package com.duoduo.androidbaseproject.application;

import android.app.Application;

/**
 * Application代理基类
 */

public abstract class BaseApplicationProxy {

    protected Application application;

    public BaseApplicationProxy(Application application) {
        this.application = application;
    }

    public abstract void onCreate();
}
