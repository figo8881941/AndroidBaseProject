package com.duoduo.commonbusiness.net.common;

import org.json.JSONObject;

/**
 * 处理网络的Model
 */
public interface IBaseNetModel {

    /**
     * 执行JSON POST请求
     * @param url
     * @param postData
     * @param callback
     */
    public void execAsyncJSONPostRequest(String url, JSONObject postData, IRequestCallback<JSONObject> callback);
}
