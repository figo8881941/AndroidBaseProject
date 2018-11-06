package com.duoduo.main.classify.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;
import com.duoduo.main.classify.data.ClassifySubTabDataBean;
import com.duoduo.main.classify.home.controller.ClassifySubHomeController;
import com.duoduo.main.classify.home.data.ClassifySubHomeDataBean;
import com.duoduo.main.classify.home.event.ClassifySubHomeDataRequestEvent;
import com.duoduo.main.classify.home.view.ClassifySubHomeAdapter;
import com.duoduo.main.classify.home.view.ClassifySubHomeViewHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 分类子首页Fragment
 */
public class ClassifySubHomeFragment extends BaseFragment<ClassifySubTabDataBean.CategoryNewListEntity> {

    private ViewGroup mainView;

    private RecyclerView recyclerView;
    private View recyclerHeaderView;
    private ClassifySubHomeAdapter recyclerAdapter;

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
        final Context context = getContext().getApplicationContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        recyclerAdapter = new ClassifySubHomeAdapter(context);

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
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
                ClassifySubHomeDataBean homeDataBean = event.getArg3();
                recyclerHeaderView = ClassifySubHomeViewHelper.createHeaderViewByData(getContext().getApplicationContext(), homeDataBean);
                recyclerAdapter.setHeaderView(recyclerHeaderView);
                recyclerAdapter.notifyDataSetChanged();
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
