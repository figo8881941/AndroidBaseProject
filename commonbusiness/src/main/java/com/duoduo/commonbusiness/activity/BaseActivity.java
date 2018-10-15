package com.duoduo.commonbusiness.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * activity基类
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 是否被销毁的标志
     */
    protected boolean isDestroy;

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }
}
