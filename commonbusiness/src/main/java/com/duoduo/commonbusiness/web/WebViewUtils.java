package com.duoduo.commonbusiness.web;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import wendu.dsbridge.DWebView;

/**
 * WebView工具类
 *
 * @author wangzhuobin
 */
public class WebViewUtils {


    /**
     * Webview的弱引用集合，把可以限制网络图片加载的webview放到里面去，省流量模式统一控制
     */
    private static ArrayList<WeakReference<WebView>> canBlockNetworkImageWeakReferences;


    /**
     * WebView打开全功能设置的方法
     *
     * @param context
     * @param webView
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setFullFunctionForWebView(Context context, WebView webView, boolean isDebug) {
        if (webView == null) {
            return;
        }
        // 设置WebView可调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(isDebug);
        }

        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        if (webSettings == null) {
            return;
        }
        // 打开js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 打开缓存
        webSettings.setAppCacheEnabled(true);
        // 设置可以使用localStorage
        webSettings.setDomStorageEnabled(true);
        // 打开页面时， 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //不要设置新窗口打开
        webSettings.setSupportMultipleWindows(false);
        // 支持db
        webSettings.setDatabaseEnabled(true);
        String dir = context.getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(dir);
        // 支持定位
        webSettings.setGeolocationEnabled(true);

        addWebViewToCanBlockNetworkImageReferences(webView);

        // cjm TODO
//        IMainService mainProvider = (IMainService) ARouter.getInstance().build("/main/provider/MainProviderService").navigation();
//        if (mainProvider != null && mainProvider.isFlowSave() && !DeviceUtils.isWifiEnable(context.getApplicationContext())) {
//            webSettings.setBlockNetworkImage(true);
//        } else {
//            webSettings.setBlockNetworkImage(false);
//        }

        /**
         *  Webview在安卓5.0之前默认允许其加载混合网络协议内容
         *  在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 移除接口，防止安全问题
        removeWebViewUnsafeJavaScriptInterface(webView);
    }

    public static void addWebViewToCanBlockNetworkImageReferences(
            WebView webView) {
        if (webView == null) {
            return;
        }
        synchronized (WebViewUtils.class) {
            if (canBlockNetworkImageWeakReferences == null) {
                canBlockNetworkImageWeakReferences = new ArrayList<>();
            }
            ArrayList<WeakReference<WebView>> remove = new ArrayList<>();
            for (WeakReference<WebView> weakReference : canBlockNetworkImageWeakReferences) {
                if (weakReference == null) {
                    remove.add(weakReference);
                    continue;
                }
                if (weakReference.get() == null) {
                    remove.add(weakReference);
                    continue;
                }
                if (weakReference.get() == webView) {
                    return;
                }
            }
            canBlockNetworkImageWeakReferences.removeAll(remove);
            canBlockNetworkImageWeakReferences.add(new WeakReference<>(webView));
        }
    }

    /**
     * 设置弱引用集合里面的webview是否加载图片
     *
     * @param block
     */
    public static void setWebViewBlockNetworkImage(boolean block) {
        synchronized (WebViewUtils.class) {
            if (canBlockNetworkImageWeakReferences == null) {
                return;
            }
            ArrayList<WeakReference<WebView>> remove = new ArrayList<WeakReference<WebView>>();
            for (WeakReference<WebView> weakReference : canBlockNetworkImageWeakReferences) {
                if (weakReference == null) {
                    remove.add(weakReference);
                    continue;
                }
                WebView webView = weakReference.get();
                if (webView == null) {
                    remove.add(weakReference);
                    continue;
                } else {
                    WebSettings webSettings = webView.getSettings();
                    if (webSettings != null) {
                        webSettings.setBlockNetworkImage(block);
                    }
                }

            }
            canBlockNetworkImageWeakReferences.removeAll(remove);
        }
    }

    /**
     * API17以下的手机移除有安全漏洞的JS接口
     * API17以后谷歌保障
     *
     * @param webView
     */
    public static void removeWebViewUnsafeJavaScriptInterface(WebView webView) {
        if (webView != null) {
            // api 17 以下 WebView存在漏洞，会被攻击者利用反射获取信息，需要移除部分js接口
            // http://www.wooyun.org/bugs/wooyun-2014-078617
            if (Build.VERSION.SDK_INT < 17) {
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
            }
        }
    }

    public static boolean handleUrlIntent(Context context, String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return false;
        }
        if (handleSpecialUrl(url)) {
            return true;
        }
        boolean ret = true;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            ret = false;
        }
        return true;
    }

    private static boolean handleSpecialUrl(String url) {
        if (url == null) {
            return true;
        }
        if (url.startsWith("tmall://")) {
            return true;
        }
        if (url.startsWith("suning://")) {
            return true;
        }
        if (url.startsWith("tbopen://")) {
            return true;
        }
        return url.startsWith("openapp.jdmobile://");
    }


    public static void postUrlData(WebView webView, String url, JSONObject data) {
        byte[] postData = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("handle", 0);
            jsonObject.put("shandle", 0);
            jsonObject.put("data", data);
            postData = jsonObject.toString().getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 加载页面
        webView.postUrl(url, postData);
    }

    /**
     * 所有的{@link WebView}必须这样销毁
     *
     * @param webView
     */
    public static void destroyWebView(WebView webView) {
        if (webView != null) {
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();
        }
    }

    /**
     * 执行js的方法
     *
     * @param webView
     * @param jsString
     */
    public static void evaluateJavascript(DWebView webView, String jsString) {
        try {
            if (webView != null && jsString != null
                    && !TextUtils.isEmpty(jsString.trim())) {
                webView.evaluateJavascript(jsString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是不是本应用的url
     *
     * @param url
     * @return
     */
    public static boolean isInnerUrl(String url) {
        boolean isInnerUrl = false;
        if (url != null && (url.contains("gmilesquan.com") ||
                url.contains("chezhuwuyou.cn"))) {

            isInnerUrl = true;
        }
        return isInnerUrl;
    }

}
