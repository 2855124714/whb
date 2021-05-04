package com.fjsy.architecture.global.data.bean;

import com.fjsy.architecture.data.response.bean.ArrayBean;

import java.util.List;

public class PayOptionLoadBean extends ArrayBean {


    public List<DataBean> data;

    public static class DataBean {
        public String id;
        public String name;
        public String cname;
    }
}
