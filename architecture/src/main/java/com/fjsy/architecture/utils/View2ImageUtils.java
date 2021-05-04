package com.fjsy.architecture.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by Czm on 2020/5/23 13:48
 */
public class View2ImageUtils {
    /**
     * View转换成Bitmap
     *
     * @param targetView     targetView
     * @param getCacheResult 转换成功回调接口
     */
    public static void getBitmapFromView(@NonNull Activity activity, View targetView, final CacheResult getCacheResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //准备一个bitmap对象，用来将copy出来的区域绘制到此对象中
            final Bitmap bitmap = Bitmap.createBitmap(targetView.getWidth(), targetView.getHeight(), Bitmap.Config.ARGB_8888);
            //获取layout的left-top顶点位置
            final int[] location = new int[2];
            targetView.getLocationInWindow(location);
            //请求转换
            PixelCopy.request(activity.getWindow(),
                    new Rect(location[0], location[1],
                            location[0] + targetView.getWidth(), location[1] + targetView.getHeight()),
                    bitmap, new PixelCopy.OnPixelCopyFinishedListener() {
                        @Override
                        public void onPixelCopyFinished(int copyResult) {
                            //如果成功
                            if (copyResult == PixelCopy.SUCCESS) {
                                //方法回调
                                getCacheResult.result(bitmap);
                            }
                        }
                    }, new Handler(Looper.getMainLooper()));
        } else {
            //开启DrawingCache
            targetView.setDrawingCacheEnabled(true);
            //构建开启DrawingCache
            targetView.buildDrawingCache();
            //获取Bitmap
            Bitmap drawingCache = targetView.getDrawingCache();
            //方法回调
            getCacheResult.result(drawingCache);
            //销毁DrawingCache
            targetView.destroyDrawingCache();
        }
    }

    public interface CacheResult{
        void result(Bitmap bitmap);
    }


}
