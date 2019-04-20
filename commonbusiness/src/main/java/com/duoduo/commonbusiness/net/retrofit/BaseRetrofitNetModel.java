package com.duoduo.commonbusiness.net.retrofit;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.okhttp.OKHttpClientFactory;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class BaseRetrofitNetModel {

    protected OkHttpClient okHttpClient;
    protected Context context;
    protected Retrofit retrofit;
    protected String baseUrl;

    protected BaseRetrofitNetModel(Context context) {
        this.context = context.getApplicationContext();
        this.okHttpClient = OKHttpClientFactory.getOkHttpClient(context);
        this.baseUrl = CommonNetDataUtils.getHostFromGlobalBuildConfig();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .client(this.okHttpClient)
                .build();
    }

    protected RequestBody createJsonRequestBody(JSONObject data) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), data.toString());
        return requestBody;
    }
}
