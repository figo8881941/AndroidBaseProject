package com.duoduo.commonbusiness.web;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.duoduo.commonbase.utils.AppUtils;
import com.duoduo.commonbase.utils.ThreadUtils;
import com.duoduo.commonbusiness.net.CommonNetDataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import wendu.dsbridge.CompletionHandler;


/**
 * 供WebView调用的接口
 *
 * @author wangzhuobin
 */
public class BaseWebInterface {

    protected WeakReference<WebView> webViewReference;

    protected WeakReference<IBaseWebViewContainer> containerReference;

    protected Context context;

    protected boolean isDestory = false;

    public BaseWebInterface(Context context, WebView view, IBaseWebViewContainer container) {
        this.context = context;
        webViewReference = new WeakReference<WebView>(view);
        containerReference = new WeakReference<IBaseWebViewContainer>(container);
    }

    protected IBaseWebViewContainer getContainer() {
        if (containerReference != null) {
            return containerReference.get();
        }
        return null;
    }

    @JavascriptInterface
    public String getPheadString(JSONObject jsonObject) throws JSONException {
        return CommonNetDataUtils.getPheadJsonWithGlobalBuildConfig(context).toString();
    }

    @JavascriptInterface
    public void showLoadingPage(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.showLoadingPage();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void hideLoadingPage(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.hideLoadingPage();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void showLoadingDialog(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.showLoadingDialog();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void hideLoadingDialog(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.hideLoadingDialog();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void enablePullToRefresh(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final boolean enable = jsonObject.optBoolean("enable", false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.enablePullToRefresh(enable);
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void enableReloadWhenLogin(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final boolean enable = jsonObject.optBoolean("enable", false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.enableReloadWhenLogin(enable);
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }


    @JavascriptInterface
    public void enableOnResumeOnPause(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final boolean enable = jsonObject.optBoolean("enable", false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.enableOnResumeOnPause(enable);
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void enableOnBackpressed(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final boolean enable = jsonObject.optBoolean("enable", false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.enableOnBackPressed(enable);
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void pullToRefresh(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.pullToRefresh();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void onRefreshComplete(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.onRefreshComplete();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void close(final JSONObject jsonObjecisAppInstallt) throws JSONException {
        IBaseWebViewContainer container = getContainer();
        if (container != null) {
            container.close();
        }
    }

    @JavascriptInterface
    public void isAppInstall(final JSONObject jsonObject, CompletionHandler handler) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final String pkgname = jsonObject.optString("pkgname");
        if (TextUtils.isEmpty(pkgname)) {
            return;
        }
        boolean isInstall = AppUtils.isAppInstall(context, pkgname);
        JSONObject result = new JSONObject();
        result.put("status", isInstall ? 1 : 0);
        handler.complete(result.toString());
    }

    @JavascriptInterface
    public void toast(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final String text = jsonObject.optString("text");
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (context == null) {
                    return;
                }
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    /**
     * 复制内容到系统粘贴板的方法
     *
     * @param jsonObject
     */
    @JavascriptInterface
    public void copyToClipboard(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final String content = jsonObject.optString("content");
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (context == null) {
                    return;
                }
                ClipboardManager clipboardManager = (ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(content);
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void getClipboardText(final JSONObject jsonObject, final CompletionHandler handler) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (context == null) {
                    return;
                }
                try {
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    JSONObject result = new JSONObject();
                    result.put("clipboardText", clipboardManager.getText());
                    handler.complete(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    @JavascriptInterface
    public void setActionButtons(final JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.setActionButtons(jsonObject.toString());
                }
            }
        };
        ThreadUtils.runInUIThread(runnable, false);
    }

    /**
     * 启动应用程序
     */
    @JavascriptInterface
    public void launch(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return;
        }
        final String param = jsonObject.optString("param");
        Log.e("cpt", "-=-=-：" + param);
        if (TextUtils.isEmpty(param)) {
            return;
        }
        try {
            ARouter.getInstance().build(Uri.parse(param)).navigation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据包名打开应用程序
     */
    @JavascriptInterface
    public void launchApp(final JSONObject jsonObject, final CompletionHandler handler) {
        JSONObject result = new JSONObject();
        if (jsonObject == null) {
            try {
                result.put("status", 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            handler.complete(result.toString());
            return;
        }

        String pkgname = jsonObject.optString("pkgname");
        if (TextUtils.isEmpty(pkgname)) {
            try {
                result.put("status", 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            handler.complete(result.toString());
        } else {
            boolean isSuccess = AppUtils.launchApp(context, pkgname);
            try {
                result.put("status", isSuccess ? 1 : 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            handler.complete(result.toString());
        }
    }

    /**
     * 重新加载数据的方法
     */
    @JavascriptInterface
    public void reload(JSONObject jsonObject) throws JSONException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IBaseWebViewContainer container = getContainer();
                if (container != null) {
                    container.reload();
                }
            }
        };
        ThreadUtils.runInUIThread(runnable);
    }

    /**
     * 取登录的方法
     *
     * @param jsonObject
     */
    @JavascriptInterface
    public void login(JSONObject jsonObject, final CompletionHandler completionHandler) {
//        if (jsonObject == null) {
//            return;
//        }
//
//        String phone = jsonObject.optString("phone");
//        if (TextUtils.isEmpty(phone)) {
//            ARouter.getInstance().build(IGlobalRoutePathConsts.LOGIN_PAGE).navigation();
//        } else {
//            ARouter.getInstance().build(Uri.parse(IGlobalRoutePathConsts.COMMON_PREFIX
//                    + IGlobalRoutePathConsts.LOGIN_PAGE + "?phone=" + phone)).navigation();
//        }
//
//        final IAccountService accountService = (IAccountService) ARouter.getInstance().build
//                (IGlobalRouteProviderConsts.ACCOUNT_SERVICE).navigation();
//        if (accountService != null) {
//            accountService.addLoginListener(new OnLoginListener() {
//                @Override
//                public void onLogin() {
//                    if (completionHandler != null) {
//                        String token = accountService.getAccessToken();
//                        completionHandler.complete("{\"status\":1, \"access_token\": " + token + "}");
//                    }
//                    accountService.removeLoginListener(this);
//                }
//
//                @Override
//                public void onLoginFailed() {
//                    if (completionHandler != null) {
//                        completionHandler.complete("{\"status\":0}");
//                    }
//                }
//
//                @Override
//                public void onLoginCancel() {
//                    accountService.removeLoginListener(this);
//                }
//
//                @Override
//                public void onLoginStart() {
//                }
//
//                @Override
//                public void onLogout() {
//                }
//
//                @Override
//                public void onUserInfoUpdate() {
//                }
//            });
//        }

    }

    protected Activity getActivity() {
        IBaseWebViewContainer container = getContainer();
        if (container != null) {
            return container.getActivity();
        }
        return null;
    }

    protected WebView getWebView() {
        if (webViewReference != null) {
            return webViewReference.get();
        }
        return null;
    }

    public void destory() {
        isDestory = true;
        webViewReference = null;
        context = null;
    }
}
