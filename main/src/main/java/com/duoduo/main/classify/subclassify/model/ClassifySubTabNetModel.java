package com.duoduo.main.classify.subclassify.model;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.commonbusiness.net.volley.BaseVolleyNetModel;
import com.duoduo.main.classify.subclassify.consts.IClassifySubTabConsts;

import org.json.JSONObject;

public class ClassifySubTabNetModel extends BaseVolleyNetModel {


    public ClassifySubTabNetModel(Context context) {
        super(context);
    }

    /**
     * 请求子分类数据的方法
     *
     * @param categoryId
     * @throws Exception
     */
    public void requestSubTabProductData(int categoryId, int pageNum, IRequestCallback<JSONObject> callback) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubTabConsts.FunId.CLASSIFY_SUB_TAB_DATA, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        data.put("curCategoryId", categoryId);
        data.put("pageNum", pageNum);
        data.put("pageSize", 30);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONPostRequest(url, postData, callback);
    }

    /**
     * 请求子分类排行榜主题数据的方法
     *
     * @param topicId
     * @throws Exception
     */
    public void requestSubTabTopicData(int topicId, IRequestCallback<JSONObject> callback) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubTabConsts.FunId.CLASSIFY_SUB_TAB_TOPIC_DATA, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        data.put("topicId", topicId);
        data.put("pageNum", 1);
        data.put("pageSize", 5);
        data.put("personal", 1);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONPostRequest(url, postData, callback);
    }
}
