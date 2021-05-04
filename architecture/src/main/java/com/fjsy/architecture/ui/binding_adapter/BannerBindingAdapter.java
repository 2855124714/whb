package com.fjsy.architecture.ui.binding_adapter;

import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.ActivityUtils;
import com.fjsy.architecture.ui.glide.GlideImageLoader;
import com.google.android.material.internal.ContextUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.BaseIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class BannerBindingAdapter {
    @BindingAdapter(value = {"bannerData", "onBannerListener","bannerImgNeedCrop"}, requireAll = false)
    public static void bannerSetConfig(Banner banner, List<String> bannerData, OnBannerListener onBannerListener, boolean bannerImgNeedCrop) {
        banner.addBannerLifecycleObserver((FragmentActivity) ActivityUtils.getActivityByContext(banner.getContext()));
        banner.setAdapter(new BannerImageAdapter<String>(bannerData) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                //图片加载自己实现
                if (bannerImgNeedCrop)
                    holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                GlideImageLoader.getInstance().load(holder.imageView, data);
            }
        }, true);
        if (onBannerListener != null)
            banner.setOnBannerListener(onBannerListener);
        banner.isAutoLoop(true);
        banner.start();
    }

    @BindingAdapter(value = {"bindingBanner"})
    public static void bindingBanner(Banner banner, BannerBindingIndicator bannerBindingIndicator) {
        if (bannerBindingIndicator != null) {
            bannerBindingIndicator.setBanner(banner);
        }
    }

    @BindingAdapter(value = {"bindingIndicator"})
    public static void bindingIndicator(BaseIndicator indicator, BannerBindingIndicator bannerBindingIndicator) {
        if (bannerBindingIndicator != null) {
            bannerBindingIndicator.setIndicator(indicator);
        }
    }

}
