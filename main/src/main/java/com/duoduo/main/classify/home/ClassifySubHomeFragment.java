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

import com.duoduo.commonbusiness.dialog.CommonLoadingDialogHelper;
import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.main.R;
import com.duoduo.main.base.data.ProductDataUtils;
import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.home.controller.ClassifySubHomeController;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;
import com.duoduo.main.classify.home.data.ClassifyTopicEntity;
import com.duoduo.main.classify.home.event.ClassifySubHomeDataRequestEvent;
import com.duoduo.main.classify.home.event.ClassifyTopicDataRequestEvent;
import com.duoduo.main.classify.home.view.ClassifySubHomeAdapter;
import com.duoduo.main.classify.home.view.ClassifySubHomeHeaderView;
import com.duoduo.main.classify.home.view.ClassifySubHomeViewHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 分类子首页Fragment
 */
public class ClassifySubHomeFragment extends BaseFragment<ClassifySubTabEntity.CategoryNewListEntity> {

    private ViewGroup mainView;

    private SmartRefreshLayout refreshLayout;

    private RecyclerView recyclerView;
    private ClassifySubHomeHeaderView recyclerHeaderView;
    private ClassifySubHomeAdapter recyclerAdapter;

    private CommonLoadingDialogHelper loadingDialogHelper;

    //首页数据
    private ClassifySubHomeEntity homeEntity;

    //当前主题页
    private int currentTopicPage = 1;
    //是否有下一页数据
    private boolean hasNextPage = true;

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

        refreshLayout = (SmartRefreshLayout) mainView.findViewById(R.id.smartRefreshLayout);
        //一开始，没有数据，禁止下拉、上拉刷新，等加载数据回来，再打开
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (hasHomeData()) {
                    //如果已经有数据，下拉重新请求数据
                    if (controller != null) {
                        controller.requestClassifySubHomeData();
                    }
                    //刷新数据，先停止banner滚动
                    stopBannerAutoPlay();
                } else {
                    //如果没有数据，不下拉刷新
                    refreshlayout.finishRefresh();
                }
            }
        });

        //上拉加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (hasTopicData() && hasNextPageData()) {
                    //如果有主题数据，上拉请求更多
                    ClassifySubHomeEntity.TopicModuleDtoEntity topicModuleDtoEntity = homeEntity.getTopicModuleDto();
                    controller.requestTopicData(data.getId(), topicModuleDtoEntity.getTopicPageId(), currentTopicPage + 1);
                } else {
                    //如果没有主题数据，不上拉请求更多
                    refreshlayout.finishLoadmore();
                }
            }
        });

        recyclerAdapter = new ClassifySubHomeAdapter(context);

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        loadingDialogHelper = new CommonLoadingDialogHelper(getActivity());
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
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_SUCCESS: {
                homeEntity = event.getArg3();

                if (hasHomeData()) {
                    //如果有数据，打开上拉刷新
                    refreshLayout.setEnableRefresh(true);
                }

                //主题页重制为第1页
                currentTopicPage = 1;

                //如果有主题，就请求主题数据
                ClassifySubHomeEntity.TopicModuleDtoEntity topicModuleDtoEntity = homeEntity.getTopicModuleDto();
                if (topicModuleDtoEntity != null) {
                    controller.requestTopicData(data.getId(), topicModuleDtoEntity.getTopicPageId(), currentTopicPage);
                }
                //清空原来的主题数据
                recyclerAdapter.setData(null);
                //先禁止下拉加载更多
                refreshLayout.setEnableLoadmore(false);

                //初始化headerview
                recyclerHeaderView = ClassifySubHomeViewHelper.initHeaderViewByData(
                        getContext().getApplicationContext(), homeEntity, recyclerHeaderView);
                recyclerAdapter.setHeaderView(recyclerHeaderView);
                recyclerView.setAdapter(recyclerAdapter);

                refreshLayout.finishRefresh();

                //开启banner滚动
                startBannerAutoPlay();
            }
            break;
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getContext().getApplicationContext(), exception);

                refreshLayout.finishRefresh();
            }
            break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifyTopicDataRequestEvent(ClassifyTopicDataRequestEvent event) {
        if (data == null || isDestroy || event == null) {
            return;
        }
        int requestId = event.getArg1();
        if (requestId != data.getId()) {
            return;
        }
        int page = event.getArg2();
        int what = event.getWhat();
        switch (what) {
            case ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_START: {
                if (page == 1) {
                    loadingDialogHelper.showLoadingDialog();
                }
            }
            break;
            case ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_SUCCESS: {
                ClassifyTopicEntity classifyTopicEntity = event.getArg3();
                List<ProductInfoEntity> productInfoEntities = classifyTopicEntity != null ?
                        classifyTopicEntity.getProductList() : null;
                if (productInfoEntities != null && !productInfoEntities.isEmpty()) {
                    //如果有商品数据
                    currentTopicPage = event.getArg2();
                    List<TopicTwoProductListEntity> data = ProductDataUtils.makeTopicTwoProductListEntitys(recyclerAdapter.getData(), productInfoEntities);
                    recyclerAdapter.setData(data);
                    recyclerAdapter.notifyDataSetChanged();
                    if (hasTopicData()) {
                        //如果有主题数据，打开上拉加载更多
                        refreshLayout.setEnableLoadmore(true);
                    }
                    refreshLayout.finishLoadmore();
                } else {
                    //如果没有商品数据
                    hasNextPage = false;
                    refreshLayout.finishLoadmoreWithNoMoreData();
                }
                loadingDialogHelper.hideLoadingDialog();
            }
            break;
            case ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getContext().getApplicationContext(), exception);
                refreshLayout.finishLoadmore();
                loadingDialogHelper.hideLoadingDialog();
            }
            break;
            default:
                break;
        }

    }

    /**
     * 是否有首页数据
     *
     * @return
     */
    private boolean hasHomeData() {
        return homeEntity != null;
    }

    /**
     * 是否有主题数据
     *
     * @return
     */
    private boolean hasTopicData() {
        List<TopicTwoProductListEntity> datas = recyclerAdapter != null
                ? recyclerAdapter.getData() : null;
        return datas != null && !datas.isEmpty();
    }

    /**
     * 是否有下一页数据
     *
     * @return
     */
    private boolean hasNextPageData() {
        return hasNextPage;
    }

    /**
     * 如果有banner，就开启banner自动播放
     */
    private void startBannerAutoPlay() {
        if (recyclerHeaderView != null) {
            recyclerHeaderView.startBannerAutoPlay();
        }
    }

    /**
     * 如果有banner，就停止banner自动播放
     */
    private void stopBannerAutoPlay() {
        if (recyclerHeaderView != null) {
            recyclerHeaderView.stopBannerAutoPlay();
        }
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
        EventBus.getDefault().unregister(this);
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

        if (loadingDialogHelper != null) {
            loadingDialogHelper.destroy();
            loadingDialogHelper = null;
        }

        homeEntity = null;

        currentTopicPage = 1;
        hasNextPage = true;
        controller = null;
    }
}
