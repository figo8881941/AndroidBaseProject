package com.duoduo.main.main;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.duoduo.commonbusiness.permission.DefaultCheckRequestListener;
import com.duoduo.commonbusiness.permission.PermissionUtils;
import com.duoduo.main.R;
import com.duoduo.main.main.controller.MainController;

/**
 * 主activity
 */
public class MainActivity extends AppCompatActivity {

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(), R.string.main_no_permission_tips, Toast.LENGTH_LONG).show();
            }
        }, Manifest.permission.READ_PHONE_STATE);
    }
}
