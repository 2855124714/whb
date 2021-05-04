package com.fjsy.architecture.ui.binding_adapter;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.BindingAdapter;

import com.fjsy.architecture.ui.widget.X5WebView;
import com.fjsy.architecture.utils.X5WebViewImgInitUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

public class WebViewBindingAdapter {

    @BindingAdapter(value = {"webViewLoadDataBaseUrl", "webViewLoadDataContent"}, requireAll = false)
    public static void loadDataWithBaseURL(WebView webView, String webViewLoadDataBaseUrl, String webViewLoadDataContent) {
        webView.loadDataWithBaseURL(!TextUtils.isEmpty(webViewLoadDataBaseUrl) ? webViewLoadDataBaseUrl : null, webViewLoadDataContent, "text/html", "UTF-8", null);
    }

    @BindingAdapter(value = {"webViewLoadDataBaseUrl", "webViewLoadDataContent"}, requireAll = false)
    public static void loadDataWithBaseURL(com.tencent.smtt.sdk.WebView webView, String webViewLoadDataBaseUrl, String webViewLoadDataContent) {
        webView.loadDataWithBaseURL(!TextUtils.isEmpty(webViewLoadDataBaseUrl) ? webViewLoadDataBaseUrl : null, webViewLoadDataContent, "text/html", "UTF-8", null);
    }

    @BindingAdapter(value = {"webViewClient", "webChromeClient"}, requireAll = false)
    public static void initWebViewClient(WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient){
        webView.setWebViewClient(webViewClient==null?(new WebViewClient()):webViewClient);
        webView.setWebChromeClient(webChromeClient==null?(new WebChromeClient()):webChromeClient);
    }

    @BindingAdapter(value = {"webViewClient", "webChromeClient"}, requireAll = false)
    public static void initWebViewClient(com.tencent.smtt.sdk.WebView webView, com.tencent.smtt.sdk.WebViewClient webViewClient, com.tencent.smtt.sdk.WebChromeClient webChromeClient){
        webView.setWebViewClient(webViewClient==null?(new com.tencent.smtt.sdk.WebViewClient()):webViewClient);
        webView.setWebChromeClient(webChromeClient==null?(new com.tencent.smtt.sdk.WebChromeClient()):webChromeClient);
    }

}
