package com.duoduo.main.main;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.duoduo.commonbusiness.net.BaseNetModel;
import com.duoduo.commonbusiness.net.NetDataUtils;

import org.json.JSONObject;

/**
 * 主界面网络model
 */
public class MainNetModel extends BaseNetModel {

    public MainNetModel(Context context) {
        super(context);
    }

    public void requestTabData(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = NetDataUtils.getUrlWithGlobalBuildConfig(30000, "quMall");
        JSONObject postData = NetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                NetDataUtils.getParamJsonObjectWithGlobalBuildConfig(postData),
                listener, errorListener);
        requestQueue.add(request);
    }
}
