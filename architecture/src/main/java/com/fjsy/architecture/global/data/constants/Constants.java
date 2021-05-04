package com.fjsy.architecture.global.data.constants;

import android.os.Build;
import android.os.Environment;

import com.fjsy.architecture.app.BaseApp;

import java.io.File;
import java.io.IOException;

public class Constants {

    /**
     * url地址区域
     */
    //api地址
//    public static final String baseUrl = "http://tjpl.alixia.net/";  //测试服务器
    public static final String baseUrl = "http://app.fjtjkj.cn/";

    public static final String fileUpLoadUrl = baseUrl + "api/file/upload.html";
    //腾讯地址
    public static final String tencentBaseUrl = "https://apis.map.qq.com/ws/";

    /**
     * 配置信息
     */
    //app包名
    public static final String ROOT_PACKAGE = "com.fjsy.tjclan";
    public static final String TJClan = "TJClan";
    public static int defaultTime = 60;
    public static int trendsMaxSelectImg = 9;
//    public static int trendsVideoMaxDuration = 90;
    public static int trendsVideoMaxDuration = 3*60;
    //微信包名
    public static final String WeChatPkgName = "com.tencent.mm";

    /**
     * SDK key值存放
     */
    //腾讯地图
    public static final String TencentLocationKey = "EDOBZ-GKM6W-FRBRX-RT7T5-NJKH2-5BBXT";
    //微信开放平台
    public static final String WxAppID = "wx5210779f6f928b78";
    public static final String WxAppSecret = "898a329d0d9611c1d164b11f17d79e24";
    public static final String UmAPPKEY="60153a2c6a2a470e8f984a14";

    private static final String mediaSavePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + "tj_clan";

    public static String getMediaSavePath() {
        File file = new File(mediaSavePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return mediaSavePath;
    }

    public static String getVideoSavePath() {
        File file = new File(mediaSavePath + File.separator + "video");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getImageSavePath() {
        File file = new File(mediaSavePath + File.separator + "image");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
    public static String generateImageFileDir(){
        File file = new File(mediaSavePath + File.separator + "image");
        if(!file.exists()){
            file.mkdir();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getAbsolutePath();
    }
//    public static String getTimCacheSavePath() {
//        File file = new File(mediaSavePath + File.separator + "cache");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        return file.getAbsolutePath();
//    }
}
