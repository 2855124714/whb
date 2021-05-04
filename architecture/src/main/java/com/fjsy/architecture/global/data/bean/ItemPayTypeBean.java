package com.fjsy.architecture.global.data.bean;

import androidx.databinding.BaseObservable;

import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.R;

public class ItemPayTypeBean extends BaseObservable {

    public String name;
    public String type;

    public boolean select;

    public int icResId;
    public String content="";//具体内容(如卡号，支付宝账户)

    public ItemPayTypeBean(String type) {
        this.type = type;
        select = false;
        if (type.equals("wxpay")){
            name = StringUtils.getString(R.string.wechat);
            icResId = R.mipmap.ic_pay_wx;
        } else if (type.equals("alipay")){
            name = StringUtils.getString(R.string.alipay);
            icResId = R.mipmap.ic_pay_alipay;
        } else if (type.equals("unionpay")){
            name = StringUtils.getString(R.string.union_bank);
            icResId = R.mipmap.ic_pay_bank;
        }
    }
}
