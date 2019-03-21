package com.duoduo.main.classify.subclassify.presenter;

import android.content.Context;

import com.duoduo.commonbase.mvp.presenter.BasePresenter;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.main.base.data.ProductDataUtils;
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabProductDataEntity;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabTopicDataEntity;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabProductDataReqeustEvent;
import com.duoduo.main.classify.subclassify.event.ClassifySubTabTopicDataReqeustEvent;
import com.duoduo.main.classify.subclassify.model.ClassifySubTabModel;
import com.duoduo.main.classify.subclassify.model.IClassifySubTabModel;
import com.duoduo.main.classify.subclassify.view.IClassifySubTabView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 分类子Tab presenter
 */
public class ClassifySubTabPresenter extends BasePresenter<IClassifySubTabView, IClassifySubTabModel> implements IClassifySubTabPresenter {

    private ClassifySubTabEntity.CategoryNewListEntity tabData;

    //当前商品数据页码
    private int currentProductPage = 1;

    //当前子分类商品数据
    private ClassifySubTabProductDataEntity productDataEntity;
    //当前子分类经过加工后的商品列表数据
    private List<TopicTwoProductListEntity> productListData;

    //当前子分类热销排行榜主题数据
    private ClassifySubTabTopicDataEntity topicDataEntity;

    //是否正在进行商品数据请求
    private boolean isDodingProductDataRequest = false;

    //是否正在进行热销排行榜主题数据请求
    private boolean isDodingTopicDataRequest = false;

    public ClassifySubTabPresenter(Context context, IClassifySubTabView view, ClassifySubTabEntity.CategoryNewListEntity tabData) {
        super(context, view);
        this.tabData = tabData;
        EventBus.getDefault().register(this);
    }

    @Override
    public IClassifySubTabModel createModel() {
        return new ClassifySubTabModel(context);
    }


    @Override
    public void requestFirstData() {
        if (tabData != null && model != null) {
            model.requestSubTabProductData(tabData.getId(), 1);
            model.requestSubTabTopicData(tabData.getTopicId());
        }
    }

    @Override
    public void handleSelected() {
        //如果被选中，而且当前没有数据，也没有正在进行请求，就请求数据
        if (!hasData() && !isDodingRequest()) {
            requestFirstData();
        }
    }

    /**
     * 是否已经有数据
     * @return
     */
    private boolean hasData() {
        return productDataEntity != null || topicDataEntity != null;
    }

    /**
     * 是否正在进行请求
     * @return
     */
    private boolean isDodingRequest() {
        return isDodingProductDataRequest || isDodingTopicDataRequest;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestSubTabProductData(ClassifySubTabProductDataReqeustEvent event) {
        if (tabData == null || view == null || view.isDestroy() || event == null) {
            return;
        }
        int categoryId = event.getArg1();
        if (categoryId != tabData.getId()) {
            //不是这个子Tab的事件
            return;
        }
        currentProductPage = event.getArg2();
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_START: {
                isDodingProductDataRequest = true;
                if (currentProductPage == 1) {
                    view.showLoadingDialog();
                }
            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_SUCCESS: {
                isDodingProductDataRequest = false;
                productDataEntity = event.getArg3();
                if (productDataEntity != null) {
                    productListData = ProductDataUtils
                            .makeTopicTwoProductListEntitys(productListData, productDataEntity.getProductList());
                    view.updateListByProductData(productListData);
                }
                hideCommonLoadingWhenNoRequest();
            }
            break;
            case ClassifySubTabProductDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR: {
                isDodingProductDataRequest = false;
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(context, exception);
                hideCommonLoadingWhenNoRequest();
            }
            break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestSubTabTopicData(ClassifySubTabTopicDataReqeustEvent event) {
        if (tabData == null || view == null || view.isDestroy() || event == null) {
            return;
        }
        int topicId = event.getArg1();
        if (topicId != tabData.getTopicId()) {
            //不是这个tab的事件
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabTopicDataReqeustEvent.EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_START: {
                isDodingTopicDataRequest = true;
                view.showLoadingDialog();
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
                CommonNetErrorHandler.handleNetError(context, exception);
                hideCommonLoadingWhenNoRequest();
            }
            break;
        }
    }

    /**
     * 判断是否正在请求，如果没有，就隐藏loading对话框
     */
    private void hideCommonLoadingWhenNoRequest() {
        if (!isDodingRequest() && view != null) {
            view.hideLoadingDialog();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EventBus.getDefault().unregister(this);

        currentProductPage = 1;
        productDataEntity = null;
        topicDataEntity = null;
        isDodingProductDataRequest = false;
        isDodingTopicDataRequest = false;
    }
}
