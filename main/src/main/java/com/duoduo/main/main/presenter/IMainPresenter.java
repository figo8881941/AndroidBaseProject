package com.duoduo.main.main.presenter;

import com.duoduo.commonbusiness.mvp.IBasePresenter;

/**
 * 主界面Presenter接口
 */
public interface IMainPresenter extends IBasePresenter {

    /**
     * 请求Tab数据
     */
    public void requestTabData();

    /**
     * 检查需要的权限的方法
     */
    public void checkNeedPermissions();
}
