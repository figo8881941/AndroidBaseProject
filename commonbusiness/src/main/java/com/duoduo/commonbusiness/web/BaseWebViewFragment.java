package com.duoduo.commonbusiness.web;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duoduo.commonbase.utils.DeviceUtils;
import com.duoduo.commonbusiness.R;
import com.duoduo.commonbusiness.fragment.BaseLoadingDialogFragment;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;
import com.duoduo.commonbusiness.view.CommonErrorView;
import com.duoduo.commonbusiness.view.CommonPageLoading;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.Iterator;

import wendu.dsbridge.DWebView;


public abstract class BaseWebViewFragment<D> extends BaseLoadingDialogFragment<D> implements IBaseWebViewContainer {

    protected final boolean DEBUG = true;
    protected final String TAG = this.getClass().getSimpleName();

    protected ViewGroup root;

    // 网页加载超时时间
    protected final long LOAD_TIME_OUT = 30 * 1000;

    // 显示h5的webview
    protected DWebView contentWebView;
    protected SmartRefreshLayout smartRefreshLayout;

    // js接口
    protected BaseWebInterface webAppInterface;

    // 加载出错界面
    protected CommonErrorView nodataView;

    // 加载loading界面
    protected CommonPageLoading pageLoading;

    // 处理超时的Runnable
    protected Runnable timeoutRunnable;

    protected Handler handler;

    protected boolean hasError = false;

    // 客户端是否在resume和pause时回调页面接口javascript:onResume()和javascript:onPause()。默认值是false，不回调。如果是true，需要回调。
    protected boolean callbackWhenResumAndPause = true;

    protected boolean timeout = false;

    // 页面是否加载成功
    protected boolean loadSuccess = false;

