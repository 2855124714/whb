package com.fjsy.architecture.global.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.global.download.DownLoadHelper;
import com.fjsy.architecture.global.download.DownloadListener;
import com.fjsy.architecture.utils.Constants;

import java.io.File;
import java.io.IOException;


public class AppUpdateService extends IntentService {

    public AppUpdateService() {
        super("AppUpdateService");
    }

    private String downLoadUrl;
    private final String apkPath = Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    private String apkName = "tjClan" + TimeUtils.getNowString() + ".apk";
    private long mLastTime = 0;
    private final int notificationId = 88888888;

    public static final String DownloadUrl = "downloadUrl";

    public static void startAppUpdateService(Context context,String downloadUrl){
        Intent intent = new Intent(context, AppUpdateService.class);
        intent.putExtra(DownloadUrl,downloadUrl);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            downLoadUrl = intent.getStringExtra(DownloadUrl);
            Log.d("downLoadApk", "downLoadUrl : " + downLoadUrl);
            File file = new File(apkPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            downLoadApk();
        }
    }

    private void downLoadApk() {
        DownLoadHelper.getInstance().downLoadFile(downLoadUrl, apkPath, apkName);
        DownLoadHelper.getInstance().addDownLoadListener(new DownloadListener() {
            @Override
            public void onStartDownload(String tag) {

            }

            @Override
            public void onProgress(String tag, int progress) {
                Log.d("downLoadApk", "onProgress : " + tag + " - " + progress);
                if (System.currentTimeMillis() - mLastTime >= 1000 || progress == 100) {
                    mLastTime = System.currentTimeMillis();
                    NotificationUtils.notify(notificationId, new Utils.Consumer<NotificationCompat.Builder>() {
                        @Override
                        public void accept(NotificationCompat.Builder builder) {
                            builder.setSmallIcon(R.mipmap.ic_logo)
                                    .setContentTitle("???????????????????????????")
                                    .setContentText("app?????????...")
                                    .setProgress(100, progress, false)
                                    .setAutoCancel(true)
                                    .setOnlyAlertOnce(true);
                        }
                    });
                }

            }

            @Override
            public void onFinishDownload(String tag, File file) {
                Log.d("downLoadApk", "onFinishDownload : " + tag + " - " + file.getName());
                ToastUtils.showShort(R.string.download_complete);
                NotificationUtils.notify(notificationId, builder -> builder.setSmallIcon(R.mipmap.ic_logo)
                        .setContentTitle("???????????????????????????")
                        .setContentText("app????????????")
                        .setAutoCancel(true)
                        .setOnlyAlertOnce(true)
                        .setContentIntent(getPendingIntent(file))
                        .setFullScreenIntent(getPendingIntent(file), true));

            }

            @Override
            public void onFail(String tag, String msg) {
                Log.d("downLoadApk", "onFail : " + tag + " - " + msg);
                ToastUtils.showShort(msg);
            }
        });
    }

    /**
     * ??????Apk???Intent
     *
     * @return
     */
    private PendingIntent getPendingIntent(File saveFile) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        String[] command = {"chmod", "777", saveFile.getPath()};
        ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //?????????????????????7.0??????
            Uri apkUri = FileProvider.getUriForFile(getApplicationContext(),
                    Constants.getFileProviderName(), saveFile);//???AndroidManifest??????android:authorities???
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION); //???????????????????????????????????????????????????Uri??????????????????
        } else {
            install.setDataAndType(Uri.fromFile(saveFile), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(AppUpdateService.this, 0, install, PendingIntent.FLAG_UPDATE_CURRENT);
        startActivity(install);
        return pendingIntent;
    }
}
