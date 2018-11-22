package com.duoduo.main.classify.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.event.ClassifySubTabDataRequestEvent;
import com.duoduo.main.classify.model.ClassifyNetModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 分类controller
 */
public class ClassifyController {

    private Context context;

    private ClassifyNetModel classifyNetModel;

    public ClassifyController(Context context) {
        this.context = context.getApplicationContext();
        classifyNetModel = new ClassifyNetModel(context);
    }

    /**
     * 请求分类Tab数据的方法
     */
    public void requestClassifySubTabData() {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifySubTabDataRequestEvent event = new ClassifySubTabDataRequestEvent();

        //通知开始
        event.setWhat(ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_START);
        eventBus.post(event);

        try {
            classifyNetModel.requestClassifySubTabData(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //通知请求完成
                    event.setWhat(ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_FINISH);
                    ClassifySubTabEntity classifySubTabEntity = JSON.parseObject(response.toString(), ClassifySubTabEntity.class);
                    event.setArg3(classifySubTabEntity);
                    eventBus.post(event);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //通知出错
                    event.setWhat(ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_ERROR);
                    event.setArg4(error);
                    eventBus.post(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //通知出错
            event.setWhat(ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_ERROR);
            event.setArg4(e);
            eventBus.post(event);
        }
    }

}
