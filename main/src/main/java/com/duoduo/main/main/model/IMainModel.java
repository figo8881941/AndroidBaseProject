package com.duoduo.main.main.model;

import com.duoduo.commonbase.mvp.model.IBaseModel;

/**
 * 主界面model接口
 */
public interface IMainModel extends IBaseModel {

    /**
     * 请求首页Tab数据的方法
     */
    public void requestTabData();
}
