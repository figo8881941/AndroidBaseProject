package com.duoduo.main.main.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.duoduo.commonbase.mvp.model.BaseModel;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.event.MainTabRequestEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 主界面Model
 */
public class MainModel extends BaseModel implements IMainModel {

    private MainNetModel mainNetModel;

    public MainModel(Context context) {
        super(context);
        mainNetModel = new MainNetModel(context);
    }

//    /**
//     * 请求首页Tab数据的方法
//     */
//    public void requestTabData() {
//        final EventBus eventBus = EventBus.getDefault();
//        final MainTabRequestEvent event = new MainTabRequestEvent();
//
//        //通知开始
//        event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_START);
//        eventBus.post(event);
//
//        try {
//            mainNetModel.requestTabData(new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    //通知请求完成
//                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_SUCCESS);
//                    MainTabEntity mainTabEntity = JSON.parseObject(response.toString(), MainTabEntity.class);
//                    event.setArg3(mainTabEntity);
//                    eventBus.post(event);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //通知出错
//                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR);
//                    event.setArg4(error);
//                    eventBus.post(event);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            //通知出错
//            event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR);
//            event.setArg4(e);
//            eventBus.post(event);
//        }
//    }

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
            mainNetModel.requestTabData(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //通知出错
                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR);
                    event.setArg4(e);
                    eventBus.post(event);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //通知请求完成
                    String responseString = response.body().string();
                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_SUCCESS);
                    MainTabEntity mainTabEntity = JSON.parseObject(responseString, MainTabEntity.class);
                    event.setArg3(mainTabEntity);
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

    @Override
    public void destroy() {
        super.destroy();
        mainNetModel = null;
    }
}