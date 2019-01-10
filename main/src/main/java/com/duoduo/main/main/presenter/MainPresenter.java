package com.duoduo.main.main.presenter;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.duoduo.commonbase.permission.DefaultCheckRequestListener;
import com.duoduo.commonbase.permission.PermissionUtils;
import com.duoduo.commonbase.permission.annotation.NeedPermission;
import com.duoduo.commonbusiness.mvp.presenter.BasePresenter;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.main.R;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.event.MainTabRequestEvent;
import com.duoduo.main.main.model.IMainModel;
import com.duoduo.main.main.model.MainModel;
import com.duoduo.main.main.view.IMainView;

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

    @Override
    @NeedPermission(permissions = {Manifest.permission.READ_PHONE_STATE}, ignoreShowRationale = true)
    public void requestTabData() {
        if (model != null) {
            model.requestTabData();
        }
    }

    /**
     * 检查需要的权限的方法
     */
    public void checkNeedPermissions() {
        PermissionUtils.checkAndRequestPermission(context, true, new DefaultCheckRequestListener() {
            @Override
            public void onGrantedPermission(String... permissions) {
                //授权成功，请求Tab数据
                if (view != null && !view.isDestroy()) {
                    requestTabData();
                }
            }

            @Override
            public void onDeniedPermission(String... permissions) {
                //授权失败，提示
                Toast.makeText(context, R.string.main_main_no_permission_tips, Toast.LENGTH_LONG).show();
            }
        }, Manifest.permission.READ_PHONE_STATE);
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
