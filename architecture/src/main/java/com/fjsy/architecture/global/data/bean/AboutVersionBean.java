package com.fjsy.architecture.global.data.bean;

import com.fjsy.architecture.data.response.bean.BaseBean;

import java.io.Serializable;

public class AboutVersionBean extends BaseBean implements Serializable {

    public VersionBean version;
    public String download;

    public static class VersionBean {
        public String cname;
        public String name;
    }
}
