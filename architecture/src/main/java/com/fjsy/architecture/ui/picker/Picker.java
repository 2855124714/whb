package com.fjsy.architecture.ui.picker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.ui.glide.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.SdkVersionUtils;

import java.io.File;
import java.util.List;

public class Picker {

    public static final int Image = 0x100;
    public static final int Video = 0x200;
    public static final int Camera = 0x300;

    /**
     * 打开系统相机
     *
     * @param activity
     * @param requestCode
     */
    public static void openSystemCamera(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isUseCustomCamera(false)// 是否使用自定义相机
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 打开系统相机
     *
     * @param fragment
     * @param requestCode
     */
    public static void openSystemCamera(Fragment fragment, int requestCode) {
        PictureSelector.create(fragment)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isUseCustomCamera(false)// 是否使用自定义相机
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .isUseCustomCamera(true)// 是否使用自定义相机
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 打开相机
     * @param activity
     * @param requestCode
     */
    public static void openCamera(Activity activity,int maxSelectImgNum,int maxSelectVideoNum, int requestCode){
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofAll())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .maxSelectNum(maxSelectImgNum)// 最大图片选择数量
                .isUseCustomCamera(true)// 是否使用自定义相机
                .setOutputCameraPath(Constants.getImageSavePath())// 自定义相机输出目录
                .minSelectNum(1)// 最小选择数量
                .maxVideoSelectNum(maxSelectVideoNum)
                //.querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高，默认为true
                .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高，默认false
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                //.cameraFileName(System.currentTimeMillis() + ".jpg")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用
                //.renameCompressFile(System.currentTimeMillis() + ".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                .isPreviewImage(true)// 是否可预览图片
                .isPreviewVideo(true)// 是否可预览视频
                .isEnablePreviewAudio(false) // 是否可播放音频
//                    .isCamera(true)// 是否显示拍照按钮
//                    .isEnableCrop(true)// 是否裁剪
//                    .basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                //.setCircleDimmedColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪背景色值
                //.setCircleDimmedBorderColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪边框色值
                //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                .isOpenClickSound(true)// 是否开启点击声音
                .cutOutQuality(80)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .videoMaxSecond(Constants.trendsVideoMaxDuration)
                .videoMinSecond(3)
                .videoQuality(1)
                .recordVideoMinSecond(3)
                .recordVideoSecond(Constants.trendsVideoMaxDuration)
                .forResult(requestCode);
    }

    /**
     * 打开相机
     * @param fragment
     * @param requestCode
     */
    public static void openCamera(Fragment fragment,int maxSelectImgNum,int maxSelectVideoNum, int requestCode){
        PictureSelector.create(fragment)
                .openCamera(PictureMimeType.ofAll())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .maxSelectNum(maxSelectImgNum)// 最大图片选择数量
                .isUseCustomCamera(true)// 是否使用自定义相机
                .setOutputCameraPath(Constants.getImageSavePath())// 自定义相机输出目录
                .minSelectNum(1)// 最小选择数量
                .maxVideoSelectNum(maxSelectVideoNum)
                //.querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高，默认为true
                .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高，默认false
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                //.cameraFileName(System.currentTimeMillis() + ".jpg")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用
                //.renameCompressFile(System.currentTimeMillis() + ".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                .isPreviewImage(true)// 是否可预览图片
                .isPreviewVideo(true)// 是否可预览视频
                .isEnablePreviewAudio(false) // 是否可播放音频
//                    .isCamera(true)// 是否显示拍照按钮
//                    .isEnableCrop(true)// 是否裁剪
//                    .basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                //.setCircleDimmedColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪背景色值
                //.setCircleDimmedBorderColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪边框色值
                //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                .isOpenClickSound(true)// 是否开启点击声音
                .cutOutQuality(80)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .videoMaxSecond(Constants.trendsVideoMaxDuration)
                .videoMinSecond(3)
                .videoQuality(1)
                .recordVideoMinSecond(3)
                .recordVideoSecond(Constants.trendsVideoMaxDuration)
                .forResult(requestCode);
    }

    /**
     * 单选图片
     *
     * @param activity
     * @param requestCode
     */
    public static void pickSingle(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofAll())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 单选图片
     *
     * @param fragment
     * @param requestCode
     */
    public static void pickSingle(Fragment fragment, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofAll())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 单选图片
     *
     * @param activity
     * @param requestCode
     */
    public static void pickSingleImage(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 单选图片
     *
     * @param fragment
     * @param requestCode
     */
    public static void pickSingleImage(Fragment fragment, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 单选视频
     *
     * @param activity
     * @param requestCode
     */
    public static void pickSingleVideo(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 单选视频
     *
     * @param fragment
     * @param requestCode
     */
    public static void pickSingleVideo(Fragment fragment, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 单选图片
     *
     * @param activity
     * @param listener
     */
    public static void pickSingle(Activity activity, OnResultCallbackListener<LocalMedia> listener) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofAll())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(listener);
    }

    /**
     * 单选图片
     *
     * @param fragment
     * @param listener
     */
    public static void pickSingle(Fragment fragment, OnResultCallbackListener<LocalMedia> listener) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofAll())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(listener);
    }

    /**
     * 单选图片
     *
     * @param activity
     * @param listener
     */
    public static void pickSingleImage(Activity activity, OnResultCallbackListener<LocalMedia> listener) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(listener);
    }

