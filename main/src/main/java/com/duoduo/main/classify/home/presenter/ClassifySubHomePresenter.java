package com.duoduo.main.classify.home.presenter;

import android.content.Context;

import com.duoduo.commonbase.mvp.presenter.BasePresenter;
import com.duoduo.commonbusiness.net.common.CommonNetErrorHandler;
import com.duoduo.main.base.data.ProductDataUtils;
import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;
import com.duoduo.main.classify.home.data.ClassifyTopicEntity;
import com.duoduo.main.classify.home.event.ClassifySubHomeDataRequestEvent;
import com.duoduo.main.classify.home.event.ClassifyTopicDataRequestEvent;
import com.duoduo.main.classify.home.model.ClassifySubHomeModel;
import com.duoduo.main.classify.home.model.IClassifySubHomeModel;
import com.duoduo.main.classify.home.view.IClassifySubHomeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 分类首页presenter
 */
public class ClassifySubHomePresenter extends BasePresenter<IClassifySubHomeView, IClassifySubHomeModel> implements IClassifySubHomePresenter {


    //首页数据
    private ClassifySubHomeEntity homeEntity;

    //当前主题页
    private int currentTopicPage = 1;
    //是否有下一页数据
    private boolean hasNextPage = true;

    //首页Tab数据
    private ClassifySubTabEntity.CategoryNewListEntity homeTabData;

    //主题数据
    List<TopicTwoProductListEntity> topicData;

    public ClassifySubHomePresenter(Context context
            , IClassifySubHomeView view, ClassifySubTabEntity.CategoryNewListEntity homeTabData) {
        super(context, view);
        this.homeTabData = homeTabData;
        EventBus.getDefault().register(this);
    }

    @Override
    public IClassifySubHomeModel createModel() {
        return new ClassifySubHomeModel(context);
    }

    @Override
    public void requestClassifySubHomeData() {
        if (model != null) {
            model.requestClassifySubHomeData();
        }
    }

    @Override
    public void requestTopicData(int requestId, int topicId, int pageNum) {
        if (model != null) {
            model.requestTopicData(requestId, topicId, pageNum);
        }
    }

    @Override
    public void handlePullToRefresh() {
        if (view == null || view.isDestroy()) {
            return;
        }
        if (hasHomeData()) {
            //如果已经有数据，下拉重新请求数据
            requestClassifySubHomeData();
            //刷新数据，先停止banner滚动
            view.stopBannerAutoPlay();
        } else {
            //如果没有数据，不下拉刷新
            view.finishRefresh();
        }
    }

    @Override
    public void handleToLoadMore() {
        if (view == null || view.isDestroy()) {
            return;
        }
        if (hasTopicData() && hasNextPageData()) {
            //如果有主题数据，上拉请求更多
            ClassifySubHomeEntity.TopicModuleDtoEntity topicModuleDtoEntity = homeEntity.getTopicModuleDto();
            requestTopicData(homeTabData.getId(), topicModuleDtoEntity.getTopicPageId(), currentTopicPage + 1);
        } else {
            //如果没有主题数据，不上拉请求更多
            view.finishLoadMore();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifySubHomeDataRequestEvent(ClassifySubHomeDataRequestEvent event) {
        if (view == null || view.isDestroy() || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_START: {

            }
            break;
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_SUCCESS: {

                homeEntity = event.getArg3();

                //主题页重置为第1页
                currentTopicPage = 1;

                //重置原来的主题数据
                topicData = null;

                //如果有主题，就请求主题数据
                ClassifySubHomeEntity.TopicModuleDtoEntity topicModuleDtoEntity = homeEntity.getTopicModuleDto();
                if (topicModuleDtoEntity != null) {
                    requestTopicData(homeTabData.getId(), topicModuleDtoEntity.getTopicPageId(), currentTopicPage);
                }

                if (hasHomeData()) {
                    //如果有数据，打开上拉刷新
                    view.setEnableRefresh(true);
                }

                //先禁止下拉加载更多
                view.setEnableLoadMore(false);

                //数据初始化View
                view.updateViewBySubHomeData(homeEntity);

                //刷新完成
                view.finishRefresh();

                //开启banner滚动
                view.startBannerAutoPlay();
            }
            break;
            case ClassifySubHomeDataRequestEvent.EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(context, exception);

                //刷新完成
                view.finishRefresh();
            }
            break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifyTopicDataRequestEvent(ClassifyTopicDataRequestEvent event) {
        if (homeTabData == null || view == null ||  view.isDestroy() || event == null) {
            return;
        }
        int requestId = event.getArg1();
        if (requestId != homeTabData.getId()) {
            return;
        }
        int page = event.getArg2();
        int what = event.getWhat();
        switch (what) {
            case ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_START: {
                if (page == 1) {
                    view.showLoadingDialog();
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
                    topicData = ProductDataUtils.makeTopicTwoProductListEntitys(topicData, productInfoEntities);
                    view.updateListByTopicData(topicData);
                    if (hasTopicData()) {
                        //如果有主题数据，打开上拉加载更多
                        view.setEnableLoadMore(true);
                    }
                    view.finishLoadMore();
                } else {
                    //如果没有商品数据
                    hasNextPage = false;
                    view.finishLoadMoreWithNoMoreData();
                }
                view.hideLoadingDialog();
            }
            break;
            case ClassifyTopicDataRequestEvent.EVENT_CLASSIFY_TOPIC_DATA_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(context, exception);
                view.finishLoadMore();
                view.hideLoadingDialog();
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
     * 是否有下一页数据
     *
     * @return
     */
    private boolean hasNextPageData() {
        return hasNextPage;
    }

    /**
     * 是否有主题数据
     *
     * @return
     */
    private boolean hasTopicData() {
        return topicData != null && !topicData.isEmpty();
    }

    @Override
    public void destroy() {
        super.destroy();
        EventBus.getDefault().unregister(this);

        homeEntity = null;

        topicData = null;
        currentTopicPage = 1;
        hasNextPage = true;
    }
}
