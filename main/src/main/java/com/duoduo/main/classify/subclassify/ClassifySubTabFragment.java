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
import com.duoduo.main.classify.subclassify.event.ClassifySubTabProductDataReqeustEvent;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabTopicDataReqeustEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 子分类Fragment
 */
public class ClassifySubTabFragment extends BaseFragment<ClassifySubTabEntity.CategoryNewListEntity> {

    private ViewGroup mainView;

    private ClassifySubTabController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ClassifySubTabController(getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_tab_fragment, container, false);
        initView();
        controller.requestSubTabProductData(data.getId());
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

            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_FINISH: {
                ClassifySubTabProductDataEntity entity = event.getArg3();

            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR: {

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

            }
            break;
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_FINISH: {

            }
            break;
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR: {

            }
            break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
