package com.duoduo.main.main.presenter;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.duoduo.commonbase.permission.annotation.DeniedPermission;
import com.duoduo.commonbase.permission.annotation.NeedPermission;
import com.duoduo.commonbase.permission.annotation.ShowRationable;
import com.duoduo.commonbase.permission.entity.DeniedPermissionEntity;
import com.duoduo.commonbase.permission.entity.ShowRationaleEntity;
import com.duoduo.commonbusiness.mvp.presenter.BasePresenter;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.main.R;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.event.MainTabRequestEvent;
import com.duoduo.main.main.model.IMainModel;
import com.duoduo.main.main.model.MainModel;
import com.duoduo.main.main.view.IMainView;
import com.yanzhenjie.permission.RequestExecutor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 主界面Presenter
 */
public class MainPresenter extends BasePresenter<IMainView, IMainModel> implements IMainPresenter {

    public MainPresenter(Context context, IMainView view) {
        super(context, view);
        EventBus.getDefault().register(this);
    }

    @Override
    public IMainModel createModel() {
        return new MainModel(context);
    }

    /**
     * 请求Tab数据，需要权限检查
     */
    @Override
    @NeedPermission(permissions = {Manifest.permission.READ_PHONE_STATE}
    , ignoreShowRationale = true, requestCode = 1000, once = true, continueWhenDenied = true)
    public void requestTabData() {
        if (model != null) {
            model.requestTabData();
        }
    }

    /**
     * 处理权限申请被拒绝的方法
     *
     * @param entity
     */
    @DeniedPermission
    private void handleDeniedPermission(DeniedPermissionEntity entity) {
        Toast.makeText(context, R.string.main_main_no_permission_tips, Toast.LENGTH_LONG).show();
    }

    /**
     * 处理需要展示权限说明对话框的方法
     * @param entity
     */
    @ShowRationable
    private void handleShowRationable(ShowRationaleEntity entity) {
        RequestExecutor requestExecutor = entity.getExecutor();
        requestExecutor.execute();
    }

    /**
     * 处理请求tab数据返回
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMainTabRequest(MainTabRequestEvent event) {
        if (view == null || view.isDestroy() || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case MainTabRequestEvent.EVENT_NAME_REQUEST_START: {

            }
            break;
            case MainTabRequestEvent.EVENT_NAME_REQUEST_SUCCESS: {
                //tab数据返回，更新界面
                MainTabEntity mainTabEntity = event.getArg3();
                view.updateViewByMainTabData(mainTabEntity);
            }
            break;
            case MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(context, exception);
            }
            break;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EventBus.getDefault().unregister(this);
    }

}
