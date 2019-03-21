package com.duoduo.main.classify.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.duoduo.commonbusiness.net.volley.BaseVolleyNetModel;
import com.duoduo.commonbusiness.net.volley.CommonJsonObjectRequest;
import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.main.classify.consts.IClassifyConsts;

import org.json.JSONObject;

/**
 * 分类网络处理
 */
public class ClassifyVolleyNetModel extends BaseVolleyNetModel {

    public ClassifyVolleyNetModel(Context context) {
        super(context);
    }

    /**
     * 请求分类Tab数据的方法
     */
    public void requestClassifySubTabData(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifyConsts.FunId.CLASSIFY_SUB_TAB_DATA, "quMall");
        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JsonObjectRequest request = new CommonJsonObjectRequest(Request.Method.POST, url,
                CommonNetDataUtils.getParamJsonObject(postData),
                listener, errorListener);
        executeRequest(request);
    }
}
