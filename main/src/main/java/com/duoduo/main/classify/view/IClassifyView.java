package com.duoduo.main.classify.view;

import com.duoduo.commonbase.mvp.view.IBaseView;
import com.duoduo.main.classify.data.ClassifySubTabEntity;

/**
 * 分类View接口
 */
public interface IClassifyView extends IBaseView {

    /**
     * 数据初始化子分类Tab
     * @param classifySubTabEntity
     */
    public void updateSubTabByData(ClassifySubTabEntity classifySubTabEntity);
}
