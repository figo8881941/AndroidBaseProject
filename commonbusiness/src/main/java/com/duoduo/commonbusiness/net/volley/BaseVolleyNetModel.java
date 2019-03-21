package com.duoduo.commonbusiness.net.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.commonbusiness.net.common.IBaseNetModel;
import com.duoduo.commonbusiness.net.common.IRequestCallback;

import org.json.JSONObject;

/**
 * 网络处理基类
 *
 * @author wangzhuobin
 */
public abstract class BaseVolleyNetModel implements IBaseNetModel {

    protected RequestQueue requestQueue;
    protected Context context;

    protected BaseVolleyNetModel(Context context) {
        this.context = context.getApplicationContext();
        requestQueue = RequestQueueFactory.getRequeQueueRespondInAsyn(this.context);
    }

    @Override
    public void execAsyncJSONPostRequest(String url, JSONObject postData, final IRequestCallback<JSONObject> callback) {
        if (requestQueue == null) {
            return;
        }
        CommonJsonObjectRequest request = new CommonJsonObjectRequest(
                Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
            }
        });
        requestQueue.add(request);
    }

    public void destroy() {
        requestQueue = null;
        context = null;
    }

}
