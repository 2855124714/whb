package com.fjsy.architecture.global.data.bean;

public class UpLoadBean {
    public int code;
    public String msg;
    public DataBean data;
    public int count;
    public int time;

    public static class DataBean {
        public String name;
        public String path;
        public String url;
        public String id;
        public String localPath;
        public int localIndex;
    }
}
