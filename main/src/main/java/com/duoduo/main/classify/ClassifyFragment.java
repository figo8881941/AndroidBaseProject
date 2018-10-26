package com.duoduo.main.classify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;
import com.duoduo.main.classify.controller.ClassifyController;
import com.duoduo.main.classify.event.ClassifyHomeDataRequestEvent;
import com.duoduo.main.classify.event.ClassifyTabDataRequestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 分类Fragment
 */
public class ClassifyFragment extends BaseFragment {

    private ViewGroup mainView;

    private ClassifyController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        controller = new ClassifyController(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_fragment, container, false);
        initView();
        controller.requestClassifyTabData();
        return mainView;
    }

    private void initView() {

    }

    @Override
    public void onSelected() {
        super.onSelected();
    }

    @Override
    public void onUnSelected() {
        super.onUnSelected();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifyTabDataRequestEvent(ClassifyTabDataRequestEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifyHomeDataRequestEvent(ClassifyHomeDataRequestEvent event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
