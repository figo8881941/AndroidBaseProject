package com.duoduo.commonbusiness.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 通用loading对话框helper
 */
public class CommonLoadingDialogHelper {

    private Context context;

    public CommonLoadingDialogHelper(Context context) {
        this.context = context;
    }

    /**
     * 对话框
     */
    protected Dialog loadingDialog;

    public boolean isLoadingDialogShow() {
        return loadingDialog != null && loadingDialog.isShowing();
    }

    public void showLoadingDialog() {
//        if (isDestroy || isFinishing()) {
//            return;
//        }
        if (loadingDialog == null) {
            loadingDialog = createDialog();
        }
        if (!isLoadingDialogShow()) {
            loadingDialog.show();
        }
    }

    public void hideLoadingDialog() {
        if (isLoadingDialogShow()) {
            loadingDialog.dismiss();
        }
    }

    protected Dialog createDialog() {
        return new CommonLoadingDialog(context);
    }

    public void destroy() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
