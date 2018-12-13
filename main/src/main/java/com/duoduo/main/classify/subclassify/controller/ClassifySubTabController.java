package com.duoduo.main.classify.subclassify.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabProductDataEntity;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabTopicDataEntity;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabProductDataReqeustEvent;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabTopicDataReqeustEvent;
import com.duoduo.main.classify.subclassify.model.ClassifySubTabNetModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

public class ClassifySubTabController {

    private ClassifySubTabNetModel netModel;

    private Context context;

    public ClassifySubTabController(Context context) {
        this.context = context.getApplicationContext();
        netModel = new ClassifySubTabNetModel(this.context);
    }

    /**
     * 请求子分类商品数据的方法
     *
     * @param categoryId
     */
    public void requestSubTabProductData(int categoryId) {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifySubTabProductDataReqeustEvent event = new ClassifySubTabProductDataReqeustEvent();
        event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_START);
        event.setArg1(categoryId);

        eventBus.post(event);

        try {
            netModel.requestSubTabProductData(categoryId, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ClassifySubTabProductDataEntity dataEntity = JSON.parseObject(response.toString(), ClassifySubTabProductDataEntity.class);
                    event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_SUCCESS);
                    event.setArg3(dataEntity);
                    eventBus.post(event);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR);
                    event.setArg4(error);
                    eventBus.post(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

            event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR);
            event.setArg4(e);
            eventBus.post(event);
        }

    }

    /**
     * 请求子分类排行榜数据的方法
     *
     * @param topicId
     */
    public void requestSubTabTopicData(int topicId) {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifySubTabTopicDataReqeustEvent event = new ClassifySubTabTopicDataReqeustEvent();
        event.setWhat(ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_START);
        event.setArg1(topicId);

        eventBus.post(event);

        try {
            netModel.requestSubTabTopicData(topicId, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ClassifySubTabTopicDataEntity dataEntity = JSON.parseObject(response.toString(), ClassifySubTabTopicDataEntity.class);
                    event.setWhat(ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_SUCCESS);
                    event.setArg3(dataEntity);
                    eventBus.post(event);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    event.setWhat(ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR);
                    event.setArg4(error);
                    eventBus.post(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

            event.setWhat(ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR);
            event.setArg4(e);
            eventBus.post(event);
        }

    }

}
