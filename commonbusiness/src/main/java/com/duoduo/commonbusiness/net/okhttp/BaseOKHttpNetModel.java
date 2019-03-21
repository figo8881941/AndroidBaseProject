package com.duoduo.commonbusiness.net.okhttp;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.IBaseNetModel;
import com.duoduo.commonbusiness.net.common.IRequestCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络处理基类
 */
public abstract class BaseOKHttpNetModel implements IBaseNetModel {
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
    @Override
    public void execAsyncJSONPostRequest(String url, JSONObject postData, IRequestCallback<JSONObject> callback) {
        if (okHttpClient == null) {
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postData.toString());
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new CommonOKHttpJsonCallback(callback));
    }

    public void destroy() {
        okHttpClient = null;
        context = null;
    }
}
