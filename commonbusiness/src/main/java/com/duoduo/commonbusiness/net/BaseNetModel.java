package com.duoduo.commonbusiness.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

/**
 * 网络处理基类
 *
 * @author wangzhuobin
 */
public abstract class BaseNetModel {

    protected RequestQueue requestQueue;
    protected Context context;

    protected BaseNetModel(Context context) {
        this.context = context.getApplicationContext();
        requestQueue = RequestQueueFactory.getRequeQueueRespondInAsyn(this.context);
    }

    public void executeRequest(Request request) {
        requestQueue.add(request);
    }

    public void cancelTaskByTag(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public void destroy() {
        requestQueue = null;
        context = null;
    }

}
