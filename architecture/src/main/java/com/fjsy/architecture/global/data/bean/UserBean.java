package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.data.response.bean.BaseBean;

import java.io.Serializable;

//@Entity(tableName = "user" //表名
//)
//@TypeConverters({StatusInfoBeanConverter.class, TencentImBeanConverter.class})
public class UserBean extends BaseBean implements Serializable {
    //PrimaryKey 必须要有,且不为空,autoGenerate 主键的值是否由Room自动生成,默认false
//    @PrimaryKey(autoGenerate = false)
//    @NonNull
    public String id;

    public String avatar_url;
    public String truename;
    public String nickname;
    public int sex;
    public String age;
    public String region;
    public String sign;
    public String mobile;
    public String clan_id;
    public String token;
    public TencentImBean tencent_im;

    public ThirdBean third;

    public String family_member_id;//家谱id，可判断是否有家谱
    public String clan_member_id;//族谱id，可判断是否有族谱

    public boolean paypwd_set ;//是否设置支付密码

    public String tel_code;//手机号码区域号


    public String family_status;
    public FamilyCnfBean family_cnf;

    public String getStrSex() {
        notifyChange();
        if (sex == 1) {
            return StringUtils.getString(R.string.sex_male);
        } else if (sex == 2) {
            return StringUtils.getString(R.string.sex_female);
        } else {
            return StringUtils.getString(R.string.sex_secrecy);
        }
    }

    public void setStrSex(String s) {
        notifyChange();
        if (s.equals(StringUtils.getString(R.string.sex_male))) {
            sex = 1;
        } else if (s.equals(StringUtils.getString(R.string.sex_female))) {
            sex = 2;
        } else {
            sex = 0;
        }
    }

    public static int getStrSex(String s) {
        if (s.equals(StringUtils.getString(R.string.sex_male))) {
            return 1;
        } else if (s.equals(StringUtils.getString(R.string.sex_female))) {
            return 2;
        } else {
            return 0;
        }
    }

    public String getId() {
        notifyChange();
        return !TextUtils.isEmpty(id) ? id : "0";
    }

    public String getNickname() {
        notifyChange();
        return !TextUtils.isEmpty(nickname) ? nickname : "";
    }

    public String getTruename() {
        notifyChange();
        return !TextUtils.isEmpty(truename) ? truename : "";
    }

    public String getAvatar_url() {
        notifyChange();
        return !TextUtils.isEmpty(avatar_url) ? avatar_url : "";
    }

    public String getSign() {
        notifyChange();
        return !TextUtils.isEmpty(sign) ? sign : "";
    }

    public int getTencentImBeanAppId() {
        notifyChange();
        if (tencent_im == null) {
            return 0;
        }
        return !TextUtils.isEmpty(tencent_im.appid) ? Integer.valueOf(tencent_im.appid) : 0;
    }

    public String getTencentImBeanSign() {
        notifyChange();
        if (tencent_im == null) {
            return "";
        }
        return !TextUtils.isEmpty(tencent_im.sign) ? tencent_im.sign : "";
    }

    public static class FamilyCnfBean implements Serializable {
        public String master_wap_url;
        public String wap_url;

        public String member_url;
        public String clan_url;
        public String family_url;
    }

    public class TencentImBean implements Serializable {
        public String appid;
        public String sign;
    }

    public class ThirdBean implements Serializable {
        public boolean weixin;//是否绑定微信
    }

}
