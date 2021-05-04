package com.fjsy.architecture.global.data.bean;

import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.data.response.bean.ArrayBean;

import java.util.List;

public class PayChannelLoadBean extends ArrayBean {

    public List<DataBean> data;

    public static class DataBean {
        public String id;
        public String name;
        public String cname;
        public boolean select;


        public int icResId;

        public int getIcResId() {
            if (name.equals("wxpay")){
                return icResId = R.mipmap.ic_pay_wx;
            } else if (name.equals("alipay")){
                return icResId = R.mipmap.ic_pay_alipay;
            } else if (name.equals("unionpay")){
                return icResId = R.mipmap.ic_pay_bank;
            } else {
                return 0;
            }
        }
    }
}
