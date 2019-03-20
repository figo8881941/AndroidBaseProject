package com.duoduo.weex.container;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.duoduo.commonbase.utils.ActivityUtils;
import com.duoduo.commonbase.utils.DeviceUtils;
import com.duoduo.commonbase.utils.StatusBarUtils;
import com.duoduo.commonbase.utils.ViewUtils;
import com.duoduo.commonbusiness.activity.BaseLoadingDialogActivity;
import com.duoduo.commonbusiness.router.path.IWeexPath;
import com.duoduo.commonbusiness.view.CommonActionBar;
import com.duoduo.commonbusiness.view.CommonErrorView;
import com.duoduo.commonbusiness.view.CommonPageLoading;
import com.duoduo.weex.R;
import com.orhanobut.logger.Logger;
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

    private String TAG = getClass().getSimpleName();
    private boolean DEBUG = true;

    private WXSDKInstance wxSDKInstance;

    // 状态栏占位
    private View statusBarSpaceView;

    // 标题
    private CommonActionBar actionBar;

    // 内容容器
    private ViewGroup contentContainer;

    // 加载出错界面
    private CommonErrorView noDataView;

    // 加载loading界面
    private CommonPageLoading pageLoading;

    // Weex页面显示宽高
    private int displayWidth;
    private int displayHeight;

    // 网页加载超时时间
    private final long LOAD_TIME_OUT = 30 * 1000;

    // 处理超时的Runnable
    private Runnable timeoutRunnable;

    // 页面是否加载超时
    private boolean timeout = false;

    private Handler handler;

    // 标题
    @Autowired
    protected String title;

    // 页面名称
    @Autowired
    protected String pageName;

    // bundle js url
    @Autowired
    protected String bundleUrl;

    // 是否全屏
    @Autowired
    protected boolean isFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果需要全屏展示
        if (isFullScreen) {
            ActivityUtils.changeActivityToFullScreen(this);
        }

        // 设置状态栏颜色
        StatusBarUtils.changeStatusBarTran(this, false);

        setContentView(R.layout.weex_container_common_weex_activity);
        initSDKInstance();
        initView();
        controlActionBarLayout();
        calculateDiplayWidthHeight(getApplicationContext());
        handler = new Handler(Looper.getMainLooper());
        initTimeoutRunable();
        render();
        hideContentContainer();
        showLoadingPage();
    }

    private void initSDKInstance() {
        wxSDKInstance = new WXSDKInstance(this);
        wxSDKInstance.registerRenderListener(this);
    }

    private void initView() {
        statusBarSpaceView = findViewById(R.id.status_bar_spaceview);
        statusBarSpaceView.getLayoutParams().height = StatusBarUtils.getStatusBarHeightFit(getApplicationContext());

        actionBar = (CommonActionBar) findViewById(R.id.actionbar);
        actionBar.setTitle(title);
        actionBar.setBackButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        contentContainer = findViewById(R.id.content_container);
        // loading界面
        pageLoading = (CommonPageLoading) findViewById(R.id.page_loading);
        // 错误页面
        noDataView = (CommonErrorView) findViewById(R.id.no_data_view);
        noDataView.setRefrshBtClickListner(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //错误界面点击刷新
                render();
                hideContentContainer();
                showLoadingPage();
                hideNoDataView();
            }
        });
    }

    /**
     * 控制ActionBar的展示
     */
    private void controlActionBarLayout() {
        if (isFullScreen) {
            hideStatusBarSpace();
            hideActionBar();
        } else {
            showStatusBarSpace();
            showActionBar();
        }
    }

    private void calculateDiplayWidthHeight(Context context) {
        displayWidth = DeviceUtils.getDisplayWidth(context);
        displayHeight = DeviceUtils.getDisplayHeight(context);
        if (!isFullScreen) {
            int actionBarHeght = getResources().getDimensionPixelSize(R.dimen.commonbusiness_common_actionbar_height);
            displayHeight = displayHeight - actionBarHeght;
        }
    }

    private void render() {
        if (isDestroy) {
            return;
        }
        //String pageName = "WeexApp";
        //String bundleUrl = "http://192.168.14.200:8081/dist/index.js";
        //String pageName = "WeexApp";
        //String bundleUrl = "file://assets/dist/index.js";
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, bundleUrl);
        Context context = getApplicationContext();
        wxSDKInstance.renderByUrl(
                pageName,
                bundleUrl,
                options,
                null,
                displayWidth,
                displayHeight,
                WXRenderStrategy.APPEND_ASYNC);
        timeout = false;
        handler.removeCallbacks(timeoutRunnable);
        handler.postDelayed(timeoutRunnable, LOAD_TIME_OUT);
    }

    private void initTimeoutRunable() {
        timeoutRunnable = new Runnable() {

            @Override
            public void run() {
                if (DEBUG) {
                    Logger.t(TAG).i("timeoutRunnable 超时");
                }
                // 超时
                timeout = true;
                hideContentContainer();
                hideLoadingPage();
                showNoDataView();
            }
        };
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (DEBUG) {
            Logger.t(TAG).i("onViewCreated");
        }
        if (isDestroy) {
            return;
        }
        if (timeout) {
            return;
        }
        handler.removeCallbacks(timeoutRunnable);
        contentContainer.addView(view,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        if (DEBUG) {
            Logger.t(TAG).i("onRenderSuccess");
        }
        if (isDestroy) {
            return;
        }
        showContentContainer();
        hideLoadingPage();
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        if (DEBUG) {
            Logger.t(TAG).i("onRefreshSuccess");
        }
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        if (DEBUG) {
            Logger.t(TAG).i("onException");
        }
        if (isDestroy) {
            return;
        }
        hideLoadingPage();
        showNoDataView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wxSDKInstance != null) {
            wxSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wxSDKInstance != null) {
            wxSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wxSDKInstance != null) {
            wxSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wxSDKInstance != null) {
            wxSDKInstance.onActivityDestroy();
        }
        if (pageLoading != null) {
            pageLoading.clearAnimation();
            pageLoading = null;
        }
        if (noDataView != null) {
            noDataView.setRefrshBtClickListner(null);
            noDataView = null;
        }
        if (handler != null) {
            handler.removeCallbacks(timeoutRunnable);
            handler = null;
        }
        timeoutRunnable = null;
    }

    public void showLoadingPage() {
        ViewUtils.showView(pageLoading);
    }

    public void hideLoadingPage() {
        ViewUtils.goneView(pageLoading);
    }

    public void showContentContainer() {
        ViewUtils.showView(contentContainer);
    }

    public void hideContentContainer() {
        ViewUtils.hideView(contentContainer);
    }

    private void showNoDataView() {
        ViewUtils.showView(noDataView);
    }

    private void hideNoDataView() {
        ViewUtils.goneView(noDataView);
    }

    private void hideStatusBarSpace() {
        ViewUtils.goneView(statusBarSpaceView);
    }

    private void showStatusBarSpace() {
        ViewUtils.showView(statusBarSpaceView);
    }

    private void showActionBar() {
        ViewUtils.showView(actionBar);
    }

    private void hideActionBar() {
        ViewUtils.goneView(actionBar);
    }
}
