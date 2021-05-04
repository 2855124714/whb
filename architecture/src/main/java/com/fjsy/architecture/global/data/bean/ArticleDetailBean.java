package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import com.fjsy.architecture.data.response.bean.BaseBean;
import com.fjsy.architecture.global.data.bean.MomentDetailBean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArticleDetailBean extends BaseBean implements Serializable {
    public String id;
    public String name;
    public String cname;
    public String content;
    public String title;

    public UserBean user;
    public String create_time;

    public String is_zan;
    public String is_collect;
    public String comment_num;

    public List<String> label;

//    public static class LabelBean implements Serializable {
//        public String id;
//        public String cname;
//    }

    public Boolean isDoLike() {
        if (TextUtils.isEmpty(is_zan)) {
            is_zan = "0";
        }
        notifyChange();
        return is_zan.equals("1");
    }

    public Boolean isCollect() {
        if (TextUtils.isEmpty(is_collect)) {
            is_collect = "0";
        }
        notifyChange();
        return is_collect.equals("1");
    }

    public void setDoLike(boolean status) {
        if (status) {
            is_zan = "1";
        } else {
            is_zan = "0";
        }
        notifyChange();
    }

    public void setCollect(boolean status) {
        if (status) {
            is_collect = "1";
        } else {
            is_collect = "0";
        }
        notifyChange();
    }

    public void addCommentNum() {
        long l = Long.parseLong(comment_num);
        l += 1;
        comment_num = String.valueOf(l);
        notifyChange();
    }

    public static class UserBean {
        public String id;
        public String truename;
    }
}
