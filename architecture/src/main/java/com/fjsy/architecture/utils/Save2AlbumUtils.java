package com.fjsy.architecture.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.R;

import java.io.File;

public class Save2AlbumUtils {

    /**
     * 保存图片
     *
     * @param context
     * @param file
     */
    public static void saveImage(Context context, File file) {
        ContentResolver localContentResolver = context.getContentResolver();
        ContentValues localContentValues = getImageContentValues(context, file, System.currentTimeMillis());
        localContentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValues);

        Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        final Uri localUri = Uri.fromFile(file);
        localIntent.setData(localUri);
        context.sendBroadcast(localIntent);
    }

    public static ContentValues getImageContentValues(Context paramContext, File paramFile, long paramLong) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("title", paramFile.getName());
        localContentValues.put("_display_name", paramFile.getName());
        localContentValues.put("mime_type", "image/jpeg");
        localContentValues.put("datetaken", Long.valueOf(paramLong));
        localContentValues.put("date_modified", Long.valueOf(paramLong));
        localContentValues.put("date_added", Long.valueOf(paramLong));
        localContentValues.put("orientation", Integer.valueOf(0));
        localContentValues.put("_data", paramFile.getAbsolutePath());
        localContentValues.put("_size", Long.valueOf(paramFile.length()));
        return localContentValues;
    }

    /**
     * 保存视频
     *
     * @param context
     * @param file
     */
    public static void saveVideo(Context context, File file) {
        //是否添加到相册
        ContentResolver localContentResolver = context.getContentResolver();
        ContentValues localContentValues = getVideoContentValues(context, file, System.currentTimeMillis());
        Uri localUri = localContentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, localContentValues);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri));
    }

    public static ContentValues getVideoContentValues(Context paramContext, File paramFile, long paramLong) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("title", paramFile.getName());
        localContentValues.put("_display_name", paramFile.getName());
        localContentValues.put("mime_type", "video/mp4");
        localContentValues.put("datetaken", Long.valueOf(paramLong));
        localContentValues.put("date_modified", Long.valueOf(paramLong));
        localContentValues.put("date_added", Long.valueOf(paramLong));
        localContentValues.put("_data", paramFile.getAbsolutePath());
        localContentValues.put("_size", Long.valueOf(paramFile.length()));
        return localContentValues;
    }

    /**
     * @param filePath 保存到本地的文件路径
     * @param isVideo  是否是视频
     */
    public static void scanFile(Context context, String filePath, boolean isVideo) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            File file = new File(filePath);
//
//            LogUtils.e("fails");
//            try {
//                if (isVideo) {
////                        MediaStore.Images.Media.insertImage(BaseApp.getContext().getContentResolver(),
////                                file.getAbsolutePath(), file.getName(), null);
//
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            FileUtils.insertVideo(filePath);
////                        }
////                    });
//
//                } else {
//
//                    MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                            file.getAbsolutePath(), file.getName(), null);
////                    FileUtils.insertImage(filePath);
//                }
//                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            MediaScannerConnection mMediaScannerConnection = null;
//            MediaScannerConnection finalMMediaScannerConnection = mMediaScannerConnection;
//            mMediaScannerConnection = new MediaScannerConnection(context, new MediaScannerConnection.MediaScannerConnectionClient() {
//                @Override
//                public void onMediaScannerConnected() {
//
//                }
//
//                @Override
//                public void onScanCompleted(String path, Uri uri) {
//                    finalMMediaScannerConnection.disconnect();
//                }
//            });
//            if (isVideo) {
//                //主动扫描视频
//                mMediaScannerConnection.scanFile(filePath, "video/*");
//            } else {
//                //主动扫描图片
//                mMediaScannerConnection.scanFile(filePath, "image/*");
//            }
//        }

        MediaScannerConnection mMediaScannerConnection = null;
        MediaScannerConnection finalMMediaScannerConnection = mMediaScannerConnection;
        mMediaScannerConnection = new MediaScannerConnection(context, new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {

            }

            @Override
            public void onScanCompleted(String path, Uri uri) {
                if (finalMMediaScannerConnection != null)
                    finalMMediaScannerConnection.disconnect();
            }
        });
        if (isVideo) {
            //主动扫描视频
            mMediaScannerConnection.scanFile(filePath, "video/*");
        } else {
            //主动扫描图片
            mMediaScannerConnection.scanFile(filePath, "image/*");
        }

    }

}
