package com.duoduo.main.classify.subclassify.model;

/**
 * 分类子Tab model接口
 */
public interface IClassifySubTabModel {

    /**
     * 请求子分类商品数据的方法
     *
     * @param categoryId
     * @param pageNume
     */
    public void requestSubTabProductData(int categoryId, int pageNume);

    /**
     * 请求子分类排行榜数据的方法
     *
     * @param topicId
     */
    public void requestSubTabTopicData(int topicId);
}
