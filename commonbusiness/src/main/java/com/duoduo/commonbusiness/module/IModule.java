package com.duoduo.commonbusiness.module;

import android.app.Application;

/**
 * Module接口
 */
public interface IModule {

    /**
     * application onCreate方法回调
     * @param application
     */
    public void applicationOnCreate(Application application, boolean isMainProcess);
}
