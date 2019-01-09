package com.duoduo.commonbusiness.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.duoduo.commonbusiness.dialog.CommonLoadingDialogHelper;
import com.duoduo.commonbusiness.mvp.view.IBaseLoadingDialogView;

/**
 * 带loading dialog的activity的基类
 */
public abstract class BaseLoadingDialogActivity extends BaseActivity implements IBaseLoadingDialogView {

    //Commonloading
    protected CommonLoadingDialogHelper loadingDialogHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialogHelper = new CommonLoadingDialogHelper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialogHelper != null) {
            loadingDialogHelper.destroy();
            loadingDialogHelper = null;
        }
    }

    @Override
    public void showLoadingDialog() {
        if (loadingDialogHelper != null) {
            loadingDialogHelper.showLoadingDialog();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (loadingDialogHelper != null) {
            loadingDialogHelper.hideLoadingDialog();
        }
    }
}
