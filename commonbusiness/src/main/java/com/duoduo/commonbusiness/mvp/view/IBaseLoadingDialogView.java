package com.duoduo.commonbusiness.mvp.view;

/**
 * 带loading dialog的view接口
 */
public interface IBaseLoadingDialogView extends IBaseView{

    /**
     * 显示loading dialog
     */
    public void showLoadingDialog();

    /**
     * 隐藏loading dialog
     */
    public void hideLoadingDialog();
}
