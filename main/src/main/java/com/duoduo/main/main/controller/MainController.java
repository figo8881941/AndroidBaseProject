package com.duoduo.main.main.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.event.MainTabRequestEvent;
import com.duoduo.main.main.model.MainNetModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 主界面Controller
 */
public class MainController {

    private Context context;

    private MainNetModel mainNetModel;

    public MainController(Context context) {
        this.context = context.getApplicationContext();
        mainNetModel = new MainNetModel(context);
    }

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData() {
        final EventBus eventBus = EventBus.getDefault();
        final MainTabRequestEvent event = new MainTabRequestEvent();

        //通知开始
        event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_START);
        eventBus.post(event);

        try {
            mainNetModel.requestTabData(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //通知请求完成
                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_SUCCESS);
                    MainTabEntity mainTabEntity = JSON.parseObject(response.toString(), MainTabEntity.class);
                    event.setArg3(mainTabEntity);
                    eventBus.post(event);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //通知出错
                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR);
                    event.setArg4(error);
                    eventBus.post(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //通知出错
            event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR);
            event.setArg4(e);
            eventBus.post(event);
        }
    }

}