    // 要加载的地址
    protected String url;
    // 请求里面是否带上phead。默认值是true，需要带上phead。如果为false，就不带。
    protected boolean withHead = true;
    // 是否往加载页面注入CSS。默认false，不需要注入。
    protected boolean injectCss = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(
                R.layout.commonbusiness_web_basewebview_fragment_layout, container, false);
        handler = new Handler(Looper.getMainLooper());
        // 初始化数据的方法
        initData();
        // 初始化超时runable
        initTimeoutRunable();
        // 初始化界面
        initView();
        return root;
    }

    /**
     * 初始化数据的方法
     */
    protected void initData() {
        url = getUrlPath();
    }

    /**
     * 初始化界面的方法
     */
    protected void initView() {
        // 错误页面
        nodataView = (CommonErrorView) root.findViewById(R.id.no_data_view);
        nodataView.setRefrshBtClickListner(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 错误界面点击刷新
                loadUrl();
            }
        });
        // loading界面
        pageLoading = (CommonPageLoading) root.findViewById(R.id.page_loading);
        smartRefreshLayout = (SmartRefreshLayout) root.findViewById(R.id.smartRefreshLayout);
        //不可上拉加载更多
        smartRefreshLayout.setEnableLoadmore(false);
        //不能下拉刷新
        enablePullToRefresh(false);
        initWebView();
    }

    protected void initWebView() {
        contentWebView = (DWebView) root.findViewById(R.id.webview);
        contentWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        initWebViewInterface();
        WebViewUtils.setFullFunctionForWebView(
                getContext().getApplicationContext(), contentWebView, DEBUG);

        contentWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (DEBUG) {
                    Logger.t(TAG).i("onProgressChanged :" + newProgress);
                }
                if (newProgress >= 100) {
                    if (timeout) {
                        // 已经超时了，不用再处理了
                        timeout = false;
                        return;
                    }
                    if (!hasError) {
                        loadSuccess = true;
                        hideLoadingPage();
                        hideNoDataView();
                        showContentView();
                        showRefreshWebView();
                        if (injectCss) {
                            injectCss();
                        }
                    } else {
                        showNoDataView();
                        hideLoadingPage();
                        hideContentView();
                        hideRefreshWebView();
                        hasError = false;
                    }
                    if (handler != null && timeoutRunnable != null) {
                        handler.removeCallbacks(timeoutRunnable);
                    }
                } else {
                    if (!DeviceUtils.isNetworkOK(getActivity())) {
                        hasError = true;
                    }
                }
            }
        });

        contentWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (WebViewUtils.handleUrlIntent(getContext(), url)) {
                    return true;
                } else {
                    loadSuccess = false;
                    hasError = false;
                    view.loadUrl(url);
                    return true;
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                if (DEBUG) {
                    Logger.t(TAG).i("onReceivedError");
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (contentWebView != null) {
                    if (hasError) {
                        loadUrl();
                    } else {
                        WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_REFRESH);
                    }
                }
            }
        });
    }

    /**
     * 初始化native js接口的方法
     */
    protected void initWebViewInterface() {
        if (contentWebView == null) {
            return;
        }
        webAppInterface = new BaseWebInterface(getContext().getApplicationContext(), contentWebView, this);
        contentWebView.setJavascriptInterface(webAppInterface);
    }

    protected void initTimeoutRunable() {
        timeoutRunnable = new Runnable() {

            @Override
            public void run() {
                // 超时
                timeout = true;
                hasError = true;
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh();
                }
                hideContentView();
                hideLoadingPage();
                showNoDataView();
            }
        };
    }

    /**
     * 加载数据的方法
     */
    protected void loadUrl() {
        if (contentWebView != null && webAppInterface != null) {
            // 设置状态
            loadSuccess = false;
            hasError = false;
            showLoadingPage();
            onRefreshComplete();
            hideNoDataView();
            hideContentView();
            // 开始计时
            if (handler != null && timeoutRunnable != null) {
                handler.removeCallbacks(timeoutRunnable);
                handler.postDelayed(timeoutRunnable, LOAD_TIME_OUT);
            }

            if (withHead) {
                try {
                    JSONObject data = new JSONObject();
                    data.put(IWebConsts.Key.KEY_PHEAD, CommonNetDataUtils.getPheadJsonWithGlobalBuildConfig(getContext().getApplicationContext()));
                    JSONObject postData = getPostData();
                    if (postData != null) {
                        Iterator<String> keys = postData.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            data.put(key, postData.get(key));
                        }
                    }
                    WebViewUtils.postUrlData(contentWebView, url, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                contentWebView.loadUrl(url);
            }
        }
    }

    protected JSONObject getPostData() {
        return null;
    }

    /**
     * 往加载的页面注入css的方法
     */
    protected void injectCss() {
//        if (contentWebView != null) {
//            try {
//                contentWebView
//                        .loadUrl("javascript:{ var head = document.getElementsByTagName('head');  var link=document.createElement('link');   link.setAttribute('rel', 'stylesheet');  link.setAttribute('type', 'text/css');  link.setAttribute('href', 'http://xmiles.cn/js_css/xmiles_inject.css');   head[0].appendChild(link); }");
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
    }

    protected void showContentView() {
        if (contentWebView != null
                && contentWebView.getVisibility() != View.VISIBLE) {
            contentWebView.setVisibility(View.VISIBLE);
        }
    }

    protected void hideContentView() {
        if (contentWebView != null
                && contentWebView.getVisibility() != View.INVISIBLE) {
            contentWebView.setVisibility(View.INVISIBLE);
        }
    }

    protected void showRefreshWebView() {
        if (smartRefreshLayout != null
                && smartRefreshLayout.getVisibility() != View.VISIBLE) {
            smartRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    protected void hideRefreshWebView() {
        if (smartRefreshLayout != null
                && smartRefreshLayout.getVisibility() != View.INVISIBLE) {
            smartRefreshLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showLoadingPage() {
        if (pageLoading != null
                && pageLoading.getVisibility() != View.VISIBLE) {
            pageLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadingPage() {
        if (pageLoading != null
                && pageLoading.getVisibility() != View.GONE) {
            pageLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void enablePullToRefresh(boolean enable) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setEnableRefresh(enable);
        }
    }

    @Override
    public void enableReloadWhenLogin(boolean enable) {

    }

    @Override
    public void enableOnResumeOnPause(boolean enable) {
        callbackWhenResumAndPause = enable;
    }

    @Override
    public void enableOnBackPressed(boolean enable) {

    }

    @Override
    public void pullToRefresh() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onRefreshComplete() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void close() {
    }

    protected void showNoDataView() {
        if (nodataView != null
                && nodataView.getVisibility() != View.VISIBLE) {
            nodataView.setVisibility(View.VISIBLE);
        }
    }

    protected void hideNoDataView() {
        if (nodataView != null
                && nodataView.getVisibility() != View.GONE) {
            nodataView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setActionButtons(String data) {

    }

    @Override
    public void reload() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (callbackWhenResumAndPause) {
            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_RESUME);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (callbackWhenResumAndPause) {
            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_PAUSE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.clearAnimation();
            smartRefreshLayout = null;
        }
        if (contentWebView != null) {
            WebViewUtils.destroyWebView(contentWebView);
            contentWebView = null;
        }
        if (webAppInterface != null) {
            webAppInterface.destory();
            webAppInterface = null;
        }
        if (pageLoading != null) {
            pageLoading.clearAnimation();
            pageLoading = null;
        }
        if (nodataView != null) {
            nodataView.setRefrshBtClickListner(null);
            nodataView = null;
        }
        handler = null;
        timeoutRunnable = null;
    }

    /**
     * 获取加载Url的方法
     * @return
     */
    public abstract String getUrlPath();
}
