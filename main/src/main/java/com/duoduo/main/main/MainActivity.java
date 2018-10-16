package com.duoduo.main.main;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.duoduo.commonbase.utils.ActivityUtils;
import com.duoduo.commonbusiness.activity.BaseActivity;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.commonbusiness.permission.DefaultCheckRequestListener;
import com.duoduo.commonbusiness.permission.PermissionUtils;
import com.duoduo.main.R;
import com.duoduo.main.main.controller.MainController;
import com.duoduo.main.main.data.MainTabDataBean;
import com.duoduo.main.main.event.MainTabRequestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 主activity
 */
public class MainActivity extends BaseActivity {

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_main_main_activity);

        //白色风格透明状态栏
        ActivityUtils.changeStatusBarTran(this, true);

        EventBus.getDefault().register(this);

        mainController = new MainController(getApplicationContext());

        // 检查必须的权限
        checkShouldGetPermission();
    }

    /**
     * 检查必须权限的方法
     */
    private void checkShouldGetPermission() {
        PermissionUtils.checkAndRequestPermission(getApplicationContext(), true, new DefaultCheckRequestListener() {
            @Override
            public void onGrantedPermission(String... permissions) {
                //授权成功，请求Tab数据
                if (mainController != null) {
                    mainController.requestTabData();
                }
            }

            @Override
            public void onDeniedPermission(String... permissions) {
                //授权失败，提示
                Toast.makeText(getApplicationContext(), R.string.main_main_no_permission_tips, Toast.LENGTH_LONG).show();
            }
        }, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * 处理请求tab数据返回
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMainTabRequest(MainTabRequestEvent event) {
        if (isDestroy || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case MainTabRequestEvent.EVENT_NAME_REQUEST_START : {

            }
            break;
            case MainTabRequestEvent.EVENT_NAME_REQUEST_FINISH : {
                MainTabDataBean mainTabDataBean = event.getArg3();

            }
            break;
            case MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR : {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getApplicationContext(), exception);
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
