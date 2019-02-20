package com.duoduo.androidbaseproject.application;

import android.app.Application;

/**
 * Application代理基类
 */

public abstract class BaseApplicationProxy {

    protected Application application;

    protected boolean isMainProcess;

    public BaseApplicationProxy(Application application, boolean isMainProcess) {
        this.application = application;
        this.isMainProcess = isMainProcess;
    }

    public abstract void onCreate();
}
