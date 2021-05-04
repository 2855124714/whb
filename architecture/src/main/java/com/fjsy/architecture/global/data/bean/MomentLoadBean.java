package com.fjsy.architecture.global.data.bean;

import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;

import com.blankj.utilcode.util.LogUtils;
import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.location.LocationInfoManage;
import com.tencent.map.geolocation.TencentLocationUtils;

import java.io.Serializable;
import java.util.List;

public class MomentLoadBean extends ArrayBean implements Serializable {

    public List<DataBean> data;

    public static class DataBean extends BaseObservable implements Serializable {
        public String id;
        public UserBean user;
        public String title;
        public String content;
//        public LabelBean label;
        public String zan_num;
        public String is_zan;
        public String is_collect;
        public String label_cname;
        public String label_id;
        public LocationBean location;
        public String create_time;
        public List<String> label;
        public List<String> photos;
        public List<ZansBean> zans;
        public List<CommentsBean> comments;
        public String thumbnail;

        public boolean getIs_Zan() {
            if (TextUtils.isEmpty(is_zan)) {
                is_zan = "0";
            }
            notifyChange();
            return "1".equals(is_zan);
        }

        public void setIs_zan(boolean is_zan_state) {
            if (is_zan_state) {
                is_zan = "1";
                zan_num = Integer.parseInt(zan_num) + 1 + "";
            } else {
                is_zan = "0";
                zan_num = Integer.parseInt(zan_num) - 1 + "";
            }
            notifyChange();
        }

        public String getZan_num() {
            notifyChange();
            return zan_num;
        }

        public String getCommentsSize() {
            notifyChange();
            if (null == comments || comments.size() == 0) {
                return String.valueOf(0);
            } else {
                return String.valueOf(comments.size());
            }
        }

        public boolean getIs_collect() {
            if (TextUtils.isEmpty(is_collect)) {
                is_collect = "0";
            }
            notifyChange();
            return "1".equals(is_collect);
        }

        public void setIs_collect(boolean is_collect_state) {
            if (is_collect_state) {
                is_collect = "1";
//                zan_num = Integer.parseInt(zan_num) + 1 + "";
            } else {
                is_collect = "0";
//                zan_num = Integer.parseInt(zan_num) - 1 + "";
            }
            notifyChange();
        }
//        public static class LabelBean implements Serializable {
//            public String id;
//            public String cname;
//        }

        public static class UserBean extends BaseObservable implements Serializable {
            public String id;
            public String truename;
            public String nickname;
            public String avatar_url;
        }

        public boolean hasLocation() {
            notifyChange();
            return location != null;
        }

        public static class LocationBean extends BaseObservable implements Serializable {

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
//                if (LocationInfoManage.getInstance().locationInfoLiveData.getValue()!=null){
//                    int distanceBetween = (int)TencentLocationUtils.distanceBetween(Double.valueOf(latitude), Double.valueOf(longitude)
//                            , LocationInfoManage.getInstance().locationInfoLiveData.getValue().longitude
//                            , LocationInfoManage.getInstance().locationInfoLiveData.getValue().longitude);
//                    showPosition = showPosition + "  距离您"+ distanceBetween + "米";
//                }
                notifyChange();
                return showPosition;
            }
        }

        public static class ZansBean implements Serializable {
            public UserBean user;

            public static class UserBean implements Serializable {
                public String id;
                public String truename;
                public String nickname;
            }
        }

        public static class CommentsBean implements Serializable {
            public UserBean user;
            public String content;
            public String id;
            public UserBean reply_user;
        }
    }
}
