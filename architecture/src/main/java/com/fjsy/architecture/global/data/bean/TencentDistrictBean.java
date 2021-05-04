package com.fjsy.architecture.global.data.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;
import java.util.List;

public class TencentDistrictBean extends BaseObservable implements Serializable {

    public int status;
    public String message;
    public String data_version;
    public List<List<ResultBean>> result;

    public static class ResultBean {
        public String id;
        public String fullname;
        public LocationBean location;
        public boolean isSelect;

        public static class LocationBean {
            public double lat;
            public double lng;
        }
    }
}
