package com.fjsy.architecture.ui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.fjsy.architecture.utils.NetworkUtils;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Map;

/**
 * Created by Czm on 2020/6/18 17:28
 */
public class X5WebView extends WebView {

    public X5WebView(Context context) {
        super(context);
        initWebViewSettings();
    }

    public X5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebViewSettings();
    }

    public X5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebViewSettings();
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        initWebViewSettings();
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
        initWebViewSettings();
    }

    private void initWebViewSettings() {
        this.getView().setClickable(true);
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
//        webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setBuiltInZoomControls(false);
        webSetting.setDisplayZoomControls(false);
        webSetting.setMediaPlaybackRequiresUserGesture(false);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());

        //缓存相关
        if (getContext() != null) {
            if (NetworkUtils.isConnected()) {
                //有网络，则加载网络地址
//                webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式LOAD_CACHE_ELSE_NETWORK
                webSetting.setCacheMode(WebSettings.LOAD_NORMAL);//设置缓存模式LOAD_CACHE_ELSE_NETWORK
            } else {
                //无网络，则加载缓存路径
                webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
            String cacheDirPath = getContext().getExternalCacheDir().getAbsolutePath() + "/webcache";//缓存路径
            webSetting.setDatabasePath(cacheDirPath);//设置数据库缓存路径
            webSetting.setAppCachePath(cacheDirPath);//设置AppCaches缓存路径
            webSetting.setAppCacheEnabled(true);//开启AppCaches功能
        }

        if (getSettingsExtension() != null)
            this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension

        if (getX5WebViewExtension() != null)
            getX5WebViewExtension().setVerticalScrollBarEnabled(false); //垂直不显示滚动按钮

        // settings 的设计
//        CrashReport.setJavascriptMonitor((CrashReport.WebViewInterface) this, false);
    }

    public void releaseAll(){
        // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
        // destory()
        ViewParent parent = getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this);
        }
        stopLoading();
        // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
        getSettings().setJavaScriptEnabled(false);
        clearHistory();
        clearView();
        removeAllViews();
        destroy();
    }
}
