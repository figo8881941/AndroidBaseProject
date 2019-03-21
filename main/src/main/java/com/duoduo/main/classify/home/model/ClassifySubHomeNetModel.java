package com.duoduo.main.classify.home.model;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.commonbusiness.net.volley.BaseVolleyNetModel;
import com.duoduo.main.classify.home.consts.IClassifySubHomeConsts;
import com.duoduo.main.main.consts.IMainConsts;

import org.json.JSONObject;

/**
 * 分类首页net model
 */
public class ClassifySubHomeNetModel extends BaseVolleyNetModel {

    public ClassifySubHomeNetModel(Context context) {
        super(context);
    }

    /**
     * 请求分类首页数据的方法
     */
    public void requestClassifySubHomeData(IRequestCallback<JSONObject> callback) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubHomeConsts.FunId.CLASSIFY_SUB_HOME_DATA, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        data.put("tabId", IMainConsts.MainTabIdValue.HOME_TAB);
        data.put("personal", 1);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONPostRequest(url, postData, callback);
    }

    /**
     * 请求主题数据的方法
     */
    public void requestTopicData(int topicId, int pageNum, IRequestCallback<JSONObject> callback) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifySubHomeConsts.FunId.CLASSIFY_TOPIC_DATA, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        data.put("topicId", topicId);
        data.put("pageNum", pageNum);
        data.put("pageSize", 30);
        data.put("personal", 1);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONPostRequest(url, postData, callback);
    }
}
