package com.duoduo.main.classify.subclassify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.subclassify.controller.ClassifySubTabController;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabProductDataEntity;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabTopicDataEntity;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabProductDataReqeustEvent;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabTopicDataReqeustEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 子分类Fragment
 */
public class ClassifySubTabFragment extends BaseFragment<ClassifySubTabEntity.CategoryNewListEntity> {

    private ViewGroup mainView;

    private ClassifySubTabController controller;

    private ClassifySubTabProductDataEntity productDataEntity;

    private ClassifySubTabTopicDataEntity topicDataEntity;

    private boolean isDodingProductDataRequest = false;

    private boolean isDodingTopicDataRequest = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        controller = new ClassifySubTabController(getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_tab_fragment, container, false);
        initView();
        if (data != null) {
            controller.requestSubTabProductData(data.getId());
            controller.requestSubTabTopicData(data.getTopicId());
        }
        return mainView;
    }

    private void initView() {
        TextView name = (TextView) mainView.findViewById(R.id.name);
        if (data != null) {
            name.setText(data.getCategoryName());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestSubTabProductData(ClassifySubTabProductDataReqeustEvent event) {
        if (isDestroy || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_START: {
                isDodingProductDataRequest = true;
            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_SUCCESS: {
                isDodingProductDataRequest = false;
                productDataEntity = event.getArg3();
            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR: {
                isDodingProductDataRequest = false;
            }
            break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestSubTabTopicData(ClassifySubTabTopicDataReqeustEvent event) {
        if (isDestroy || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_START: {
                isDodingTopicDataRequest = true;
            }
            break;
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_SUCCESS: {
                isDodingTopicDataRequest = false;
                topicDataEntity = event.getArg3();
            }
            break;
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR: {
                isDodingTopicDataRequest = false;
            }
            break;
        }
    }

    private boolean hasData() {
        return productDataEntity != null || topicDataEntity != null;
    }

    private boolean isDodingRequest() {
        return isDodingProductDataRequest || isDodingTopicDataRequest;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
