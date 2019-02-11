package com.duoduo.commonbusiness.web;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import wendu.dsbridge.DWebView;

/**
 * Created by zhongwenqi on 2017/4/17.
 */

public class WebChromeClientExt extends WebChromeClient implements DWebView.FileChooser {

    private OpenFileChooserCallBack mOpenFileChooserCallBack;

    public WebChromeClientExt(OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
    }

    // >3.0
    @Override
    public void openFileChooser(ValueCallback valueCallback, String s) {
        mOpenFileChooserCallBack.openFileChooserCallBack(valueCallback, s);
    }

    // >4.1
    @Override
    public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
        mOpenFileChooserCallBack.openFileChooserCallBack(valueCallback, acceptType);
    }

    // For Android 5.0+
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        mOpenFileChooserCallBack.showFileChooserCallBack(filePathCallback);
        return true;
    }

    public interface OpenFileChooserCallBack {
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);

        void showFileChooserCallBack(ValueCallback<Uri[]> filePathCallBack);
    }
}
