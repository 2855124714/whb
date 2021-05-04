package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.data.response.bean.BaseBean;
import com.fjsy.architecture.global.data.bean.converter.StatusInfoBeanConverter;


import java.io.Serializable;

@Entity(primaryKeys = {"id","viewUid"})
public class PaperDetailBean extends BaseBean implements Serializable {

    @NonNull
    public String id;
    @Embedded(prefix = "cover_id_")
    public CoverIdBean cover_id;
    public String benison;
    public String amount;
    public int nums;
    public String uid;
    @NonNull
    public String viewUid;
    @Embedded(prefix = "typeid_")
    public TypeidBean typeid;
    @Embedded(prefix = "cateid_")
    public CateidBean cateid;
    @Embedded(prefix = "state_")
    public StateBean state;
    public int object_id;
    public int is_show;
    public int pay_time;
    public String balance;
    public String rand_price;
    public String create_time;
    public String update_time;
    public String delete_time;
    @Embedded(prefix = "user_")
    public UserBean user;
    public String receive_money;
    public int status;

    public PaperDetailBean() {
    }

    public static class CoverIdBean implements Serializable {
        public int value;
        public String text;
    }

    public static class TypeidBean implements Serializable {
        public int value;
        public String text;
    }

    public static class CateidBean implements Serializable {
        public int value;
        public String text;

        public boolean isGroup() {
            return value == 2;
        }
    }

    public static class StateBean implements Serializable {
        public int value;
        public String text;
    }

    public static class UserBean implements Serializable {
        public String uid;
        public String nickname;
        public String avatar_url;

        public boolean isSelf() {
            return uid.equals(UserManager.getInstance().getUser().getId());
        }
    }

    public int getStatus() {
        return status;
    }

    //1.可领取 2.已领取 3.领取完 4.过期
    public String getStatusText() {
        String statusText = "";
        if (status == -1) {
            statusText = "已打开";
        } else if (status == 1) {
            statusText = "可领取";
        } else if (status == 2) {
            statusText = "已领取";
        } else if (status == 3) {
            statusText = "领取完";
        } else if (status == 4) {
            statusText = "过期";
        }
        notifyChange();
        return statusText;
    }

    public String getBenison() {
        return !TextUtils.isEmpty(benison) ? benison : StringUtils.getString(R.string.congratulations_on_fortune);
    }
}
