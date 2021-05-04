package com.fjsy.architecture.utils;

import android.os.Build;
import android.os.Environment;

import com.blankj.utilcode.util.AppUtils;

import java.io.File;

public class Constants {

    /**
     * 基础url
     */
    public static final String URL_BASE = "";

    /**
     * 支付宝支付状态
     */
    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_AUTH_FLAG = 2;
    public static final String CHAT_INFO = "chatInfo";

    /**
     * 获取FileProvider
     *
     * @return
     */
    public static String getFileProviderName() {
        return AppUtils.getAppPackageName() + ".fileprovider";
    }

    public static String getPath(String path, String fileName) {
        String tempPath = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + "/" + path;
        File file = new File(tempPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return tempPath + fileName;
    }

    public static String getRecordMusicPath() {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "music" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getRecordMusicPath(String fileName) {
        String tempPath = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "music" + File.separator;
        File file = new File(tempPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return tempPath + fileName;
    }

    public static String getRecordCachePath() {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "cache" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getRecordCachePath(String fileName) {
        String tempPath = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "cache" + File.separator;
        File file = new File(tempPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return tempPath + fileName;
    }

    public static String getRecordOutputPath() {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "output";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getRecordOutputPath(String fileName) {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "output" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path + fileName;
    }

    public static String getRecordSaveVideoPath() {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "saveVideo";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getRecordSaveVideoPath(String fileName) {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "record" + File.separator + "saveVideo" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path + fileName;
    }

    public static String getSaveImagePath() {
        String path =
                Environment
                        .getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DCIM).getPath() + File.separator + "tj_clan/image";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getSaveImagePath(String fileName) {
        String path = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).getPath() + File.separator + "tj_clan/image" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path + fileName;
    }



}
