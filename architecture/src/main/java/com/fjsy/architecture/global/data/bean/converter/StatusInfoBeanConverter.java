package com.fjsy.architecture.global.data.bean.converter;

import androidx.room.TypeConverter;

import com.blankj.utilcode.util.GsonUtils;
import com.fjsy.architecture.data.response.bean.StatusInfo;

public class StatusInfoBeanConverter {

    @TypeConverter
    public String objectToString(StatusInfo statusInfo){
        return GsonUtils.toJson(statusInfo);
    }

    @TypeConverter
    public StatusInfo stringToObject(String string){
        return GsonUtils.fromJson(string,StatusInfo.class);
    }

}
