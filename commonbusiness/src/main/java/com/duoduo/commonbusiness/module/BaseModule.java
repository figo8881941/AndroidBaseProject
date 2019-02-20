package com.duoduo.commonbusiness.module;

import android.content.Context;

/**
 * Module基类
 */
public abstract class BaseModule implements IModule {

    protected Context context;

    public BaseModule(Context context) {
        this.context = context.getApplicationContext();
    }
}
