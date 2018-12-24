package com.duoduo.commonbusiness.dialog;

import android.app.Activity;
import android.app.Dialog;

import java.lang.ref.WeakReference;

/**
 * 通用loading对话框helper
 */
public class CommonLoadingDialogHelper {

    /**
     * 对话框
     */
    protected Dialog loadingDialog;

    protected WeakReference<Activity> activityWeakReference;

    public CommonLoadingDialogHelper(Activity activity) {
        this.activityWeakReference = new WeakReference<Activity>(activity);
    }

    public boolean isLoadingDialogShow() {
        return loadingDialog != null && loadingDialog.isShowing();
    }

    public void showLoadingDialog() {
        Activity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = createDialog(activity);
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

    protected Dialog createDialog(Activity activity) {
        return new CommonLoadingDialog(activity);
    }

    public void destroy() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
