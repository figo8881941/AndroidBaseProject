package com.duoduo.web.module;

import android.app.Application;
import android.content.Context;

import com.duoduo.commonbase.module.BaseModule;

/**
 * Web Module
 */
public class WebModule extends BaseModule {

    public WebModule(Context context) {
        super(context);
    }

    @Override
    public void applicationOnCreate(Application application, boolean isMainProcess) {

    }

}
