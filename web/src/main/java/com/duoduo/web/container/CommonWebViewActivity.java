//package com.duoduo.web.container;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.net.http.SslError;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.ViewStub;
//import android.view.Window;
//import android.view.WindowManager;
//import android.webkit.DownloadListener;
//import android.webkit.SslErrorHandler;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.alibaba.android.arouter.facade.annotation.Autowired;
//import com.alibaba.android.arouter.facade.annotation.Route;
//import com.duoduo.commonbase.utils.DeviceUtils;
//import com.duoduo.commonbusiness.activity.BaseLoadingDialogActivity;
//import com.duoduo.commonbusiness.channel.ChannelUtils;
//import com.duoduo.commonbusiness.config.GlobalBuildConfig;
//import com.duoduo.commonbusiness.router.path.IWebPath;
//import com.duoduo.commonbusiness.view.CommonErrorView;
//import com.duoduo.commonbusiness.view.CommonPageLoading;
//import com.duoduo.commonbusiness.web.BaseWebInterface;
//import com.duoduo.commonbusiness.web.IBaseWebViewContainer;
//import com.duoduo.commonbusiness.web.IWebConsts;
//import com.duoduo.commonbusiness.web.WebChromeClientExt;
//import com.duoduo.commonbusiness.web.WebViewUtils;
//import com.duoduo.web.R;
//import com.orhanobut.logger.Logger;
//
//import org.greenrobot.eventbus.EventBus;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Iterator;
//
//import wendu.dsbridge.DWebView;
//
///**
// * Webview加载内容的activity，里面包含加载页面和超时错误处理
// *
// * @author wangzhuobin
// */
//@Route(path = IWebPath.COMMON_WEBVIEW_ACTIVITY)
//public class CommonWebViewActivity extends BaseLoadingDialogActivity
//        implements IBaseWebViewContainer, WebChromeClientExt.OpenFileChooserCallBack {
//
//    private final int HANDLER_MAG_SHOW_ORDER_TIP = 1;
//
//    private final boolean DEBUG = GlobalBuildConfig.getInstance().isDebugMode();
//    private final String TAG = this.getClass().getSimpleName();
//
//    // 网页加载超时时间
//    private final long LOAD_TIME_OUT = 30 * 1000;
//
//    // 标题
//    private CommonActionBar actionBar;
//
//    // 加载外部页面使用的标题栏
//    private View outterWebTitle;
//    private ImageView outterWebBackBt;
//    private ImageView outterWebCloseBt;
//    private TextView outterWebTextView;
//    private View.OnClickListener backButtonOnClickListener;
//    private View.OnClickListener closeButtonOnClickListener;
//
//
//    private CommonPullToRefreshWebView pullToRefreshWebView;
//    // 显示h5的webview
//    private DWebView contentWebView;
//
//    // js接口
//    private BaseWebInterface webAppInterface;
//
//    private HashMap<String, String> datas = new HashMap<String, String>();
//
//    // 加载出错界面
//    private CommonErrorView noDataView;
//
//    // 加载loading界面
//    private CommonPageLoading pageLoading;
//
//    // 处理超时的Runnable
//    private Runnable timeoutRunnable;
//
//    private Handler handler;
//
//    private boolean hasError = false;
//
//    // 页面是否加载成功
//    private boolean loadSuccess = false;
//
//    private boolean timeout = false;
//
//    private boolean mIsOrderSuccess = false;
//    private boolean mIsTaobaoMonitor = false;
//
//    // 标题
//    @Autowired
//    protected String title;
//    // 要加载的地址
//    @Autowired(name = "htmlUrl")
//    protected String url;
//    // 请求里面是否带上phead。值是true，需要带上phead。如果为false，就不带。
//    @Autowired
//    protected boolean withHead = true;
//    // 是否使用post的方式加载页面。
//    @Autowired
//    protected boolean usePost;
//    // 是否显示toolbar。值是false，不需要显示。如果为true，就显示。
//    @Autowired
//    protected boolean showToolbar;
//    // 按返回键后的跳转参数
//    @Autowired
//    protected String backLaunchParams;
//    // html页面是否接管返回键的处理。如果接管，按返回键时，将调用js的方法onBackPressed
//    @Autowired
//    protected boolean takeOverBackPressed;
//    // 客户端是否在resume和pause时回调页面接口javascript:onResume()和javascript:onPause()。值是false，不回调。如果是true，需要回调。
//    @Autowired
//    protected boolean callbackWhenResumAndPause;
//    //登录后是否自动刷新页面
//    @Autowired
//    protected boolean whenLoginReloadPage;
//    //是否加上理财需要的参数
//    @Autowired
//    protected boolean isNeedParams;
//    //是否全屏
//    @Autowired
//    protected boolean isFullScreen;
//    // 客户端是否显示标题。客户端需要显示标题，如果为false，则客户端不显示标题，有页面来处理。
//    @Autowired
//    protected boolean showTitle;
//    // post携带的数据，json格式的字符串
//    @Autowired
//    protected String postData;
//    // 是否控制网页返回
//    @Autowired
//    protected boolean controlPageBack;
//    @Autowired
//    protected String shareAction;
//    @Autowired
//    protected boolean reloadWhenLogin;
//    @Autowired
//    protected boolean reloadWhenLogout;
//    //是否是浏览资讯的任务类别
//    @Autowired
//    protected boolean isBrowseNewsTaskType;
//    //推送浏览任务的限定浏览时长
//    @Autowired
//    protected int pushTaskLimitTime;
//    //推送浏览任务的所得趣币
//    @Autowired
//    protected double pushTaskPoint;
//    //推送浏览任务的金币对应的id  用于防止刷接口
//    @Autowired
//    protected int coinId;
//    //推送浏览任务的pushId
//    @Autowired
//    protected int pushId;
//    // 动态注入的js
//    @Autowired
//    protected String injectJS;
//    @Autowired
//    protected String pathId; // 路径id
//
//    private ActionBarButtonController mActionBarMenuController;
//    private ActionBarMoreWindow mMoreWindow;
//    private IconImageView menuImage;
//    private ProgressBar mProgressBar;
//
//    //处理软键盘
//    private AndroidBug5497Workaround mAndroidBug5497Workaround;
//
//    private IAccountService mAccountService;
//
//    private CommonCoverLayerDialog mCoverLayerDialog;
//    private CommonIconView mCommonIconView;
//    private ViewStub mCommonIconViewStub;
//
//    private ViewStub mAnswerGdtViewStub;
//
//    //常规任务 爆料文章浏览积分任务
//    private long mLastResumeTime = -1;
//
//    //页面是否第一次加载
//    private boolean isFirstLoad = true;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (isFullScreen) {
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
//        StatusBarUtil.setTranslate(this, false);
//        setContentView(R.layout.web_activity_common_webview);
//        EventBus.getDefault().register(this);
//        mAndroidBug5497Workaround = new AndroidBug5497Workaround(this);
//        handler = new Handler(Looper.getMainLooper());
//
//        mAccountService = (IAccountService) ARouter.getInstance().build(IGlobalRouteProviderConsts.ACCOUNT_SERVICE).navigation();
//
//        // 初始化超时runable
//        initTimeoutRunable();
//        initProgressRunnable();
//        // 初始化界面
//        initView();
////        initLoginStatusChange();
//        // 加载页面
//        loadUrl();
//    }
//
//    /**
//     * 初始化界面的方法
//     */
//    @SuppressLint("JavascriptInterface")
//    private void initView() {
//        initFadeStatus();
//
//        mCommonIconViewStub = (ViewStub) findViewById(R.id.commonIconViewStub);
//        mAnswerGdtViewStub = (ViewStub) findViewById(R.id.answerGdtViewStub);
//        mCoverLayerDialog = new CommonCoverLayerDialog(this);
//
//        actionBar = (CommonActionBar) findViewById(R.id.actionbar);
//        initMorePopWindow();
//        actionBar.setTitle(title);
//        actionBar.setBackButtonOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (takeOverBackPressed && contentWebView != null && loadSuccess
//                        && !hasError) {
//                    // 返回键如果被接管，就回调js方法
//                    WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_BACKPRESSED);
//                    return;
//                }
//                finish();
//            }
//        });
//
//        // 初始化按钮监听器
//        initButtonOnClickListener();
//        outterWebTitle = findViewById(R.id.webview_guide_bar);
//        outterWebTextView = (TextView) findViewById(R.id.outter_webview_title);
//        outterWebTextView.setText(title);
//        outterWebBackBt = (ImageView) findViewById(R.id.outter_webview_back_bt);
//        outterWebBackBt.setOnClickListener(backButtonOnClickListener);
//        outterWebCloseBt = (ImageView) findViewById(R.id.outter_webview_close_bt);
//        outterWebCloseBt.setOnClickListener(closeButtonOnClickListener);
//        menuImage = (IconImageView) findViewById(R.id.menu_img);
//        menuImage.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMoreWindow.showAsDropDown(menuImage, -mMoreWindow.getWidth() + (int) (menuImage.getWidth() * 0.9f), DrawUtils.dip2px(8));
//            }
//        });
//
//        if (!TextUtils.isEmpty(title)) {
//            showTitle = true;
//        }
//
//        if (isFullScreen) {
//            hideTitle();
//            hideToolbar();
//            findViewById(R.id.common_webview_fade_status).setVisibility(View.GONE);
//        } else {
//            if (showTitle && !showToolbar) {
//                showTitle();
//            } else {
//                hideTitle();
//            }
//
//            if (showToolbar) {
//                showToolbar();
//            } else {
//                hideToolbar();
//            }
//        }
//
//        // 错误页面
//        noDataView = (CommonErrorView) findViewById(R.id.no_data_view);
//        noDataView.setRefrshBtClickListner(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // 错误界面点击刷新
//                loadUrl();
//            }
//        });
//        // loading界面
//        pageLoading = (CommonPageLoading) findViewById(R.id.page_loading);
//
//        pullToRefreshWebView = (CommonPullToRefreshWebView) findViewById(R.id.webView);
//        // 默认不能下拉刷新，除非页面开启
//        enablePullToRefresh(false);
//        pullToRefreshWebView.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                reFreshData();
//            }
//        });
//        contentWebView = (DWebView) pullToRefreshWebView.getRefreshableView();
//        contentWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        // 初始化native js接口
//        initWebViewInterface();
//        WebViewUtils.setFullFunctionForWebView(getApplicationContext(), contentWebView, DEBUG);
//        WebChromeClient webChromeClient = new WebChromeClientExt(this) {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (DEBUG) {
//                    Logger.t(TAG).i("onProgressChanged :" + newProgress);
//                }
//
//                //// TODO: 2017/7/21 先屏蔽，下个版本上
//                refreshProgess(newProgress);
//
//                if (newProgress >= 100) {
//                    if (timeout) {
//                        // 已经超时了，不用再处理了
//                        timeout = false;
//                        return;
//                    }
//
//                    //页面加载完成后 再执行这些操作
//                    if (isFirstLoad) {
//                        //判断是否福利详情页  如果是 则做福利详情页的任务
//                        if (!TextUtils.isEmpty(url) && url.contains("/qn/common?funid=30501&service=quMall&welfareId=")) {
//                            mLastResumeTime = System.currentTimeMillis();
//                            //推送浏览任务
//                            if (pushTaskLimitTime != 0 && pushTaskPoint != 0 && coinId != 0) {
//                                String id = url.substring(url.lastIndexOf("welfareId=") + 10, url.length());
//                                RoutineTasksManager.getInstance(CommonWebViewActivity.this).startPushReadTask(pushTaskLimitTime, coinId, 5, pushId, id);
//                            }
//                        } else {
//                            //推送浏览任务
//                            if (pushTaskLimitTime != 0 && pushTaskPoint != 0 && coinId != 0) {
//                                RoutineTasksManager.getInstance(CommonWebViewActivity.this).startPushReadTask(pushTaskLimitTime, coinId, 3, pushId, url);
//                            }
//                        }
//
//                        isFirstLoad = !isFirstLoad;
//                    }
//
//                    if (!hasError) {
//                        loadSuccess = true;
//                        hideLoadingPage();
//                        hideNoDataView();
//
//                        if (isFullScreen) {
//                            hideTitle();
//                            hideToolbar();
//                            findViewById(R.id.common_webview_fade_status).setVisibility(View.GONE);
//                        } else {
//                            if (showTitle && !showToolbar) {
//                                showTitle();
//                            } else {
//                                hideTitle();
//                            }
//
//                            if (showToolbar) {
//                                showToolbar();
//                            } else {
//                                hideToolbar();
//                            }
//                        }
//
//                        showContentView();
//                        checkShowCloseBt();
//                    } else {
//                        showNoDataView();
//                        hideLoadingPage();
//                        hideLoadingDialog();
//                        hideContentView();
//                        hasError = false;
//                    }
//                    if (handler != null && timeoutRunnable != null) {
//                        handler.removeCallbacks(timeoutRunnable);
//                    }
//                } else {
//                    if (!DeviceUtils.isNetworkOK(getApplicationContext())) {
//                        hasError = true;
//                    }
//                }
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String currentTitle) {
//                super.onReceivedTitle(view, currentTitle);
//                if (!TextUtils.isEmpty(title)) {
//                    return;
//                }
//
//                if (!WebViewUtils.isInnerUrl(view.getUrl())) {
//                    outterWebTextView.setText(currentTitle != null ? currentTitle : "");
//                    actionBar.setTitle(currentTitle != null ? currentTitle : "");
//                    title = currentTitle;
//                } else {
//                    if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(currentTitle)) {
////                        showTitle = true;
//                        actionBar.setTitle(currentTitle);
//                        outterWebTextView.setText(currentTitle);
//                        title = currentTitle;
//                    } else {
//                        outterWebTextView.setText(title);
//                        actionBar.setTitle(title);
//                    }
//                }
//            }
//        };
//        contentWebView.setWebChromeClient(webChromeClient);
//        contentWebView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                CptLogUtils.cptLog("加载的url=" + url);
//                if (WebViewUtils.handleUrlIntent(
//                        CommonWebViewActivity.this, url)) {
//                    return true;
//                } else {
//                    loadSuccess = false;
//                    hasError = false;
//                    if (isNeedParams) {
//                        url = handleUrl(url);
//                    }
//                    view.loadUrl(url);
//                    return true;
//                }
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode,
//                                        String description, String failingUrl) {
//                super.onReceivedError(view, errorCode, description, failingUrl);
//                if (DEBUG) {
//                    Logger.t(TAG).i("onReceivedError=");
//                }
//                hasError = true;
//            }
//
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
////                super.onReceivedSslError(view, handler, error);
//                //handler.cancel(); // Android默认的处理方式
//                handler.proceed();  // 接受所有网站的证书
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                if (!TextUtils.isEmpty(injectJS)) {
//                    String js = "window.phead=" + NetDataUtils.getPheadJson(CommonWebViewActivity.this).toString().replace("\"", "'") + ";";
//                    js += "var newscript = document.createElement(\"script\");";
//                    js += ("newscript.src=\"" + injectJS + "\";");
//                    js += ("newscript.id=\"xmiles\";");
//                    js += "document.getElementsByTagName('head')[0].appendChild(newscript);";
//                    view.loadUrl("javascript:" + js);
//                }
//            }
//        });
//
//
//        //下载处理
//        contentWebView.setDownloadListener(new DownloadListener() {
//
//            @Override
//            public void onDownloadStart(final String url, String userAgent, final String contentDisposition,
//                                        String mimetype, long contentLength) {
//
//                final CommonConfirmDialog commonConfirmDialog = new CommonConfirmDialog(CommonWebViewActivity.this);
//
//                String appName = "";
//                if (url != null) {
//                    String[] split = url.split("/");
//                    for (String str : split) {
//                        if (!TextUtils.isEmpty(str) && str.contains(".apk")) {
//                            int indexOf = str.indexOf(".apk");
//                            appName = str.substring(0, indexOf + ".apk".length());
//                            break;
//                        }
//                    }
//                }
//
//                if (TextUtils.isEmpty(appName) && !TextUtils.isEmpty(contentDisposition)) {
//                    int index = contentDisposition.indexOf("filename=");
//                    if (index >= 0) {
//                        appName = contentDisposition.substring(index + 9);
//                    }
//                }
//
//                try {
//                    appName = URLDecoder.decode(appName, "UTF-8");
//                } catch (Exception e) {
//                }
//
//                String host = "";
//                if (url != null) {
//                    Uri parse = Uri.parse(url);
//                    host = parse.getHost();
//                }
//
//                commonConfirmDialog.setTitleText("提示");
//                commonConfirmDialog.setSubTitle(String.format("该网页(%1$s)请求下载%2$sApk安装包，点击确认继续", host, !TextUtils.isEmpty(appName) ? "(" + appName + ")" : ""));
//                commonConfirmDialog.setBtnAText("取消");
//                commonConfirmDialog.setBtnBText("确认");
//                final String finalAppName = appName;
//                commonConfirmDialog.setBtnClickListener(new CommonConfirmDialog.OnBtnClickListener() {
//                    @Override
//                    public void onBtnBClick() {
//                        // 浏览器兼容，防止某些设备错误调到下载接口抛异常
//                        try {
//                            webAppInterface.downloadFile(finalAppName, url);
//                        } catch (Exception ignored) {
//                        }
//
//                        commonConfirmDialog.dismiss();
//                    }
//
//                    @Override
//                    public void onBtnAClick() {
//                        commonConfirmDialog.dismiss();
//                    }
//                });
//
//                commonConfirmDialog.show();
//            }
//        });
//
//        mProgressBar = (ProgressBar) findViewById(R.id.common_webview_progressBar);
//
//    }
//
//    @Override
//    public String getWebviewTitle() {
//        return title;
//    }
//
//    @Override
//    public String getPathId() {
//        return pathId;
//    }
//
//    @Override
//    public void enableTaobaoMonitor(boolean enable) {
//        mIsTaobaoMonitor = enable;
//    }
//
//    //处理弹窗和Icon
//    private void handleLayer(final int tabId) {
////        CptLogUtils.cptLog("webview load广告data");
//        CommonCoverLayerUtils.getInstance(this).loadTabDataForNet(tabId, TAG, new CommonCoverLayerUtils.CallBack() {
//            @Override
//            public void onResponse(final List<LayerModuleBean> moduleBean) {
////                CptLogUtils.cptLog("广告请求response");
//                ThreadUtils.runInUIThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (LayerModuleBean module : moduleBean) {
//                            if (module.getType() == 16) {
//                                CommonCoverLayerUtils.getInstance(CommonWebViewActivity.this).saveLayerDataDialog(tabId, module);
//                            } else if (module.getType() == 17) {
//                                List<LayerItemBean> moduleItems = module.getItems();
//                                CommonCoverLayerUtils.getInstance(CommonWebViewActivity.this).saveIconLayerList(tabId, JSON.toJSONString(moduleItems));
//                                initIconView(tabId, moduleItems);
//                            }
//                        }
//                        //轮询广告弹窗
//                        startAdvDialog();
//                    }
//                });
//            }
//
//            @Override
//            public void onTimeNotArrived() {
//                List<LayerItemBean> iconLayerList = CommonCoverLayerUtils.getInstance(CommonWebViewActivity.this).getIconLayerList(tabId);
//                initIconView(tabId, iconLayerList);
//                //轮询广告弹窗
//                startAdvDialog();
//            }
//        });
//
//        ThreadUtils.runInUIThreadDelay(new Runnable() {
//            @Override
//            public void run() {
//                CommonCoverLayerUtils.getInstance(CommonWebViewActivity.this).clearRequestTask(TAG);
//            }
//        }, 100);
//    }
//
//    //通用Icon的处理
//    private void initIconView(int tabId, List<LayerItemBean> iconLayerList) {
//        if (mCommonIconView == null) {
//            mCommonIconView = (CommonIconView) mCommonIconViewStub.inflate();
//            mCommonIconView.setPageId(tabId);
//        }
//        for (LayerItemBean itemBean : iconLayerList) {
//            if (itemBean.getIconType() == 0) {
//                mCommonIconView.setLeftIconAndClick(itemBean);
//            } else if (itemBean.getIconType() == 1) {
//                mCommonIconView.setRightIconAndClick(itemBean);
//            }
//        }
//    }
//
//    private void startAdvDialog() {
//        //广告弹窗
//        if (mCoverLayerDialog != null) {
//            CptLogUtils.cptLog("webview 开始广告");
//            mCoverLayerDialog.start(true);
//        }
//    }
//
//    private void refreshProgess(int newProgress) {
//        mProgressBar.setProgress(newProgress);
//        if (newProgress >= 100) {
//            if (handler != null && mProgressRunnable != null) {
//                handler.postDelayed(mProgressRunnable, 300);
//            }
//        } else {
//            if (handler != null && timeoutRunnable != null) {
//                handler.removeCallbacks(mProgressRunnable);
//            }
//            ViewUtils.showView(mProgressBar);
//        }
//    }
//
//    private void initFadeStatus() {
//        View fadeStatus = findViewById(R.id.common_webview_fade_status);
//        DeviceUtils.setFadeStatusBarHeight(getApplicationContext(), fadeStatus);
//    }
//
//    private void reFreshData() {
//        if (contentWebView != null) {
//            if (hasError) {
//                loadUrl();
//                // 神策埋点
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put(SAPropertyConsts.VIEW_PAGE_TAB_ID, "WebViewActivity");
//                    jsonObject.put(SAPropertyConsts.VIEW_PAGE_TAB_NAME, title);
//                    jsonObject.put(SAPropertyConsts.SOURCE_PATH, pushId);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                // 神策 下拉刷新的统计
//                SensorsDataAPI.sharedInstance().track(SAEventConsts.DROP_DOWN_REFRESH, jsonObject);
//            } else {
//                WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_REFRESH);
//            }
//        }
//    }
//
//    private void initMorePopWindow() {
//        mMoreWindow = new ActionBarMoreWindow(getApplicationContext());
//        mMoreWindow.setBtnClickListener(new ActionBarMoreWindow.OnBtnClickListener() {
//            @Override
//            public void onFresh() {
////                reFreshData();
//                contentWebView.reload();
//                mMoreWindow.dismiss();
//            }
//
//            @Override
//            public void onOpenBrowser() {
//                try {
//                    String url = contentWebView.getUrl();
//                    String finalUrl = IGlobalRoutePathConsts.COMMON_PREFIX + IGlobalRoutePathConsts.COMMON_EXTERNAL_WEB + "?htmlUrl=" + URLEncoder.encode(url, "UTF-8");
//                    ARouter.getInstance().build(Uri.parse(finalUrl)).navigation();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                mMoreWindow.dismiss();
//            }
//
//            @Override
//            public void onShare() {
//                try {
//                    String currentShareAction = null;
//
//                    if (!TextUtils.isEmpty(shareAction)) {
//                        currentShareAction = shareAction;
//                    } else {
//                        JSONObject defaultx = new JSONObject();
//                        defaultx.put("title", AppUtils.getAppName(getApplication(), getPackageName()));
//                        defaultx.put("content", title != null ? title : "");
//                        defaultx.put("webUrl", URLEncoder.encode(url, "UTF-8"));
//
//                        JSONObject object = new JSONObject();
//                        object.put("defaultx", defaultx);
//                        currentShareAction = IGlobalRoutePathConsts.COMMON_PREFIX + IGlobalRoutePathConsts.SHARE_PAGE +
//                                "?sharecontent=" + object.toString();
//                    }
//
//                    ARouter.getInstance().build(Uri.parse(currentShareAction)).navigation();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                mMoreWindow.dismiss();
//            }
//        });
//    }
//
//    private String getOutOpenUrl() {
//        try {
//            JSONObject data = new JSONObject();
//            data.put(IWebConsts.Key.KEY_PHEAD, NetDataUtils.getPheadJson(getApplicationContext()));
//
//            if (postData != null && !TextUtils.isEmpty(postData)) {
//                JSONObject postJson = new JSONObject(postData);
//
//                Iterator<String> keys = postJson.keys();
//                while (keys.hasNext()) {
//                    String key = keys.next();
//                    data.put(key, postJson.get(key));
//                }
//            }
//            StringBuilder urlSb = new StringBuilder(url);
//            urlSb.append("&data=");
//            urlSb.append(data.toString());
//            urlSb.append(IWebConsts.Key.KEY_OUTSIDE_OPEN);
//
//            return URLEncoder.encode(urlSb.toString(), "UTF-8");
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 初始化native js接口的方法
//     */
//    private void initWebViewInterface() {
//        if (contentWebView == null) {
//            return;
//        }
//        webAppInterface = new BaseWebInterface(this, contentWebView, this);
//        contentWebView.setJavascriptInterface(webAppInterface);
//    }
//
//    private void initTimeoutRunable() {
//        timeoutRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                if (DEBUG) {
//                    Logger.t(TAG).i("timeoutRunnable 超时");
//                }
//                // 超时
//                timeout = true;
//                hasError = true;
//                hideContentView();
//                hideLoadingPage();
//                hideLoadingDialog();
//                showNoDataView();
//            }
//        };
//    }
//
//
//    private Runnable mProgressRunnable;
//
//    private void initProgressRunnable() {
//        mProgressRunnable = new Runnable() {
//            @Override
//            public void run() {
//                ViewUtils.hideView(mProgressBar);
//            }
//        };
//    }
//
//    private void checkShowCloseBt() {
//        if (contentWebView == null) {
//            return;
//        }
//        if (contentWebView.canGoBack()) {
//            outterWebCloseBt.setVisibility(View.VISIBLE);
//        } else {
//            outterWebCloseBt.setVisibility(View.GONE);
//        }
//    }
//
//    /**
//     * 加载数据的方法
//     */
//    private void loadUrl() {
//        if (contentWebView != null && webAppInterface != null) {
//            // 设置状态
//            loadSuccess = false;
//            hasError = false;
//            showLoadingPage();
//            onRefreshComplete();
//            hideNoDataView();
//            if (!isFullScreen) {
//                showTitle();
//            }
//            hideContentView();
//            // 开始计时
//            if (handler != null && timeoutRunnable != null) {
//                handler.removeCallbacks(timeoutRunnable);
//                handler.postDelayed(timeoutRunnable, LOAD_TIME_OUT);
//            }
//
//            // 根据参数来决定使用什么请求方式
//            try {
//                JSONObject postJson = new JSONObject();
//                HashMap<String, String> postMap = new HashMap<>();
//                if (withHead) {
//                    postJson.put(IWebConsts.Key.KEY_PHEAD, NetDataUtils.getPheadJson(getApplicationContext()));
//                    postMap.put(IWebConsts.Key.KEY_PHEAD, NetDataUtils.getPheadJson(getApplicationContext()).toString());
//                }
//                if (postData != null && !TextUtils.isEmpty(postData)) {
//                    JSONObject postData = new JSONObject(this.postData);
//                    Iterator<String> keys = postData.keys();
//                    while (keys.hasNext()) {
//                        String key = keys.next();
//                        postJson.put(key, postData.get(key));
//                    }
//                }
//
//                if (usePost) {
//                    WebViewUtils.postUrlData(contentWebView, url, postJson);
//                } else {
//                    String dataStr = postJson.toString();
//                    if (isNeedParams) {
//                        url = handleUrl(url);
//                    }
//                    CptLogUtils.cptLog("url=" + url);
//                    if (TextUtils.isEmpty(dataStr) || dataStr.equals("{}")) {
//                        contentWebView.loadUrl(url);
//                    } else {
//                        contentWebView.loadUrl(url, postMap);
////                        StringBuilder mUrlSb = new StringBuilder(url);
////                        mUrlSb.append("&data=");
////                        mUrlSb.append(URLEncoder.encode(dataStr, "UTF-8"));
////                        contentWebView.loadUrl(mUrlSb.toString());
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    /**
//     * 加上理财需要的参数
//     *
//     * @param url
//     * @return
//     */
//    private String handleUrl(String url) {
//        StringBuilder urlStrBuilder = new StringBuilder();
//        urlStrBuilder.append(url);
//        if (TextUtils.isEmpty(url)) {
//            return url;
//        }
//
//        if (!url.contains("?")) {
//            urlStrBuilder.append("?meishayongdecanshu=1");//随便加个参数，下面的拼接就不用处理问号
//        }
//
//        if (!url.contains("&prd_id=")) {
//            urlStrBuilder.append("&prd_id=10001");//理财那边说 改成prd_id  用10001来标识我们
//        }
//
//        if (!url.contains("&platform=")) {
//            urlStrBuilder.append("&platform=android");
//        }
//
//        if (!TextUtils.isEmpty(DeviceUtils.getAndroidId(this))) {
//            if (!url.contains("&phone_id=")) {
//                urlStrBuilder.append("&phone_id=" + DeviceUtils.getAndroidId(this));
//            }
//        }
//
//        if (!TextUtils.isEmpty(ChannelUtils.getChannelFromApk(this))) {
//            if (!url.contains("&channel=")) {
//                urlStrBuilder.append("&channel=" + ChannelUtils.getChannelFromApk(this));
//            }
//        }
//
//        if (mAccountService.isLogined(this)) {
//            UserInfoBean userInfo = mAccountService.getUserInfo(this);
////            CptLogUtils.cptLog("webview加密后的手机号:" + userInfo.getUnionAuth());
//            if (!TextUtils.isEmpty(userInfo.getUnionAuth())) {
//                if (!url.contains("&union_auth=")) {
//                    urlStrBuilder.append("&union_auth=" + userInfo.getUnionAuth());
//                } else {
//                    urlStrBuilder.delete(url.indexOf("&union_auth="), url.length());
////                    CptLogUtils.cptLog("删除后："+urlStrBuilder.toString());
//                    urlStrBuilder.append("&union_auth=" + userInfo.getUnionAuth());
////                    CptLogUtils.cptLog("加上："+urlStrBuilder.toString());
//                }
//            }
//
////            if (!TextUtils.isEmpty(userInfo.getAccessToken())) {
////                if (!url.contains("&access_token=")) {
////                    urlStrBuilder.append("&access_token=" + userInfo.getAccessToken());
////                }
////            }
//        }
//        return urlStrBuilder.toString();
//    }
//
//    /**
//     * 初始化底部按钮点击监听器的方法
//     */
//    private void initButtonOnClickListener() {
//        backButtonOnClickListener = new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (contentWebView == null) {
//                    return;
//                }
//                if (contentWebView.canGoBack()) {
//                    contentWebView.goBack();
//                    checkShowCloseBt();
//                } else {
//                    finish();
//                }
//            }
//        };
//        closeButtonOnClickListener = new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        };
//    }
//
//    /**
//     * 返回键跳转处理的方法
//     */
//    private void backToJump() {
//        if (backLaunchParams != null
//                && !TextUtils.isEmpty(backLaunchParams.trim())) {
//
//        }
//    }
//
//    private void showContentView() {
//        ViewUtils.showView(contentWebView);
//    }
//
//    private void hideContentView() {
//        ViewUtils.hideView(contentWebView);
//    }
//
//    @Override
//    public void showLoadingPage() {
//        ViewUtils.showView(pageLoading);
//    }
//
//    @Override
//    public void hideLoadingPage() {
//        ViewUtils.hideView(pageLoading);
//    }
//
//    private void showNoDataView() {
//        ViewUtils.showView(noDataView);
//    }
//
//    private void hideNoDataView() {
//        ViewUtils.hideView(noDataView);
//    }
//
//    private void showTitle() {
//        ViewUtils.showView(actionBar);
//    }
//
//    private void hideTitle() {
//        ViewUtils.hideView(actionBar);
//    }
//
//
//    private void showToolbar() {
//        ViewUtils.showView(outterWebTitle);
//    }
//
//    public void hideToolbar() {
//        ViewUtils.hideView(outterWebTitle);
//    }
//
//    @Override
//    public Activity getActivity() {
//        return this;
//    }
//
//    @Override
//    public void enablePullToRefresh(boolean enable) {
//        if (pullToRefreshWebView != null) {
//            pullToRefreshWebView.setRefreshEnabled(enable);
//        }
//    }
//
//    @Override
//    public void enableReloadWhenLogin(boolean enable) {
//        whenLoginReloadPage = enable;
//    }
//
//    @Override
//    public void enableOnResumeOnPause(boolean enable) {
//        callbackWhenResumAndPause = enable;
//    }
//
//    @Override
//    public void enableOnBackPressed(boolean enable) {
//        takeOverBackPressed = enable;
//    }
//
//    @Override
//    public void pullToRefresh() {
//        if (pullToRefreshWebView != null) {
//            pullToRefreshWebView.setRefreshing(true);
//        }
//    }
//
//    @Override
//    public void onRefreshComplete() {
//        if (pullToRefreshWebView != null) {
//            pullToRefreshWebView.onRefreshComplete();
//        }
//    }
//
//    @Override
//    public void close() {
//        finish();
//    }
//
//    @Override
//    public void setActionButtons(String data) {
//        if (isDestroy) {
//            return;
//        }
//        if (mActionBarMenuController == null) {
//            mActionBarMenuController = new ActionBarButtonController(this.getApplicationContext());
//        }
//        ActionBarButtonList list = JSON.parseObject(data, ActionBarButtonList.class);
//        mActionBarMenuController.setButtons(list, actionBar.getMenuContainer(), contentWebView);
//    }
//
//    @Override
//    public void reload() {
//        loadUrl();
//    }
//
//    @Override
//    public void payByWeixin(AppWXPayBean appWXPayBean) {
//
//    }
//
//    @Override
//    public void payByAlipay(String orderInfo) {
//        PayUtils.handleAlipayPay(this, orderInfo);
//    }
//
//    @Override
//    public void showAnswerGdtDialog(final String posId) {
//        // 1.38 为了不影响市场审核提醒广告，去掉广点通sdk
//        if (isDestroy) {
//            return;
//        }
//    }
//
//    @Override
//    public void loadAd(String tabId) {
//        if (isDestroy) {
//            return;
//        }
//        if (mCoverLayerDialog != null) {
//            mCoverLayerDialog.setTabId(Integer.parseInt(tabId));
//            mCoverLayerDialog.setPageTitle(title);
//        }
//        CptLogUtils.cptLog("loadAd");
//        handleLayer(Integer.parseInt(tabId));
//    }
//
//    @Override
//    protected void onResume() {
//        if (isPause && mIsTaobaoMonitor) { // 开启监听淘宝下单
//            PreferencesManager preference = PreferencesManager.getDefaultSharedPreference(this);
//            int count = preference.getInt(IPreferencesConsts.COUNT_SHOW_ORDER_TIP, 0);
//            if (count < 3) {
//                String day = DateUtils.getStringDateShort();
//                boolean todayShow = preference.getBoolean(IPreferencesConsts.DAY_SHOW_ORDER_TIP + day, false);
//                if (!todayShow && !mIsOrderSuccess) {
//                    myHandler.sendEmptyMessageDelayed(HANDLER_MAG_SHOW_ORDER_TIP, 1000);
//                }
//            }
//        }
//        super.onResume();
//        if (callbackWhenResumAndPause) {
//            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_RESUME);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (callbackWhenResumAndPause) {
//            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_PAUSE);
//        }
//
//        //判断是否福利详情页  如果是 则做福利详情页的任务
//        if (mLastResumeTime != -1 && !TextUtils.isEmpty(url) && url.contains("/qn/common?funid=30501&service=quMall&welfareId=")) {
//            String id = url.substring(url.lastIndexOf("welfareId=") + 10, url.length());
//            if (!TextUtils.isEmpty(id)) {
////                CptLogUtils.cptLog("福利详情页 页面ID=" + id);
//                int time = RoutineTasksManager.getInstance(this).getWelfareDetailTaskTime();
//                if (time == -1) { // 用户首次
//                    if (System.currentTimeMillis() - mLastResumeTime > 3000) {//3000
//                        RoutineTasksManager.getInstance(this).recordBrowseWelfareDetailPage(id);
//                    }
//                } else {
//                    if (System.currentTimeMillis() - mLastResumeTime > time) {//time
//                        RoutineTasksManager.getInstance(this).recordBrowseWelfareDetailPage(id);
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (takeOverBackPressed && contentWebView != null && loadSuccess
//                && !hasError) {
//            // 返回键如果被接管，就回调js方法
//            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_BACKPRESSED);
//            return;
//        }
//        if (controlPageBack && contentWebView.canGoBack()) {
//            contentWebView.goBack();
//            checkShowCloseBt();
//        } else {
//            backToJump();
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        // onDestroy做了回收，这段正常应该是不会跑，但是不确定会不会有乱改的rom什么的，所以还是保留了
//        if (contentWebView != null) {
//            WebViewUtils.destroyWebView(contentWebView);
//            contentWebView = null;
//        }
//    }
//
//    private boolean mIsGotoChasePic;
//    public static final int REQUEST_CODE_PICK_IMAGE = 10000;//相册
//    public static final int REQUEST_CODE_CAPTURE_CAMERA = 10001;//照相机
//    private ValueCallback<Uri> mUploadMsg;
//    private ValueCallback<Uri[]> mUploadMsg5Plus;
//    private InfoUpdateDialog mPhotoDialog;
//
//    private void showPhotoDialog() {
//        if (mPhotoDialog == null) {
//            mPhotoDialog = new InfoUpdateDialog(getActivity());
//            mPhotoDialog.setCancelable(true);
//            mPhotoDialog.setItemText(getString(R.string.info_update_take_photo), getString(R.string.info_update_from_gallery));
//
//            mPhotoDialog.setListener(new InfoUpdateDialog.OnItemClickListener() {
//                @Override
//                public void onTopClick(String top) {
//                    mIsGotoChasePic = true;
//                    getImageFromCamera();
//                    mPhotoDialog.cancel();
//                }
//
//                @Override
//                public void onBottomClick(String bottom) {
//                    mIsGotoChasePic = true;
//                    getImageFromAlbum();
//                    mPhotoDialog.cancel();
//                }
//
//                @Override
//                public void onCancelClick() {
//                    mIsGotoChasePic = false;
//                    mPhotoDialog.cancel();
//                }
//            });
//
//            mPhotoDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                @Override
//                public void onShow(DialogInterface dialog) {
//                    mIsGotoChasePic = false;
//                }
//            });
//
//            mPhotoDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//
//                    if (!mIsGotoChasePic) {
//                        try {
//                            if (mUploadMsg != null) {
//                                mUploadMsg.onReceiveValue(null);
//                            }
//                            if (mUploadMsg5Plus != null) {
//                                mUploadMsg5Plus.onReceiveValue(null);
//                            }
//                        } catch (Exception e) {
//                        }
//                    }
//                }
//            });
//        }
//        mPhotoDialog.show();
//    }
//
//    protected void getImageFromAlbum() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
//    }
//
//    private String mCurImageName;
//
//    protected void getImageFromCamera() {
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            mCurImageName = "/" + System.currentTimeMillis() + ".jpg";
//            Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            File file = new File(Environment.getExternalStorageDirectory(), mCurImageName);
//            intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//            startActivityForResult(intent1, REQUEST_CODE_CAPTURE_CAMERA);
//        }
//    }
//
//    @Override
//    public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//        if ((requestCode == REQUEST_CODE_PICK_IMAGE || requestCode == REQUEST_CODE_CAPTURE_CAMERA) && resultCode == RESULT_OK) {
//
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Uri imageUri = null;
//                        String picturePath = null;
//
//                        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
//                            if (data.getData() != null) {
//                                picturePath = ImageUtil.getImagePath(getApplicationContext(), data.getData());
//                            }
//                        } else {
//                            File pictureFile = new File(Environment.getExternalStorageDirectory() + mCurImageName);
//                            if (pictureFile.exists() && pictureFile.isFile()) {
//                                picturePath = pictureFile.getPath();
//                            }
//                        }
//
//                        if (picturePath != null) {
//                            Bitmap getimage = ImageUtil.resizeBitmapFile0(picturePath, 1280, 1280);
//                            if (getimage != null) {
//                                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                                getimage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
//                                imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), getimage, null, null));
//                            }
//                        }
//
//                        if (mUploadMsg == null && mUploadMsg5Plus == null) {
//                            return;
//                        }
//
//                        if (imageUri == null) {
//                            if (mUploadMsg != null) {
//                                mUploadMsg.onReceiveValue(null);
//                            }
//                            if (mUploadMsg5Plus != null) {
//                                mUploadMsg5Plus.onReceiveValue(null);
//                            }
//                            return;
//                        }
//                        if (mUploadMsg != null) {
//                            mUploadMsg.onReceiveValue(imageUri);
//                            mUploadMsg = null;
//                        } else {
//                            mUploadMsg5Plus.onReceiveValue(new Uri[]{imageUri});
//                            mUploadMsg5Plus = null;
//                        }
//                    } catch (Exception ignore) {
//                    }
//                }
//            };
//
//            ThreadUtils.runInGlobalWorkThread(runnable);
//
//        } else if (resultCode == RESULT_CANCELED) {
//
//            try {
//                if (mUploadMsg != null) {
//                    mUploadMsg.onReceiveValue(null);
//                }
//                if (mUploadMsg5Plus != null) {
//                    mUploadMsg5Plus.onReceiveValue(null);
//                }
//            } catch (Exception e) {
//            }
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        CommonCoverLayerUtils.getInstance(this).clearRequestTask(TAG);
//        RoutineTasksManager.getInstance(this).cancelAssignMessage(coinId);
//        UMShareAPI.get(this).release();
//        EventBus.getDefault().unregister(this);
//        if (contentWebView != null) {
//            WebViewUtils.destroyWebView(contentWebView);
//            contentWebView = null;
//        }
//        if (webAppInterface != null) {
//            webAppInterface.destory();
//            webAppInterface = null;
//        }
//        if (pageLoading != null) {
//            pageLoading.clearAnimation();
//            pageLoading = null;
//        }
//        if (noDataView != null) {
//            noDataView.setRefrshBtClickListner(null);
//            noDataView = null;
//        }
//        if (handler != null) {
//            handler.removeCallbacks(timeoutRunnable);
//            handler.removeCallbacks(mProgressRunnable);
//            handler = null;
//        }
//        timeoutRunnable = null;
//
//        if (mAndroidBug5497Workaround != null) {
//            mAndroidBug5497Workaround.clean();
//            mAndroidBug5497Workaround = null;
//        }
//
//        if (mActionBarMenuController != null) {
//            mActionBarMenuController.clean();
//            mActionBarMenuController = null;
//        }
//
//        mCoverLayerDialog = null;
////
////        if (mAccountService != null ) {
////            mAccountService.removeLoginListener(this);
////            mAccountService = null;
////        }
//    }
//
//
//    @Override
//    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
//        mUploadMsg = uploadMsg;
//        showPhotoDialog();
//    }
//
//    @Override
//    public void showFileChooserCallBack(ValueCallback<Uri[]> filePathCallBack) {
//        mUploadMsg5Plus = filePathCallBack;
//        showPhotoDialog();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void handleLoginEvent(AccountEvent event) {
//        if (event == null || isDestroy) {
//            return;
//        }
//        switch (event.getWhat()) {
//            case AccountEvent.WHAT_GET_LOGIN_START:
//                break;
//            case AccountEvent.WHAT_GET_LOGIN_SUCCESS:
//                if (whenLoginReloadPage) {
//                    reload();
//                }
//                break;
//            case AccountEvent.WHAT_GET_LOGIN_ERROR:
//                break;
//            case AccountEvent.WHAT_GET_INFO_UPDATE:
//                if (whenLoginReloadPage) {
//                    reload();
//                }
//                break;
//            case AccountEvent.WHAT_GET_LOGIN_LOGIN_OUT_SUCCESS:
//                if (whenLoginReloadPage) {
//                    reload();
//                }
//                break;
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void handlePayEvent(PayResultEvent event) {
//        if (event == null || isDestroy) {
//            return;
//        }
//        WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_PAY_CALLBACK + "(" + event.resultCode + ")");
//    }
//
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void handleMallEvent(MallEvent event) {
//        if (event == null || isDestroy || !mIsTaobaoMonitor) {
//            return;
//        }
//        int what = event.getWhat();
//        if (what == MallEvent.WHAT_ORDER_SUCCESS) {
//            // 已经拿到回调
//            mIsOrderSuccess = true;
//            myHandler.removeMessages(HANDLER_MAG_SHOW_ORDER_TIP);
//        }
//    }
//
//    @Override
//    protected boolean isNeedTranslateBar() {
//        if (isFullScreen) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    Handler myHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case HANDLER_MAG_SHOW_ORDER_TIP:
//                    ARouter.getInstance().build(IGlobalRoutePathConsts.ORDER_TIP_DIALOG).withBoolean("fromProductDetail", false).navigation();
//                    PreferencesManager preference = PreferencesManager.getDefaultSharedPreference(CommonWebViewActivity.this);
//                    int count = preference.getInt(IPreferencesConsts.COUNT_SHOW_ORDER_TIP, 0);
//                    preference.putBoolean(IPreferencesConsts.DAY_SHOW_ORDER_TIP + DateUtils.getStringDateShort(), true);
//                    preference.putInt(IPreferencesConsts.COUNT_SHOW_ORDER_TIP, count + 1);
//                    preference.commit();
//                    break;
//
//            }
//        }
//    };
//}
