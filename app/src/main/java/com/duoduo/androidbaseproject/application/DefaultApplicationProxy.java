package com.duoduo.androidbaseproject.application;

import android.app.Application;

import com.duoduo.androidbaseproject.BuildConfig;
import com.duoduo.commonbusiness.config.GlobalBuildConfig;


/**
 * 默认Application代理类
 */

public class DefaultApplicationProxy extends BaseApplicationProxy {

    public DefaultApplicationProxy(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        // 初始化全局配置
        initGlobalBuildConfig();
    }

    /**
     * 初始化全局配置的方法
     */
    private void initGlobalBuildConfig() {
        GlobalBuildConfig config = GlobalBuildConfig.getInstance();
        config.setDebugMode(BuildConfig.IS_DEBUG_MODE);
        config.setDefaultChannel(BuildConfig.DEFAULT_CHANNEL);
        config.setNormalServerHost(BuildConfig.NORMAL_SERVER_HOST);
        config.setTestServerHost(BuildConfig.TEST_SERVER_HOST);
        config.setPrdid(BuildConfig.PRODUCT_ID);
        config.setPVersion(BuildConfig.PVERSON);
        config.setSDCardFolderName(BuildConfig.SDCARD_FOLDER_NAME);
    }
}
