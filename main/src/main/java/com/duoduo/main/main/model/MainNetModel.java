package com.duoduo.main.main.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.duoduo.commonbusiness.net.BaseNetModel;
import com.duoduo.commonbusiness.net.CommonJsonObjectRequest;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;
import com.duoduo.main.main.consts.IMainConsts;

import org.json.JSONObject;

/**
 * 主界面网络model
 */
public class MainNetModel extends BaseNetModel {

    public MainNetModel(Context context) {
        super(context);
    }

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IMainConsts.FunId.MAIN_PAGE_TAB, "quMall");
        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JsonObjectRequest request = new CommonJsonObjectRequest(Request.Method.POST, url,
                CommonNetDataUtils.getParamJsonObject(postData),
                listener, errorListener);
        requestQueue.add(request);
    }
}
