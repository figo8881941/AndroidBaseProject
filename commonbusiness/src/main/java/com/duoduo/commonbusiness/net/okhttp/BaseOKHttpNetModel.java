package com.duoduo.commonbusiness.net.okhttp;

import android.content.Context;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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

    /**
     * 异步执行JSON请求的方法
     *
     * @param url
     * @param postData
     * @param callback
     */
    public void execAsyncJSONRequest(String url, JSONObject postData, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postData.toString());
        execAsyncRequest(url, requestBody, callback);
    }

    /**
     * 异步执行请求的方法
     *
     * @param url
     * @param requestBody
     * @param callback
     */
    public void execAsyncRequest(String url, RequestBody requestBody, final Callback callback) {
        if (okHttpClient == null) {
            return;
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new CommonOKHttpCallback(callback));
    }

    public void destroy() {
        okHttpClient = null;
        context = null;
    }
}
