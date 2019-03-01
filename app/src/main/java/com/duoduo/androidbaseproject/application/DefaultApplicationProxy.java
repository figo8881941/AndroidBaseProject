package com.duoduo.androidbaseproject.application;

import android.app.Application;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.duoduo.androidbaseproject.BuildConfig;
import com.duoduo.commonbusiness.config.GlobalBuildConfig;
import com.duoduo.commonbase.module.IModule;
import com.duoduo.commonbusiness.weex.WeexImageAdapter;
import com.duoduo.main.eventbus.MyEventBusIndex;
import com.duoduo.main.module.MainModule;
import com.duoduo.web.module.WebModule;
import com.duoduo.weex.module.WeexModule;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import java.util.ArrayList;


/**
 * 默认Application代理类
 */

public class DefaultApplicationProxy extends BaseApplicationProxy {

    /**
     * Module
     */
    protected ArrayList<IModule> modules = null;

    public DefaultApplicationProxy(Application application, boolean isMainProcess) {
        super(application, isMainProcess);
    }

    @Override
    public void onCreate() {
        // 初始化Stetho
        Stetho.initializeWithDefaults(application);
        // 初始化全局配置
        initGlobalBuildConfig();
        // 初始化Logger日志框架
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                //return GlobalBuildConfig.getInstance().isDebugMode();
                return true;
            }
        });
        // 初始化ARouter
        initARouter();
        // 初始化Weex
        initWeex();
        // 初始化默认的EventBus
        initEventBus();
        // 初始化各Module
        initModules();
        // 通知各Module Application onCreate
        performModulesApplicationOnCreate();
    }

    /**
     * 初始化各Module
     */
    private void initModules() {
        modules = new ArrayList<IModule>();
        modules.add(new MainModule(application));
        modules.add(new WeexModule(application));
        modules.add(new WebModule(application));
    }

    /**
     * 通知各Module Application onCreate
     */
    private void performModulesApplicationOnCreate() {
        if (modules == null || modules.isEmpty()) {
            return;
        }
        for (IModule module : modules) {
            module.applicationOnCreate(application, isMainProcess);
        }
    }

    /**
     * 初始化EventBus
     */
    private void initEventBus() {
        //使用编译期生成的SubscriberInfoIndex初始化默认的EventBus
        EventBusBuilder busBuilder = EventBus.builder();
        busBuilder.addIndex(new MyEventBusIndex()).installDefaultEventBus();
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

    /**
     * 初始化ARouter
     */
    private void initARouter() {
        // These two lines must be written before init, otherwise these configurations will be invalid in the init process
        if (GlobalBuildConfig.getInstance().isDebugMode()) {
            // Print log
            ARouter.openLog();
            // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
            ARouter.openDebug();
        }
        // As early as possible, it is recommended to initialize in the Application
        ARouter.init(application);
    }

    /**
     * 初始化Weex
     */
    private void initWeex() {
        InitConfig config = new InitConfig.Builder().setImgAdapter(new WeexImageAdapter()).build();
        WXSDKEngine.initialize(application, config);
    }
}
