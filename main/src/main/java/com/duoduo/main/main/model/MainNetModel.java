package com.duoduo.main.main.model;

import android.content.Context;

import com.duoduo.commonbusiness.net.common.CommonNetDataUtils;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.commonbusiness.net.okhttp.BaseOKHttpNetModel;
import com.duoduo.main.main.consts.IMainConsts;

import org.json.JSONObject;

/**
 * 主界面网络model
 */
public class MainNetModel extends BaseOKHttpNetModel {

    public MainNetModel(Context context) {
        super(context);
    }

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData(IRequestCallback callback) {
        String url = CommonNetDataUtils.getUrlWithGlobalBuildConfig(IMainConsts.FunId.MAIN_TAB, "quMall");
        JSONObject data = CommonNetDataUtils.getPostDataWithPheadFromGlobalBuildConfig(context);
        JSONObject postData = CommonNetDataUtils.getParamJsonObject(data);
        execAsyncJSONPostRequest(url, postData, callback);
    }
}
