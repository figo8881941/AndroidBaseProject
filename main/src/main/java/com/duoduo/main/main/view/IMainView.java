package com.duoduo.main.main.view;

import com.duoduo.commonbusiness.mvp.IBaseView;
import com.duoduo.main.main.data.MainTabEntity;

/**
 * 主界面View接口
 */
public interface IMainView extends IBaseView {

    /**
     * 根据tab数据更新界面的方法
     *
     * @param mainTabEntity
     */
    public void updateViewByMainTabData(MainTabEntity mainTabEntity);
}
