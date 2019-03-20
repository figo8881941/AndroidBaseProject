package com.duoduo.weex.container;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.duoduo.commonbase.utils.DeviceUtils;
import com.duoduo.commonbusiness.activity.BaseLoadingDialogActivity;
import com.duoduo.commonbusiness.router.path.IWeexPath;
import com.duoduo.weex.R;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Weex容器Activity
 */

@Route(path = IWeexPath.COMMON_WEEX_ACTIVITY)
public class CommonWeexActivity extends BaseLoadingDialogActivity implements IWXRenderListener {

    WXSDKInstance mWXSDKInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weex_container_common_weex_activity);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        String pageName = "WeexApp";
        String bundleUrl = "http://192.168.1.61:8081/dist/index.js";
        //String pageName = "WeexApp";
        //String bundleUrl = "file://assets/dist/index.js";
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, bundleUrl);
        Context context = getApplicationContext();
        mWXSDKInstance.renderByUrl(
                pageName,
                bundleUrl,
                options,
                null,
                DeviceUtils.getDisplayWidth(context),
                DeviceUtils.getDisplayHeight(context),
                WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
