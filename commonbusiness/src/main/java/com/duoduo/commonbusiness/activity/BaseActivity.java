package com.duoduo.commonbusiness.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.duoduo.commonbusiness.mvp.view.IBaseView;

/**
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    /**
     * 是否被销毁的标志
     */
    protected boolean isDestroy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }
}
