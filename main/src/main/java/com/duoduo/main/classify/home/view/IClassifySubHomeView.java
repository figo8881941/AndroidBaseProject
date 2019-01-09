package com.duoduo.main.classify.home.view;

import com.duoduo.commonbusiness.mvp.view.IBaseLoadingDialogView;
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;

import java.util.List;

/**
 * 分类首页view接口
 */
public interface IClassifySubHomeView extends IBaseLoadingDialogView {

    /**
     * 如果有banner，就开启banner自动播放
     */
    public void startBannerAutoPlay();

    /**
     * 如果有banner，就停止banner自动播放
     */
    public void stopBannerAutoPlay();

    /**
     * 停止下拉刷新
     */
    public void finishRefresh();

    /**
     * 停止上拉加载更多
     */
    public void finishLoadMore();

    /**
     * 停止上拉加载更多，并显示没有数据
     */
    public void finishLoadMoreWithNoMoreData();

    /**
     * 设置是否开启下拉刷新
     * @param enable
     */
    public void setEnableRefresh(boolean enable);

    /**
     * 设置是否开启上拉加载更多
     * @param enable
     */
    public void setEnableLoadMore(boolean enable);

    /**
     * 使用首页数据进行View初始化
     * @param homeEntity
     */
    public void updateViewBySubHomeData(ClassifySubHomeEntity homeEntity);

    /**
     * 使用主题数据更新列表
     * @param topicData
     */
    public void updateListByTopicData(List<TopicTwoProductListEntity> topicData);
}
