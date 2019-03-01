package com.duoduo.main.classify.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.commonbase.mvp.model.BaseModel;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.event.ClassifySubTabDataRequestEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 分类Model
 */
public class ClassifyModel extends BaseModel implements IClassifyModel {

    private ClassifyNetModel classifyNetModel;

    public ClassifyModel(Context context) {
        super(context);
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
                    event.setWhat(ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_SUCCESS);
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

    @Override
    public void destroy() {
        super.destroy();
        classifyNetModel = null;
    }
}
