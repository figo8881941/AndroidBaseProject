package com.duoduo.web.container;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.duoduo.commonbase.utils.ActivityUtils;
import com.duoduo.commonbase.utils.DeviceUtils;
import com.duoduo.commonbase.utils.StatusBarUtils;
import com.duoduo.commonbase.utils.ViewUtils;
import com.duoduo.commonbase.view.IconImageView;
import com.duoduo.commonbusiness.activity.BaseLoadingDialogActivity;
import com.duoduo.commonbusiness.config.GlobalBuildConfig;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;
import com.duoduo.commonbusiness.router.path.IWebPath;
import com.duoduo.commonbusiness.view.CommonActionBar;
import com.duoduo.commonbusiness.view.CommonErrorView;
import com.duoduo.commonbusiness.view.CommonPageLoading;
import com.duoduo.commonbusiness.web.BaseWebInterface;
import com.duoduo.commonbusiness.web.IBaseWebViewContainer;
import com.duoduo.commonbusiness.web.IWebConsts;
import com.duoduo.commonbusiness.web.WebChromeClientExt;
import com.duoduo.commonbusiness.web.WebViewUtils;
import com.duoduo.web.R;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import wendu.dsbridge.DWebView;

/**
 * Webview加载内容的activity，里面包含加载页面和超时错误处理
 *
 * @author wangzhuobin
 */
