package com.duoduo.main.classify.presenter;

import com.duoduo.commonbase.mvp.presenter.IBasePresenter;

/**
 * 分类Presenter接口
 */
public interface IClassifyPresenter extends IBasePresenter {

    /**
     * 请求子分类tab的方法
     */
    public void requestClassifySubTabData();
}
