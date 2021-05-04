package com.fjsy.architecture.ui.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.LayoutWatermarkBinding;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.global.event.GlobalEventAction;
import com.fjsy.architecture.utils.EssFile;
import com.fjsy.architecture.utils.EventUtils;
import com.fjsy.architecture.utils.FFmpegCMDUtils;
import com.fjsy.architecture.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import io.microshow.rxffmpeg.RxFFmpegInvoke;
import okhttp3.Call;

public class DownloadService extends Service {

    /**
     * 启动下载
     */
    //文件下载地址
    public static final String DownloadUrl = "url";
    //文件名
    public static final String FileName = "name";
    //用户Id
    public static final String UserId = "userId";

    public static final ArrayList<String> urlList = new ArrayList<>();

    /**
     * 文件下载状态
     */
    //下载中
    public static final int DownLoadStateDownloading = 0x1;
    //下载完成
    public static final int DownLoadStateFinish = 0x2;
    //下载失败
    public static final int DownLoadStateFail = 0x3;

    public DownloadService() {
    }

    public static void startDownloadService(Context context, String downloadUrl, String fileName, String userId) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadUrl, downloadUrl);
        intent.putExtra(FileName, fileName);
        intent.putExtra(UserId, userId);
        context.startService(intent);
        urlList.add(downloadUrl);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String downloadUrl = intent.getStringExtra(DownloadUrl);
        String fileName = intent.getStringExtra(FileName);
        String userId = intent.getStringExtra(UserId);
        new DownloadFile(downloadUrl, fileName, userId);
        return super.onStartCommand(intent, flags, startId);
    }


    public class DownloadFile {

        public String downloadUrl = "";
        public String fileName = "";
        public String userId = "";

        public DownloadFile(String downloadUrl, String fileName, String userId) {
            this.downloadUrl = downloadUrl;
            this.fileName = fileName;
            this.userId = userId;
            startDownload();
        }

        public void startDownload() {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            OkHttpUtils.get().url(downloadUrl)
                    .build()
                    .execute(new FileCallBack(Constants.getVideoSavePath()
                            , !TextUtils.isEmpty(fileName) ? fileName : downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1)) {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            EventUtils.postData(GlobalEventAction.Download, downloadUrl, DownLoadStateFail);
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            if (new EssFile(response).isVideo()) {
                                File watermarkFile = new File(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "watermark" + userId + ".png");
                                if (!watermarkFile.exists()) {
                                    LayoutWatermarkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(Utils.getApp()), R.layout.layout_watermark, null, false);
                                    binding.setUserId(userId);
                                    binding.executePendingBindings();
                                    ImageUtils.save(ImageUtils.view2Bitmap(binding.getRoot()), watermarkFile, Bitmap.CompressFormat.PNG);
                                }
                                ThreadUtils.getCachedPool().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        String videoSavePath = Constants.getVideoSavePath() + File.separator + userId + (downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1));
//                                        String cmd = "ffmpeg -y -i " + response.getAbsolutePath() + " -i " + watermarkFile.getAbsolutePath() + " -filter_complex [0:v]scale=iw:ih[outv0];[1:0]scale=0.0:0.0[outv1];[outv0][outv1]overlay=0:0 -preset superfast " + videoSavePath;
//                                        RxFFmpegInvoke.getInstance().runCommand(cmd.split(" "), new RxFFmpegInvoke.IFFmpegListener() {
                                        RxFFmpegInvoke.getInstance().runCommand(FFmpegCMDUtils.addWaterMark(response.getAbsolutePath(), watermarkFile.getAbsolutePath(), videoSavePath), new RxFFmpegInvoke.IFFmpegListener() {
                                            @Override
                                            public void onFinish() {
                                                EventUtils.postData(GlobalEventAction.Download, downloadUrl, DownLoadStateFinish);
                                                MediaScannerConnection.scanFile(getApplicationContext(), new String[]{videoSavePath},
                                                        new String[]{"video/*"}, new MediaScannerConnection.OnScanCompletedListener() {
                                                            @Override
                                                            public void onScanCompleted(String path, Uri uri) {

                                                            }
                                                        });
                                                response.delete();
                                                watermarkFile.delete();
                                            }

                                            @Override
                                            public void onProgress(int progress, long progressTime) {

                                            }

                                            @Override
                                            public void onCancel() {

                                            }

                                            @Override
                                            public void onError(String message) {

                                            }
                                        });
                                    }
                                });
                            } else {
                                EventUtils.postData(GlobalEventAction.Download, downloadUrl, DownLoadStateFinish);
                            }
                        }

                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            EventUtils.postData(GlobalEventAction.Download, downloadUrl, DownLoadStateDownloading, decimalFormat.format(progress * 100));
                        }

                    });
        }
    }
}