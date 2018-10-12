package com.duoduo.main.main.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.main.main.data.MainTabDataBean;
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
        context = context.getApplicationContext();
        mainNetModel = new MainNetModel(context);
    }

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData() {
        final EventBus eventBus = EventBus.getDefault();
        final MainTabRequestEvent event = new MainTabRequestEvent();
                mainNetModel.requestTabData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                MainTabDataBean mainTabDataBean = JSON.parseObject(response.toString(), MainTabDataBean.class);
                event.setArg3(mainTabDataBean);
                eventBus.post(event);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}