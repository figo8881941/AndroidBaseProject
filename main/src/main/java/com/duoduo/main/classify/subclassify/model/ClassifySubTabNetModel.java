package com.duoduo.main.classify.subclassify.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.duoduo.commonbusiness.net.BaseNetModel;
import com.duoduo.commonbusiness.net.CommonJsonObjectRequest;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;
import com.duoduo.main.classify.subclassify.consts.IClassifySubTabConsts;

import org.json.JSONObject;

public class ClassifySubTabNetModel extends BaseNetModel {


    public ClassifySubTabNetModel(Context context) {
        super(context);
    }

    /**
     * 请求子分类数据的方法
     * @param categoryId
     * @param listener
     * @param errorListener
     * @throws Exception
     */
    public void requestSubTabProductData(int categoryId, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubTabConsts.FunId.CLASSIFY_SUB_TAB_DATA, "quMall");
        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        postData.put("curCategoryId", categoryId);
        postData.put("pageNum", 1);
        postData.put("pageSize", 30);
        CommonJsonObjectRequest request = new CommonJsonObjectRequest(
                Request.Method.POST, url, CommonNetDataUtils.getParamJsonObject(postData), listener, errorListener);
        requestQueue.add(request);
    }

    /**
     * 请求子分类排行榜主题数据的方法
     * @param topicId
     * @param listener
     * @param errorListener
     * @throws Exception
     */
    public void requestSubTabTopicData(int topicId, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubTabConsts.FunId.CLASSIFY_SUB_TAB_TOPIC_DATA, "quMall");
        JSONObject postData = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        postData.put("topicId", topicId);
        postData.put("pageNum", 1);
        postData.put("pageSize", 5);
        postData.put("personal", 1);
        CommonJsonObjectRequest request = new CommonJsonObjectRequest(
                Request.Method.POST, url, CommonNetDataUtils.getParamJsonObject(postData), listener, errorListener);
        requestQueue.add(request);
    }
}
