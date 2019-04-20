package com.duoduo.main.main.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface IMainNetApi {

    @POST
    public Call<ResponseBody> requestTabData(@Url String url, @Body RequestBody body);
}
