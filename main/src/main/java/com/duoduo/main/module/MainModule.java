package com.duoduo.main.module;

import android.app.Application;
import android.content.Context;

import com.duoduo.commonbase.module.BaseModule;

/**
 * Main Module
 */
public class MainModule extends BaseModule {

    public MainModule(Context context) {
        super(context);
    }

    @Override
    public void applicationOnCreate(Application application, boolean isMainProcess) {

    }
}
