package com.duoduo.androidbaseproject.router;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.duoduo.commonbusiness.activity.BaseActivity;

/**
 * 外部路由Activity
 */
public class SchemeFilterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对外部URL直接进行中转
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
