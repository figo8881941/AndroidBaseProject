package com.duoduo.commonbusiness.net.okhttp;

import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * 网络处理基类
 */
public class BaseOKHttpNetModel {
    protected OkHttpClient okHttpClient;
    protected Context context;

    protected BaseOKHttpNetModel(Context context) {
        this.context = context.getApplicationContext();
        this.okHttpClient = OKHttpClientFactory.getOkHttpClient(context);
    }

    public void destroy() {
        okHttpClient = null;
        context = null;
    }
}