    /**
     * 单选图片
     *
     * @param fragment
     * @param listener
     */
    public static void pickSingleImage(Fragment fragment, OnResultCallbackListener<LocalMedia> listener) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(listener);
    }

    /**
     * 单选视频
     *
     * @param activity
     * @param listener
     */
    public static void pickSingleVideo(Activity activity, OnResultCallbackListener<LocalMedia> listener) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(listener);
    }

    /**
     * 单选视频
     *
     * @param fragment
     * @param listener
     */
    public static void pickSingleVideo(Fragment fragment, OnResultCallbackListener<LocalMedia> listener) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(listener);
    }

    /**
     * 多选图片
     *
     * @param activity
     * @param requestCode
     */
    public static void pickImage(Activity activity, int maxSelectNum, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxSelectNum)
                .isCamera(false)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 多选图片
     *
     * @param fragment
     * @param requestCode
     */
    public static void pickImage(Fragment fragment, int maxSelectNum, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxSelectNum)
                .isCamera(false)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 选择
     *
     * @param fragment
     * @param requestCode
     */
    public static void pick(Fragment fragment, int maxSelectImgNum,int maxSelectVideoNum, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofAll())
                .minSelectNum(1)
                .maxSelectNum(maxSelectImgNum)
                .maxVideoSelectNum(maxSelectVideoNum)
                .videoMinSecond(3)
                .videoMaxSecond(Constants.trendsVideoMaxDuration)
                .isCamera(false)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }

    /**
     * 过滤音频
     * @param fragment
     * @param maxSelectImgNum
     * @param maxSelectVideoNum
     * @param requestCode
     */
    public static void pickChat(Fragment fragment, int maxSelectImgNum,int maxSelectVideoNum, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofAll())
                .minSelectNum(1)
                .maxSelectNum(maxSelectImgNum)
                .maxVideoSelectNum(maxSelectVideoNum)
                .isCamera(false)
                .isEnablePreviewAudio(false)
                .isOpenClickSound(false)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }
    /**
     * 选择
     *
     * @param activity
     * @param requestCode
     */
    public static void pick(Activity activity, int maxSelectImgNum,int maxSelectVideoNum, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofAll())
                .minSelectNum(1)
                .maxSelectNum(maxSelectImgNum)
                .maxVideoSelectNum(maxSelectVideoNum)
                .videoMinSecond(3)
                .videoMaxSecond(Constants.trendsVideoMaxDuration)
                .recordVideoSecond(Constants.trendsVideoMaxDuration)
                .recordVideoMinSecond(Constants.trendsVideoMaxDuration)
                .isCamera(false)
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .videoQuality(1)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);
    }



}
