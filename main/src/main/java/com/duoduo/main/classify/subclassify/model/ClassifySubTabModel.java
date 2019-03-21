package com.duoduo.main.classify.subclassify.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.duoduo.commonbase.mvp.model.BaseModel;
import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabProductDataEntity;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabTopicDataEntity;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabProductDataReqeustEvent;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabTopicDataReqeustEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * 分类子Tab model
 */
public class ClassifySubTabModel extends BaseModel implements IClassifySubTabModel {

    private ClassifySubTabNetModel netModel;

    public ClassifySubTabModel(Context context) {
        super(context);
        netModel = new ClassifySubTabNetModel(this.context);
    }

    /**
     * 请求子分类商品数据的方法
     *
     * @param categoryId
     */
    public void requestSubTabProductData(int categoryId, int pageNume) {
        final EventBus eventBus = EventBus.getDefault();
        final ClassifySubTabProductDataReqeustEvent event = new ClassifySubTabProductDataReqeustEvent();
        event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_START);
        event.setArg1(categoryId);
        event.setArg2(pageNume);
        eventBus.post(event);

        try {
            netModel.requestSubTabProductData(categoryId, pageNume, new IRequestCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject response) {
                    ClassifySubTabProductDataEntity dataEntity = JSON.parseObject(response.toString(), ClassifySubTabProductDataEntity.class);
                    event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_SUCCESS);
                    event.setArg3(dataEntity);
                    eventBus.post(event);
                }

                @Override
                public void onFailure(Exception e) {
                    event.setWhat(ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR);
                    event.setArg4(e);
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
            netModel.requestSubTabTopicData(topicId, new IRequestCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject response) {
                    ClassifySubTabTopicDataEntity dataEntity = JSON.parseObject(response.toString(), ClassifySubTabTopicDataEntity.class);
                    event.setWhat(ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_SUCCESS);
                    event.setArg3(dataEntity);
                    eventBus.post(event);
                }

                @Override
                public void onFailure(Exception e) {
                    event.setWhat(ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR);
                    event.setArg4(e);
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

    @Override
    public void destroy() {
        super.destroy();
        netModel = null;
    }
}
