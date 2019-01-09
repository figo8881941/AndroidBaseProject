package com.duoduo.main.classify.subclassify;

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
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.home.view.ClassifySubHomeAdapter;
import com.duoduo.main.classify.subclassify.model.ClassifySubTabModel;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabProductDataEntity;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabTopicDataEntity;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabProductDataReqeustEvent;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabTopicDataReqeustEvent;
import com.duoduo.main.classify.subclassify.model.IClassifySubTabModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 子分类Fragment
 */
public class ClassifySubTabFragment extends BaseFragment<ClassifySubTabEntity.CategoryNewListEntity> {

    private ViewGroup mainView;

    private IClassifySubTabModel classifySubTabModel;

    //当前商品数据页码
    private int currentProductPage = 1;

    //当前子分类商品数据
    private ClassifySubTabProductDataEntity productDataEntity;

    //当前子分类热销排行榜主题数据
    private ClassifySubTabTopicDataEntity topicDataEntity;

    //是否正在进行商品数据请求
    private boolean isDodingProductDataRequest = false;

    //是否正在进行热销排行榜主题数据请求
    private boolean isDodingTopicDataRequest = false;

    private RecyclerView recyclerView;

    private ClassifySubHomeAdapter recyclerAdapter;

    //Commonloading
    private CommonLoadingDialogHelper loadingDialogHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        classifySubTabModel = new ClassifySubTabModel(getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_tab_fragment, container, false);
        initView();
        return mainView;
    }

    private void initView() {
        final Context context = getContext().getApplicationContext();

        recyclerAdapter = new ClassifySubHomeAdapter(context);

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        loadingDialogHelper = new CommonLoadingDialogHelper(getActivity());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestSubTabProductData(ClassifySubTabProductDataReqeustEvent event) {
        if (data == null || isDestroy || event == null) {
            return;
        }
        int categoryId = event.getArg1();
        if (categoryId != data.getId()) {
            //不是这个子Tab的事件
            return;
        }
        currentProductPage = event.getArg2();
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_START: {
                isDodingProductDataRequest = true;
                if (currentProductPage == 1) {
                    loadingDialogHelper.showLoadingDialog();
                }
            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_SUCCESS: {
                isDodingProductDataRequest = false;
                productDataEntity = event.getArg3();
                if (productDataEntity != null) {
                    List<TopicTwoProductListEntity> entities = ProductDataUtils
                            .makeTopicTwoProductListEntitys(recyclerAdapter.getData(), productDataEntity.getProductList());
                    recyclerAdapter.setData(entities);
                    recyclerAdapter.notifyDataSetChanged();
                }
                hideCommonLoadingWhenNoRequest();
            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR: {
                isDodingProductDataRequest = false;
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getContext().getApplicationContext(), exception);
                hideCommonLoadingWhenNoRequest();
            }
            break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestSubTabTopicData(ClassifySubTabTopicDataReqeustEvent event) {
        if (data == null || isDestroy || event == null) {
            return;
        }
        int topicId = event.getArg1();
        if (topicId != data.getTopicId()) {
            //不是这个tab的事件
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_START: {
                isDodingTopicDataRequest = true;
                loadingDialogHelper.showLoadingDialog();
            }
            break;
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_SUCCESS: {
                isDodingTopicDataRequest = false;
                topicDataEntity = event.getArg3();
                hideCommonLoadingWhenNoRequest();
            }
            break;
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR: {
                isDodingTopicDataRequest = false;
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getContext().getApplicationContext(), exception);
                hideCommonLoadingWhenNoRequest();
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

    /**
     * 判断是否正在请求，如果没有，就隐藏loading对话框
     */
    private void hideCommonLoadingWhenNoRequest() {
        if (!isDodingRequest() && loadingDialogHelper != null) {
            loadingDialogHelper.hideLoadingDialog();
        }
    }

    /**
     * 请求首页数据
     */
    private void requestFirstData() {
        if (data != null) {
            classifySubTabModel.requestSubTabProductData(data.getId(), 1);
            classifySubTabModel.requestSubTabTopicData(data.getTopicId());
        }
    }

    @Override
    public void onSelected() {
        super.onSelected();
        //如果被选中，而且当前没有数据，也没有正在进行请求，就请求数据
        if (!hasData() && !isDodingRequest()) {
            requestFirstData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        currentProductPage = 1;
        productDataEntity = null;
        topicDataEntity = null;
        isDodingProductDataRequest = false;
        isDodingTopicDataRequest = false;

        mainView = null;
        classifySubTabModel = null;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            recyclerView = null;
        }
        recyclerAdapter = null;

        if (loadingDialogHelper != null) {
            loadingDialogHelper.destroy();
            loadingDialogHelper = null;
        }
    }
}
