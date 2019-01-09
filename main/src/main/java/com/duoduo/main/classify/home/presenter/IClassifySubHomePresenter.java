package com.duoduo.main.classify.home.presenter;

import com.duoduo.commonbusiness.mvp.presenter.IBasePresenter;

/**
 * 分类首页presenter接口
 */
public interface IClassifySubHomePresenter extends IBasePresenter {

    /**
     * 请求分类首页数据的方法
     */
    public void requestClassifySubHomeData();

    /**
     * 请求分类首页数据的方法
     */
    public void requestTopicData(int requestId, int topicId, int pageNum);

    /**
     * 处理下拉刷新
     */
    public void handlePullToRefresh();

    /**
     * 处理上来加载更多
     */
    public void handleToLoadMore();
}
