package com.duoduo.main.main.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMainNetApi {

    @POST("quMall/common?funid=30000&shandle=0&handle=0")
    public Call<ResponseBody> requestTabData(@Body RequestBody body);
}
