package com.fjsy.architecture.global.data.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;
import java.util.List;

public class TencentPlaceSearchBean extends BaseObservable implements Serializable {

    public int status;
    public String message;
    public int count;
    public String request_id;
    public RegionBean region;
    public List<DataBean> data;

    public static class RegionBean extends BaseObservable implements Serializable {
        public String title;
    }

    public static class DataBean extends BaseObservable implements Serializable {
        public String id;
        public String title;
        public String address;
        public String tel;
        public String category;
        public int type;
        public LocationBean location;
        public double _distance;
        public AdInfoBean ad_info;
        public boolean select;

        public static class LocationBean extends BaseObservable implements Serializable {
            public double lat;
            public double lng;
        }

        public static class AdInfoBean extends BaseObservable implements Serializable {
            public String adcode;
            public String province;
            public String city;
            public String district;
        }
    }
}
