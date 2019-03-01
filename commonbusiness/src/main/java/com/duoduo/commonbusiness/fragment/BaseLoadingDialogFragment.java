package com.duoduo.commonbusiness.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.duoduo.commonbusiness.dialog.CommonLoadingDialogHelper;
import com.duoduo.commonbase.mvp.view.IBaseLoadingDialogView;

/**
 * 带loading dialog的Fragment的基类
 * @param <K>
 */
public class BaseLoadingDialogFragment<K> extends BaseFragment<K> implements IBaseLoadingDialogView {

    //Commonloading
    protected CommonLoadingDialogHelper loadingDialogHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialogHelper = new CommonLoadingDialogHelper(getActivity());
    }

    @Override
    public void onDestroy() {
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
