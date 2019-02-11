package com.duoduo.web.container;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.duoduo.commonbusiness.activity.BaseActivity;
import com.duoduo.commonbusiness.router.path.IWebPath;
import com.duoduo.web.R;

@Route(path = IWebPath.COMMON_WEBVIEW_ACTIVITY)
public class CommonWebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_container_common_webview_activity);
    }
}
