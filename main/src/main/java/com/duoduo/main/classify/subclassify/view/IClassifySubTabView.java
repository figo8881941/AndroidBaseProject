package com.duoduo.main.classify.subclassify.view;

import com.duoduo.commonbusiness.mvp.view.IBaseLoadingDialogView;
import com.duoduo.main.base.data.TopicTwoProductListEntity;

import java.util.List;

/**
 * 分类子Tab View接口
 */
public interface IClassifySubTabView extends IBaseLoadingDialogView {

    /**
     * 使用商品数据更新List的方法
     * @param entities
     */
    public void updateListByProductData(List<TopicTwoProductListEntity> entities);
}
