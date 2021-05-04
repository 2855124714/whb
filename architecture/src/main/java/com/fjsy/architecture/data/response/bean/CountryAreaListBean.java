package com.fjsy.architecture.data.response.bean;

import androidx.databinding.BaseObservable;

import com.fjsy.architecture.data.response.bean.ArrayBean;

import java.io.Serializable;
import java.util.List;

public class CountryAreaListBean extends ArrayBean implements Serializable {
    public List<CountryAreaBean> data;
//    {
//        "id": 1,
//            "cname": "中国",
//            "name": "China",
//            "code": "86"
//    }
    public static class CountryAreaBean extends BaseObservable implements Serializable {
        public String cname;
        public String code;
        public String name;
        public int id;
    }

}
