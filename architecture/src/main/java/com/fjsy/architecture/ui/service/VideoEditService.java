package com.fjsy.architecture.ui.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.blankj.utilcode.util.ThreadUtils;
import com.fjsy.architecture.utils.FFmpegCMDUtils;

import java.util.concurrent.Executors;

import io.microshow.rxffmpeg.RxFFmpegInvoke;

public class VideoEditService extends Service {

    public VideoEditService() {
    }

//    public static void startVideoEditService(Context context,){
//
//    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    public void AddWaterMark(String filePath,String WaterMarkPath) {
        ThreadUtils.getCachedPool().execute(new Runnable() {
            @Override
            public void run() {
                RxFFmpegInvoke.getInstance().runCommand(FFmpegCMDUtils.addWaterMark(filePath, WaterMarkPath, filePath), new RxFFmpegInvoke.IFFmpegListener() {
                    @Override
                    public void onFinish() {

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
    }

}