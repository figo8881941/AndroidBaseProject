package com.duoduo.main.classify.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;
import com.duoduo.main.classify.data.ClassifySubTabDataBean;
import com.duoduo.main.classify.home.controller.ClassifySubHomeController;
import com.duoduo.main.classify.home.event.ClassifySubHomeDataRequestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 分类子首页Fragment
 */
public class ClassifySubHomeFragment extends BaseFragment<ClassifySubTabDataBean.CategoryListEntity> {

    private ViewGroup mainView;

    private ClassifySubHomeController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        controller = new ClassifySubHomeController(getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_home_fragment, container, false);
        initView();
        controller.requestClassifySubHomeData();
        return mainView;
    }

    private void initView() {
        TextView name = (TextView) mainView.findViewById(R.id.name);
        if (data != null) {
            name.setText(data.getCategoryName());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifySubHomeDataRequestEvent(ClassifySubHomeDataRequestEvent event) {
        if (isDestroy || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_START: {

            }
            break;
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_FINISH: {

            }
            break;
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR: {

            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
