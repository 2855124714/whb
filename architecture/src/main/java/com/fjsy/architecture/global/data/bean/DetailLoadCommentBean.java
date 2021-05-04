package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailLoadCommentBean extends ArrayBean {

    public List<DataBean> data;

    public static class DataBean extends BaseObservable implements Serializable {
        public String id;
        public UserBean user;
        public String content;
        public String zan_num;
        public String is_zan;
        public String create_time;
        public List<FollowBean> follow;

        public void changeZanState() {
            if (TextUtils.isEmpty(zan_num)) {
                zan_num = "0";
            }
            long l = Long.parseLong(zan_num);
            if (isDoLike()) {
                l -= 1;
            } else {
                l += 1;
            }
            changeDoLike(!isDoLike());
            zan_num = String.valueOf(l);
            notifyChange();
        }

        public void changeDoLike(boolean state){
            if (state){
                zan_num = "1";
            } else {
                zan_num = "0";
            }
        }

        public Boolean isDoLike(){
            if (TextUtils.isEmpty(is_zan)) {
                zan_num = "0";
            }
            notifyChange();
            return zan_num.equals("1");
        }

        public static class FollowBean {
            public UserBean user;
            public String id;
            public String content;
            public String zan_num;
            public String create_time;
            public UserBean reply_user;

        }
    }

    public static class UserBean extends BaseObservable implements Serializable {
        public String id;
        public String truename;
        public String avatar_url;
        public String nickname;

        public String getTruename(){
            notifyChange();
            return !TextUtils.isEmpty(truename)?truename:"";
        }
    }
}
