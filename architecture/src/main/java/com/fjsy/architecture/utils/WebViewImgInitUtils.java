package com.fjsy.architecture.utils;

import android.webkit.WebView;

/**
 * Created by Czm on 2020/4/15 16:57
 */
public class WebViewImgInitUtils {

    public static void setImgInit(WebView wv_html){
        addImageClickListener(wv_html);
        imgReset(wv_html);
    };

    // 注入js函数监听
    public static void addImageClickListener(WebView wv_html) {
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        String jsCode = "javascript:(function(){" +
                "var imgs=document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<imgs.length;i++){" +
                "window.jsCallJavaObj.getImgs(imgs[i].src);" +
                "imgs[i].onclick=function(){" +
                "window.jsCallJavaObj.showBigImg(this.src);" +
//                "var img = imgs[i];   " +
//                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}}})()";
        wv_html.loadUrl(jsCode);

//        String jsPCode = "javascript:(function(){" +
//                "var pArr=document.getElementsByTagName(\"p\");" +
//                "for(var i=0;i<pArr.length;i++){" +
//                "var p = pArr[i];" +
//                "p.style.line-height = '2000px';" +
//                "}}})()";
//        mWebView.loadUrl(jsPCode);

        wv_html.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('div'); " +
                "var dv = objs[0]" +
                "dv.style.maxWidth = '100%'; " +//img.style.height = 'auto';" +
                "})()");
    }

    public static void imgReset(WebView wv_html) {
        wv_html.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];" +
                "img.style.maxWidth = '100%'; img.style.height = 'auto';" +
                "}" +
                "})()");
    }

    public static void getAllImgs(WebView wv_html){
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        String jsCode = "javascript:(function(){" +
                "var imgs=document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<imgs.length;i++){" +
                "window.jsCallJavaObj.getImgs(imgs[i].src);" +
                "}})()";
        wv_html.loadUrl(jsCode);
    }


}
