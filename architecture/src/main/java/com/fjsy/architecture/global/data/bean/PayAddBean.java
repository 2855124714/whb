package com.fjsy.architecture.global.data.bean;

import com.fjsy.architecture.data.response.bean.BaseBean;
import com.google.gson.annotations.SerializedName;

public class PayAddBean extends BaseBean {

    public String appid;
    public String partnerid;
    public String prepayid;
    @SerializedName("package")
    public String packageX;
    public String noncestr;
    public String timestamp;
    public String sign;
    public String no;
}
