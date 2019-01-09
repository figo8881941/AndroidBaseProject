package com.duoduo.commonbusiness.mvp.presenter;

import android.content.Context;

import com.duoduo.commonbusiness.mvp.model.IBaseModel;
import com.duoduo.commonbusiness.mvp.view.IBaseView;

/**
 * Presenter基类
 */
public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IBasePresenter{

    protected Context context;

    protected V view;

    protected M model;

    public BasePresenter(Context context, V view) {
        this.context = context.getApplicationContext();
        this.view = view;
        this.model = createModel();
    }

    /**
     * 创建model的方法
     * @return
     */
    public abstract M createModel();

    @Override
    public void destroy() {
        context = null;
        view = null;
        if (model != null) {
            model.destroy();
            model = null;
        }
    }
}
