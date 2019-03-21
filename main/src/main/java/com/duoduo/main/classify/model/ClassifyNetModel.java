package com.duoduo.main.classify.model;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.commonbusiness.net.volley.BaseVolleyNetModel;
import com.duoduo.main.classify.consts.IClassifyConsts;

import org.json.JSONObject;

/**
 * 分类网络处理
 */
public class ClassifyNetModel extends BaseVolleyNetModel {

    public ClassifyNetModel(Context context) {
        super(context);
    }

    /**
     * 请求分类Tab数据的方法
     */
    public void requestClassifySubTabData(IRequestCallback<JSONObject> callback) throws Exception {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IClassifyConsts.FunId.CLASSIFY_SUB_TAB_DATA, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONPostRequest(url, postData, callback);
    }
}
