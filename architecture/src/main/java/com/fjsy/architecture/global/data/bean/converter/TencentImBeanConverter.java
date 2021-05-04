package com.fjsy.architecture.global.data.bean.converter;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import com.blankj.utilcode.util.GsonUtils;
import com.fjsy.architecture.global.data.bean.UserBean.TencentImBean;

public class TencentImBeanConverter {

    @TypeConverter
    public String objectToString(TencentImBean tencentImBean) {
        return tencentImBean != null ? GsonUtils.toJson(tencentImBean) : "";
    }

    @TypeConverter
    public TencentImBean stringToObject(String string) {
        return !TextUtils.isEmpty(string) ? GsonUtils.fromJson(string, TencentImBean.class) : null;
    }

}