package com.duoduo.main.classify.home.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.duoduo.commonbase.mvp.model.BaseModel;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;
import com.duoduo.main.classify.home.data.ClassifyTopicEntity;
import com.duoduo.main.classify.home.event.ClassifySubHomeDataRequestEvent;
import com.duoduo.main.classify.home.event.ClassifyTopicDataRequestEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 分类首页Model
 */
public class ClassifySubHomeModel extends BaseModel implements IClassifySubHomeModel {

    private ClassifySubHomeNetModel classifySubHomeNetModel;

    public ClassifySubHomeModel(Context context) {
        super(context);
        classifySubHomeNetModel = new ClassifySubHomeNetModel(context);
    }

    /**
     * 请求分类首页数据的方法
     */
    public void requestClassifySubHomeData() {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifySubHomeDataRequestEvent event = new ClassifySubHomeDataRequestEvent();

        //通知开始
        event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_START);
        eventBus.post(event);

        try {
            classifySubHomeNetModel.requestClassifySubHomeData(new IRequestCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject response) {
                    //通知请求完成
                    event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_SUCCESS);
                    ClassifySubHomeEntity classifySubHomeEntity = JSON.parseObject(response.toString(), ClassifySubHomeEntity.class);
                    event.setArg3(classifySubHomeEntity);
                    eventBus.post(event);
                }

                @Override
                public void onFailure(Exception e) {
                    //通知出错
                    event.setWhat(ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR);
                    event.setArg4(e);
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

    /**
     * 请求分类首页数据的方法
     */
    public void requestTopicData(int requestId, int topicId, int pageNum) {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifyTopicDataRequestEvent event = new ClassifyTopicDataRequestEvent();
        event.setArg1(requestId);
        event.setArg2(pageNum);

        //通知开始
        event.setWhat(ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_START);
        eventBus.post(event);

        try {
            classifySubHomeNetModel.requestTopicData(topicId, pageNum, new IRequestCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject response) {
                    //通知请求完成
                    event.setWhat(ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_SUCCESS);
                    ClassifyTopicEntity classifyTopicEntity = JSON.parseObject(response.toString(), ClassifyTopicEntity.class);
                    event.setArg3(classifyTopicEntity);
                    eventBus.post(event);
                }

                @Override
                public void onFailure(Exception e) {
                    //通知出错
                    event.setWhat(ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_ERROR);
                    event.setArg4(e);
                    eventBus.post(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //通知出错
            event.setWhat(ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_ERROR);
            event.setArg4(e);
            eventBus.post(event);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        classifySubHomeNetModel = null;
    }
}
