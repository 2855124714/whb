package com.fjsy.architecture.global.data.bean;

import com.fjsy.architecture.data.response.bean.ArrayBean;

import java.util.List;

public class ArticleLoadBean extends ArrayBean {

    public List<DataBean> data;

    public static class DataBean {
        public String id;
        public String cname;
        public String create_time;
        public String image;
        public String title;
    }
}

