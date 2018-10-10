package com.duoduo.androidbaseproject.application;

import android.app.Application;

import com.duoduo.androidbaseproject.BuildConfig;
import com.duoduo.commonbase.utils.TestUtils;


/**
 * 默认Application代理类
 */

public class DefaultApplicationProxy extends BaseApplicationProxy {

    private final boolean DEBUG = BuildConfig.IS_DEBUG_MODE;

    public DefaultApplicationProxy(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        // 初始化测试标识
        TestUtils.setDebug(BuildConfig.IS_DEBUG_MODE);
    }
}
