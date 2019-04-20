package com.duoduo.main.main.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.duoduo.commonbase.mvp.model.BaseModel;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.event.MainTabRequestEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 主界面Model
 */
public class MainModel extends BaseModel implements IMainModel {

    private MainNetModel mainNetModel;

    public MainModel(Context context) {
        super(context);
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
            mainNetModel.requestTabData(new IRequestCallback<JSONObject>() {
                @Override
                public void onFailure(Exception e) {
                    //通知出错
                    event.setWhat(MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR);
                    event.setArg4(e);
                    eventBus.post(event);
                }

                @Override
                public void onSuccess(JSONObject responseData) {
                    //通知请求完成
                    String responseString = responseData.toString();
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