@Route(path = IWebPath.COMMON_WEBVIEW_ACTIVITY)
public class CommonWebViewActivity extends BaseLoadingDialogActivity
        implements IBaseWebViewContainer, WebChromeClientExt.OpenFileChooserCallBack {

    private final boolean DEBUG = GlobalBuildConfig.getInstance().isDebugMode();
    private final String TAG = this.getClass().getSimpleName();

    // 网页加载超时时间
    private final long LOAD_TIME_OUT = 30 * 1000;

    // 标题
    private CommonActionBar actionBar;

    // 加载外部页面使用的标题栏
    private View outterWebTitle;
    private ImageView outterWebBackBt;
    private ImageView outterWebCloseBt;
    private TextView outterWebTextView;
    private View.OnClickListener backButtonOnClickListener;
    private View.OnClickListener closeButtonOnClickListener;


    private SmartRefreshLayout smartRefreshLayout;
    // 显示h5的webview
    private DWebView contentWebView;

    // js接口
    private BaseWebInterface webAppInterface;

    // 加载出错界面
    private CommonErrorView noDataView;

    // 加载loading界面
    private CommonPageLoading pageLoading;

    // 加载进度条
    private ProgressBar mProgressBar;

    // 处理超时的Runnable
    private Runnable timeoutRunnable;

    // 加载进度的 Runnable
    private Runnable mProgressRunnable;

    // 页面加载是否有错误
    private boolean hasError = false;

    // 页面是否加载成功
    private boolean loadSuccess = false;

    // 页面是否加载超时
    private boolean timeout = false;

    private Handler handler;

    // 标题
    @Autowired
    protected String title;
    // 要加载的地址
    @Autowired(name = "htmlUrl")
    protected String url;
    // 请求里面是否带上phead。值是true，需要带上phead。如果为false，就不带。
    @Autowired
    protected boolean withHead = true;
    // 是否使用post的方式加载页面。
    @Autowired
    protected boolean usePost;
    // 是否显示toolbar。值是false，不需要显示。如果为true，就显示。
    @Autowired
    protected boolean showToolbar;
    // html页面是否接管返回键的处理。如果接管，按返回键时，将调用js的方法onBackPressed
    @Autowired
    protected boolean takeOverBackPressed;
    // 客户端是否在resume和pause时回调页面接口javascript:onResume()和javascript:onPause()。值是false，不回调。如果是true，需要回调。
    @Autowired
    protected boolean callbackWhenResumAndPause;
    //登录后是否自动刷新页面
    @Autowired
    protected boolean whenLoginReloadPage;
    //是否全屏
    @Autowired
    protected boolean isFullScreen;
    // 客户端是否显示标题。客户端需要显示标题，如果为false，则客户端不显示标题，有页面来处理。
    @Autowired
    protected boolean showTitle;
    // post携带的数据，json格式的字符串
    @Autowired
    protected String postData;
    // 是否控制网页返回
    @Autowired
    protected boolean controlPageBack;
    // 动态注入的js
    @Autowired
    protected String injectJS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果需要全屏展示
        if (isFullScreen) {
            ActivityUtils.changeActivityToFullScreen(this);
        }

        // 设置状态栏颜色
        StatusBarUtils.changeStatusBarTran(this, false);

        setContentView(R.layout.web_container_common_webview_activity);

        handler = new Handler(Looper.getMainLooper());

        // 初始化超时runable
        initTimeoutRunable();
        initProgressRunnable();
        // 初始化界面
        initView();
        // 加载页面
        loadUrl();
    }

    /**
     * 初始化界面的方法
     */
    @SuppressLint("JavascriptInterface")
    private void initView() {
        actionBar = (CommonActionBar) findViewById(R.id.actionbar);
        actionBar.setTitle(title);
        actionBar.setBackButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takeOverBackPressed && contentWebView != null && loadSuccess
                        && !hasError) {
                    // 返回键如果被接管，就回调js方法
                    WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_BACKPRESSED);
                    return;
                }
                finish();
            }
        });

        // 初始化按钮监听器
        initButtonOnClickListener();
        outterWebTitle = findViewById(R.id.webview_guide_bar);
        outterWebTextView = (TextView) findViewById(R.id.outter_webview_title);
        outterWebTextView.setText(title);
        outterWebBackBt = (ImageView) findViewById(R.id.outter_webview_back_bt);
        outterWebBackBt.setOnClickListener(backButtonOnClickListener);
        outterWebCloseBt = (ImageView) findViewById(R.id.outter_webview_close_bt);
        outterWebCloseBt.setOnClickListener(closeButtonOnClickListener);

        if (!TextUtils.isEmpty(title)) {
            showTitle = true;
        }

        if (isFullScreen) {
            hideTitle();
            hideToolbar();
        } else {
            if (showTitle && !showToolbar) {
                showTitle();
            } else {
                hideTitle();
            }

            if (showToolbar) {
                showToolbar();
            } else {
                hideToolbar();
            }
        }

        // 错误页面
        noDataView = (CommonErrorView) findViewById(R.id.no_data_view);
        noDataView.setRefrshBtClickListner(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 错误界面点击刷新
                loadUrl();
            }
        });
        // loading界面
        pageLoading = (CommonPageLoading) findViewById(R.id.page_loading);

        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        // 不能上拉加载更多
        smartRefreshLayout.setEnableLoadmore(false);
        // 默认不能下拉刷新，除非页面开启
        enablePullToRefresh(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });

        contentWebView = (DWebView) findViewById(R.id.webview);
        contentWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        // 初始化native js接口
        initWebViewInterface();
        WebViewUtils.setFullFunctionForWebView(getApplicationContext(), contentWebView, DEBUG);
        WebChromeClient webChromeClient = new WebChromeClientExt(this) {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (DEBUG) {
                    Logger.t(TAG).i("onProgressChanged :" + newProgress);
                }
                // 刷新加载进度条
                refreshProgess(newProgress);

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

                        if (isFullScreen) {
                            hideTitle();
                            hideToolbar();
                        } else {
                            if (showTitle && !showToolbar) {
                                showTitle();
                            } else {
                                hideTitle();
                            }

                            if (showToolbar) {
                                showToolbar();
                            } else {
                                hideToolbar();
                            }
                        }

                        showContentView();
                        checkShowCloseBt();
                    } else {
                        showNoDataView();
                        hideLoadingPage();
                        hideLoadingDialog();
                        hideContentView();
                        hasError = false;
                    }
                    if (handler != null && timeoutRunnable != null) {
                        handler.removeCallbacks(timeoutRunnable);
                    }
                } else {
                    if (!DeviceUtils.isNetworkOK(getApplicationContext())) {
                        hasError = true;
                    }
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String currentTitle) {
                super.onReceivedTitle(view, currentTitle);
                if (!TextUtils.isEmpty(title)) {
                    return;
                }

                if (!WebViewUtils.isInnerUrl(view.getUrl())) {
                    outterWebTextView.setText(currentTitle != null ? currentTitle : "");
                    actionBar.setTitle(currentTitle != null ? currentTitle : "");
                    title = currentTitle;
                } else {
                    if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(currentTitle)) {
                        actionBar.setTitle(currentTitle);
                        outterWebTextView.setText(currentTitle);
                        title = currentTitle;
                    } else {
                        outterWebTextView.setText(title);
                        actionBar.setTitle(title);
                    }
                }
            }
        };
        contentWebView.setWebChromeClient(webChromeClient);
        contentWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (WebViewUtils.handleUrlIntent(
                        CommonWebViewActivity.this, url)) {
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
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (DEBUG) {
                    Logger.t(TAG).i("onReceivedError=");
                }
                hasError = true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); // Android默认的处理方式
                handler.proceed();  // 接受所有网站的证书
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!TextUtils.isEmpty(injectJS)) {
                    String js = "window.phead=" + CommonNetDataUtils.getPheadJsonWithGlobalBuildConfig(getApplicationContext()).toString().replace("\"", "'") + ";";
                    js += "var newscript = document.createElement(\"script\");";
                    js += ("newscript.src=\"" + injectJS + "\";");
                    js += ("newscript.id=\"xmiles\";");
                    js += "document.getElementsByTagName('head')[0].appendChild(newscript);";
                    view.loadUrl("javascript:" + js);
                }
            }
        });

        mProgressBar = (ProgressBar) findViewById(R.id.common_webview_progressBar);

    }

    /**
     * 刷新加载进度条的方法
     * @param newProgress
     */
    private void refreshProgess(int newProgress) {
        mProgressBar.setProgress(newProgress);
        if (newProgress >= 100) {
            if (handler != null && mProgressRunnable != null) {
                handler.postDelayed(mProgressRunnable, 300);
            }
        } else {
            if (handler != null && timeoutRunnable != null) {
                handler.removeCallbacks(mProgressRunnable);
            }
            ViewUtils.showView(mProgressBar);
        }
    }

    /**
     * 刷新页面的方法
     */
    private void refreshData() {
        if (contentWebView != null) {
            if (hasError) {
                loadUrl();
            } else {
                WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_REFRESH);
            }
        }
    }

    /**
     * 初始化native js接口的方法
     */
    private void initWebViewInterface() {
        if (contentWebView == null) {
            return;
        }
        webAppInterface = new BaseWebInterface(this, contentWebView, this);
        contentWebView.setJavascriptInterface(webAppInterface);
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
                hasError = true;
                hideContentView();
                hideLoadingPage();
                hideLoadingDialog();
                showNoDataView();
            }
        };
    }

    private void initProgressRunnable() {
        mProgressRunnable = new Runnable() {
            @Override
            public void run() {
                ViewUtils.goneView(mProgressBar);
            }
        };
    }

    private void checkShowCloseBt() {
        if (contentWebView == null) {
            return;
        }
        if (contentWebView.canGoBack()) {
            outterWebCloseBt.setVisibility(View.VISIBLE);
        } else {
            outterWebCloseBt.setVisibility(View.GONE);
        }
    }

    /**
     * 加载数据的方法
     */
    private void loadUrl() {
        if (contentWebView != null && webAppInterface != null) {
            // 设置状态
            loadSuccess = false;
            hasError = false;
            showLoadingPage();
            onRefreshComplete();
            hideNoDataView();
            if (!isFullScreen) {
                showTitle();
            }
            hideContentView();
            // 开始计时
            if (handler != null && timeoutRunnable != null) {
                handler.removeCallbacks(timeoutRunnable);
                handler.postDelayed(timeoutRunnable, LOAD_TIME_OUT);
            }

            // 根据参数来决定使用什么请求方式
            try {
                JSONObject postJson = new JSONObject();
                HashMap<String, String> postMap = new HashMap<>();
                JSONObject pheadJson = CommonNetDataUtils.getPheadJsonWithGlobalBuildConfig(getApplicationContext());
                if (withHead) {
                    postJson.put(IWebConsts.Key.KEY_PHEAD, pheadJson);
                    postMap.put(IWebConsts.Key.KEY_PHEAD, pheadJson.toString());
                }
                if (postData != null && !TextUtils.isEmpty(postData)) {
                    JSONObject postData = new JSONObject(this.postData);
                    Iterator<String> keys = postData.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        postJson.put(key, postData.get(key));
                    }
                }

                if (usePost) {
                    WebViewUtils.postUrlData(contentWebView, url, postJson);
                } else {
                    String dataStr = postJson.toString();
                    if (TextUtils.isEmpty(dataStr) || dataStr.equals("{}")) {
                        contentWebView.loadUrl(url);
                    } else {
                        contentWebView.loadUrl(url, postMap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 初始化底部按钮点击监听器的方法
     */
    private void initButtonOnClickListener() {
        backButtonOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contentWebView == null) {
                    return;
                }
                if (contentWebView.canGoBack()) {
                    contentWebView.goBack();
                    checkShowCloseBt();
                } else {
                    finish();
                }
            }
        };
        closeButtonOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    private void showContentView() {
        ViewUtils.showView(contentWebView);
    }

    private void hideContentView() {
        ViewUtils.goneView(contentWebView);
    }

    private void showNoDataView() {
        ViewUtils.showView(noDataView);
    }

    private void hideNoDataView() {
        ViewUtils.goneView(noDataView);
    }

    private void showTitle() {
        ViewUtils.showView(actionBar);
    }

    private void hideTitle() {
        ViewUtils.goneView(actionBar);
    }

    private void showToolbar() {
        ViewUtils.showView(outterWebTitle);
    }

    public void hideToolbar() {
        ViewUtils.goneView(outterWebTitle);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showLoadingPage() {
        ViewUtils.showView(pageLoading);
    }

    @Override
    public void hideLoadingPage() {
        ViewUtils.goneView(pageLoading);
    }

    @Override
    public void enablePullToRefresh(boolean enable) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setEnableRefresh(enable);
        }
    }

    @Override
    public void enableReloadWhenLogin(boolean enable) {
        whenLoginReloadPage = enable;
    }

    @Override
    public void enableOnResumeOnPause(boolean enable) {
        callbackWhenResumAndPause = enable;
    }

    @Override
    public void enableOnBackPressed(boolean enable) {
        takeOverBackPressed = enable;
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
        finish();
    }

    @Override
    public void setActionButtons(String data) {
        if (isDestroy) {
            return;
        }
    }

    @Override
    public void reload() {
        loadUrl();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (callbackWhenResumAndPause) {
            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_RESUME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (callbackWhenResumAndPause) {
            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_PAUSE);
        }
    }

    @Override
    public void onBackPressed() {
        if (takeOverBackPressed && contentWebView != null && loadSuccess
                && !hasError) {
            // 返回键如果被接管，就回调js方法
            WebViewUtils.evaluateJavascript(contentWebView, IWebConsts.JS.METHOD_ON_BACKPRESSED);
            return;
        }
        if (controlPageBack && contentWebView.canGoBack()) {
            contentWebView.goBack();
            checkShowCloseBt();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // onDestroy做了回收，这段正常应该是不会跑，但是不确定会不会有乱改的rom什么的，所以还是保留了
        if (contentWebView != null) {
            WebViewUtils.destroyWebView(contentWebView);
            contentWebView = null;
        }
    }

    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
//        mUploadMsg = uploadMsg;
//        showPhotoDialog();
    }

    @Override
    public void showFileChooserCallBack(ValueCallback<Uri[]> filePathCallBack) {
//        mUploadMsg5Plus = filePathCallBack;
//        showPhotoDialog();
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        if (noDataView != null) {
            noDataView.setRefrshBtClickListner(null);
            noDataView = null;
        }
        if (handler != null) {
            handler.removeCallbacks(timeoutRunnable);
            handler.removeCallbacks(mProgressRunnable);
            handler = null;
        }
        timeoutRunnable = null;
    }

}
