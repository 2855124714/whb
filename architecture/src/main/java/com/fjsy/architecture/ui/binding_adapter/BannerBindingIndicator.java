package com.fjsy.architecture.ui.binding_adapter;

import android.util.Log;

import com.youth.banner.Banner;
import com.youth.banner.indicator.BaseIndicator;

public class BannerBindingIndicator{
    public BaseIndicator indicator;

    public Banner banner;

    public BannerBindingIndicator newInstance(){
        return new BannerBindingIndicator();
    }

    public void setIndicator(BaseIndicator indicator) {
        this.indicator = indicator;
        checkBinding();
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
        checkBinding();
    }

    public void checkBinding(){
        if (banner !=null&& indicator!=null){
            banner.setIndicator(indicator,false);
        }

    }
}