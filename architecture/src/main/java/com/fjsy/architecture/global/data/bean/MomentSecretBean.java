package com.fjsy.architecture.global.data.bean;

import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.R;

import java.io.Serializable;

public class MomentSecretBean implements Serializable {

    public String secret;

    public String secretName;
    public String secretContent;
    public boolean select;

    public MomentSecretBean(String secret, String secretName, String secretContent, boolean select) {
        this.secret = secret;
        this.secretName = secretName;
        this.secretContent = secretContent;
        this.select = select;
    }

    public MomentSecretBean() {
    }

    /**
     * 私密状态 0保密 1公开 2亲友
     *
     * @return 私密状态
     */
    public String getSecretType() {
        if (secret.equals("0")) {
            return StringUtils.getString(R.string.trends_secrecy);
        } else if (secret.equals("1")){
            return StringUtils.getString(R.string.trends_open);
        } else if (secret.equals("2")) {
            return StringUtils.getString(R.string.trends_relatives_and_friends);
        } else {
            return "";
        }
    }

}
