package com.fjsy.architecture.data.response.bean;

import androidx.databinding.BaseObservable;
import androidx.room.Embedded;

public class BaseBean extends BaseObservable {

    @Embedded(prefix = "statusInfo_")
    public StatusInfo statusInfo = new StatusInfo();

    public String resultData;
    public int count;

    public static boolean isNull(BaseBean baseBean) {
        if (baseBean == null) {
            return true;
        }
        if (baseBean.resultData == null) {
            return true;
        }
        if ("null".equals(baseBean.resultData)) {
            return true;
        }
        return false;
    }

    public static String getNullMessage(BaseBean baseBean) {
        if (baseBean == null) {
            return "";
        } else {
            StatusInfo statusInfo = baseBean.statusInfo;
            if (statusInfo == null) {
                return "";
            } else {
                return statusInfo.statusMessage + "";
            }
        }
    }
}
