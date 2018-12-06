package com.duoduo.androidbaseproject.application;

import android.app.Application;
import android.support.annotation.Nullable;

import com.duoduo.androidbaseproject.BuildConfig;
import com.duoduo.commonbusiness.config.GlobalBuildConfig;
import com.duoduo.main.eventbus.MyEventBusIndex;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


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
        // 初始化默认的EventBus
        initEventBust();
        // 初始化Logger日志框架
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                //return GlobalBuildConfig.getInstance().isDebugMode();
                return true;
            }
        });
    }

    /**
     * 初始化EventBus
     */
    private void initEventBust() {
        //使用编译期生成的SubscriberInfoIndex初始化默认的EventBus
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
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
