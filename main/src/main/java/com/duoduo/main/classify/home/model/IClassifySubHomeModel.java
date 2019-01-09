package com.duoduo.main.classify.home.model;

/**
 * 分类首页Model接口
 */
public interface IClassifySubHomeModel {
    /**
     * 请求分类首页数据的方法
     */
    public void requestClassifySubHomeData();

    /**
     * 请求分类首页数据的方法
     */
    public void requestTopicData(int requestId, int topicId, int pageNum);
}
