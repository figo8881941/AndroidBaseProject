package com.duoduo.main.main.model;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.commonbusiness.net.retrofit.BaseRetrofitNetModel;
import com.duoduo.commonbusiness.net.retrofit.CommonRetrofitJsonCallback;
import com.duoduo.main.main.api.IMainNetApi;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 主界面网络model
 */
public class MainNetModel extends BaseRetrofitNetModel {

    public MainNetModel(Context context) {
        super(context);
    }

//    /**
//     * 请求首页Tab数据的方法
//     */
//    public void requestTabData(IRequestCallback callback) {
//        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IMainConsts.FunId.MAIN_TAB, "quMall");
//        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
//        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
//        execAsyncJSONPostRequest(url, postData, callback);
//    }

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData(IRequestCallback callback) {
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        IMainNetApi api = retrofit.create(IMainNetApi.class);
        Call<ResponseBody> call = api.requestTabData(createJsonRequestBody(postData));
        call.enqueue(new CommonRetrofitJsonCallback(callback));
    }
}
