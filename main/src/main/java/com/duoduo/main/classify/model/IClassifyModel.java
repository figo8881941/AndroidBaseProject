package com.duoduo.main.classify.model;

import com.duoduo.commonbusiness.mvp.model.IBaseModel;

/**
 * 分类model接口
 */
public interface IClassifyModel extends IBaseModel {
    /**
     * 请求分类Tab数据的方法
     */
    public void requestClassifySubTabData();
}
