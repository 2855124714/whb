package com.fjsy.architecture.global.data.bean;

public class CustomDataTypeBean {
    //0 自定义表情,0x81红包,0x82慰问包
    private int type;

    public CustomDataTypeBean() {
    }

    public CustomDataTypeBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
