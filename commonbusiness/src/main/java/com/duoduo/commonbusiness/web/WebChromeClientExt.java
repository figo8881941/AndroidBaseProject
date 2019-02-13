package com.duoduo.commonbusiness.web;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.lang.ref.WeakReference;

import wendu.dsbridge.DWebView;

/**
 * WebChromeClient扩展
 */

public class WebChromeClientExt extends WebChromeClient implements DWebView.FileChooser {

    public WebChromeClientExt() {
    }

    @Override
    public void openFileChooser(ValueCallback valueCallback, String acceptType) {

    }

    @Override
    public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {

    }
}
