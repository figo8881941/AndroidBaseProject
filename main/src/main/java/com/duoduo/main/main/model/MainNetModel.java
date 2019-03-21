package com.duoduo.main.main.model;

import android.content.Context;

import com.android.volley.Response;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.okhttp.BaseOKHttpNetModel;
import com.duoduo.main.main.consts.IMainConsts;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 主界面网络model
 */
public class MainNetModel extends BaseOKHttpNetModel {

    public MainNetModel(Context context) {
        super(context);
    }

//    /**
//     * 请求首页Tab数据的方法
//     */
//    public void requestTabData(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
//        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IMainConsts.FunId.MAIN_TAB, "quMall");
//        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
//        JsonObjectRequest request = new CommonJsonObjectRequest(Request.Method.POST, url,
//                CommonNetDataUtils.getParamJsonObject(postData),
//                listener, errorListener);
//        executeRequest(request);
//    }

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData(Callback callback) {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IMainConsts.FunId.MAIN_TAB, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONRequest(url, postData, callback);
    }
}
