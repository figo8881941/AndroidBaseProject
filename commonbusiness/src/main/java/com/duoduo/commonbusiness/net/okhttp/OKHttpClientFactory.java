package com.duoduo.commonbusiness.net.okhttp;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * OkHttpClient工厂类
 */
public class OKHttpClientFactory {

    private static OkHttpClient sOKHttpClient;

    public static synchronized OkHttpClient getOkHttpClient(Context context) {
        if (sOKHttpClient == null) {
            String cacheFilePath = context.getExternalCacheDir() + File.separator + "okhttp_cache";
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .cache(new Cache(new File(cacheFilePath), 10 * 1024 * 1024));
            sOKHttpClient = builder.build();
        }
        return sOKHttpClient;
    }

}
