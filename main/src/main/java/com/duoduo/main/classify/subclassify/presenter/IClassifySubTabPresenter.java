package com.duoduo.main.classify.subclassify.presenter;

import com.duoduo.commonbusiness.mvp.presenter.IBasePresenter;

/**
 * 分类子Tab presenter接口
 */
public interface IClassifySubTabPresenter extends IBasePresenter {

    /**
     * 请求首页数据
     */
    public void requestFirstData();

    /**
     * 处理view被选中
     */
    public void handleSelected();
}
