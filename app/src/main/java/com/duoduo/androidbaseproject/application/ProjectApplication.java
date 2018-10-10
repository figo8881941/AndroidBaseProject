package com.duoduo.androidbaseproject.application;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.duoduo.commonbase.utils.AppUtils;

public class ProjectApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplicationProxy proxy = createProxy(this);
        proxy.onCreate();
    }

    /**
     * 不同的进程，创建不同的ApplicationProxy来进行初始化
     *
     * @param application
     * @return
     */
    public BaseApplicationProxy createProxy(Application application) {
        String processName = AppUtils.getCurProcessName(application);
        if (!TextUtils.isEmpty(processName)) {
            String pkgname = application.getPackageName();
            if (processName.equals(pkgname)) {
                return new MainProcessApplicationProxy(application);
            }
        }
        return new DefaultApplicationProxy(application);
    }
}
