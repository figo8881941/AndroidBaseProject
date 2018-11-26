package com.duoduo.main.classify.home.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.duoduo.commonbusiness.net.BaseNetModel;
import com.duoduo.commonbusiness.net.CommonJsonObjectRequest;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;
import com.duoduo.main.classify.consts.IClassifyConsts;
import com.duoduo.main.classify.home.consts.IClassifySubHomeConsts;
import com.duoduo.main.main.consts.IMainConsts;

import org.json.JSONObject;

/**
 * 分类首页net model
 */
public class ClassifySubHomeNetModel extends BaseNetModel {

    public ClassifySubHomeNetModel(Context context) {
        super(context);
    }

    /**
     * 请求分类首页数据的方法
     */
    public void requestClassifySubHomeData(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubHomeConsts.FunId.CLASSIFY_SUB_HOME_DATA, "quMall");
        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        postData.put("tabId", IMainConsts.MainTabIdValue.HOME_TAB);
        postData.put("personal", 1);
        JsonObjectRequest request = new CommonJsonObjectRequest(Request.Method.POST, url,
                CommonNetDataUtils.getParamJsonObject(postData),
                listener, errorListener);
        requestQueue.add(request);
    }

    /**
     * 请求主题数据的方法
     */
    public void requestTopicData(int topicId, int pageNum, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubHomeConsts.FunId.CLASSIFY_TOPIC_DATA, "quMall");
        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        postData.put("topicId", topicId);
        postData.put("pageNum", pageNum);
        postData.put("pageSize", 30);
        postData.put("personal", 1);
        JsonObjectRequest request = new CommonJsonObjectRequest(Request.Method.POST, url,
                CommonNetDataUtils.getParamJsonObject(postData),
                listener, errorListener);
        requestQueue.add(request);
    }
}
