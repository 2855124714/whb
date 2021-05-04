package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;

import com.fjsy.architecture.data.response.bean.BaseBean;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.io.Serializable;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

public class MomentDetailBean extends BaseBean {

    public String id;
    public UserBean user;
    public String title;
    public String content;
    public String create_time;
    public String zan_num;
    public String label_cname;
    public String label_id;
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
        return "1".equals(is_collect);
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

    public boolean isVideo() {
        if (photos == null)
            return false;
        if (photos.size() == 0)
            return false;
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(photos.get(0));
        int mimeType = PictureMimeType.getMimeType(type);
        if (mimeType == PictureConfig.TYPE_VIDEO) {
            notifyChange();
            return true;
        } else {
            notifyChange();
            return false;
        }
    }

    public List<String> photos;
    public LocationBean location;
    public List<ZansBean> zans;
    public List<CommentsBean> comments;

    public static class UserBean {
        public String id;
        public String truename;
        public String nickname;
        public String avatar_url;
    }

    public static class ZansBean {
        public UserBean user;

        public static class UserBean {
            public String id;
            public String truename;
            public String nickname;
        }
    }


    public static class LocationBean extends BaseObservable {

        public String longitude;
        public String latitude;
        public String region;
        public String address;

        public String showLocation() {
            String showPosition = "";
            if (!TextUtils.isEmpty(region)) {
                showPosition = showPosition + region;
            }
            if (!TextUtils.isEmpty(address)) {
                showPosition = showPosition + address;
            }
            notifyChange();
            return showPosition;
        }
    }

    public static class CommentsBean {
        public UserBean user;
        public String id;
        public String content;
        public String create_time;

        public static class UserBean {
            public String id;
            public String truename;
        }
    }
}
