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

import com.duoduo.commonbusiness.fragment.BaseLoadingDialogFragment;
import com.duoduo.main.R;
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;
import com.duoduo.main.classify.home.presenter.ClassifySubHomePresenter;
import com.duoduo.main.classify.home.presenter.IClassifySubHomePresenter;
import com.duoduo.main.classify.home.view.ClassifySubHomeAdapter;
import com.duoduo.main.classify.home.view.ClassifySubHomeHeaderView;
import com.duoduo.main.classify.home.view.ClassifySubHomeViewHelper;
import com.duoduo.main.classify.home.view.IClassifySubHomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * 分类子首页Fragment
 */
public class ClassifySubHomeFragment
        extends BaseLoadingDialogFragment<ClassifySubTabEntity.CategoryNewListEntity>
        implements IClassifySubHomeView {

    private IClassifySubHomePresenter presenter;

    private ViewGroup mainView;

    private SmartRefreshLayout refreshLayout;

    private RecyclerView recyclerView;
    private ClassifySubHomeHeaderView recyclerHeaderView;
    private ClassifySubHomeAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ClassifySubHomePresenter(
                getActivity().getApplicationContext(), this, data);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_home_fragment, container, false);
        initView();
        presenter.requestClassifySubHomeData();
        return mainView;
    }

    private void initView() {
        final Context context = getContext().getApplicationContext();

        refreshLayout = (SmartRefreshLayout) mainView.findViewById(R.id.smartRefreshLayout);
        //一开始，没有数据，禁止下拉、上拉刷新，等加载数据回来，再打开
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (presenter == null) {
                    return;
                }
                presenter.handlePullToRefresh();
            }
        });

        //上拉加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (presenter == null) {
                    return;
                }
                presenter.handleToLoadMore();
            }
        });

        recyclerAdapter = new ClassifySubHomeAdapter(context);

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    /**
     * 如果有banner，就开启banner自动播放
     */
    @Override
    public void startBannerAutoPlay() {
        if (recyclerHeaderView != null) {
            recyclerHeaderView.startBannerAutoPlay();
        }
    }

    /**
     * 如果有banner，就停止banner自动播放
     */
    @Override
    public void stopBannerAutoPlay() {
        if (recyclerHeaderView != null) {
            recyclerHeaderView.stopBannerAutoPlay();
        }
    }

    @Override
    public void finishRefresh() {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void finishLoadMore() {
        if (refreshLayout != null) {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void finishLoadMoreWithNoMoreData() {
        if (refreshLayout != null) {
            refreshLayout.finishLoadmoreWithNoMoreData();
        }
    }

    @Override
    public void setEnableRefresh(boolean enable) {
        if (refreshLayout != null) {
            refreshLayout.setEnableRefresh(enable);
        }
    }

    @Override
    public void setEnableLoadMore(boolean enable) {
        if (refreshLayout != null) {
            refreshLayout.setEnableLoadmore(enable);
        }
    }

    @Override
    public void updateViewBySubHomeData(ClassifySubHomeEntity homeEntity) {
        if (isDestroy) {
            return;
        }
        //清空原来的主题数据
        recyclerAdapter.setData(null);
        //初始化headerview
        recyclerHeaderView = ClassifySubHomeViewHelper.initHeaderViewByData(
                getContext().getApplicationContext(), homeEntity, recyclerHeaderView);
        recyclerAdapter.setHeaderView(recyclerHeaderView);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void updateListByTopicData(List<TopicTwoProductListEntity> topicData) {
        if (isDestroy) {
            return;
        }
        recyclerAdapter.setData(topicData);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected() {
        super.onSelected();
        startBannerAutoPlay();
    }

    @Override
    public void onUnSelected() {
        super.onUnSelected();
        stopBannerAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSelected) {
            startBannerAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopBannerAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
            presenter = null;
        }
        mainView = null;
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadmore();
            refreshLayout.setOnRefreshListener(null);
            refreshLayout.setOnLoadmoreListener(null);
            refreshLayout = null;
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            recyclerView = null;
        }
        recyclerAdapter = null;
        recyclerHeaderView = null;
    }
}
