package com.duoduo.main.classify.home.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.main.classify.data.ClassifyHomeDataBean;
import com.duoduo.main.classify.home.event.ClassifySubHomeDataRequestEvent;
import com.duoduo.main.classify.home.model.ClassifySubHomeNetModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 分类首页Controller
 */
public class ClassifySubHomeController {

    private Context context;

    private ClassifySubHomeNetModel classifySubHomeNetModel;

    public ClassifySubHomeController(Context context) {
        this.context = context.getApplicationContext();
        classifySubHomeNetModel = new ClassifySubHomeNetModel(context);
    }

    /**
     * 请求分类首页数据的方法
     */
    public void requestClassifyHomeData() {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifySubHomeDataRequestEvent event = new ClassifySubHomeDataRequestEvent();

        //通知开始
        event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_START);
        eventBus.post(event);

        try {
            classifySubHomeNetModel.requestClassifySubHomeData(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //通知请求完成
                    event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_FINISH);
                    ClassifyHomeDataBean classifyHomeDataBean = JSON.parseObject(response.toString(), ClassifyHomeDataBean.class);
                    event.setArg3(classifyHomeDataBean);
                    eventBus.post(event);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //通知出错
                    event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR);
                    event.setArg4(error);
                    eventBus.post(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //通知出错
            event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR);
            event.setArg4(e);
            eventBus.post(event);
        }
    }
}
