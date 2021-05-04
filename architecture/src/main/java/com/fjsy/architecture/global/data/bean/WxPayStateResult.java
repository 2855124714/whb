package com.fjsy.architecture.global.data.bean;

/**
 * Created by Czm on 2020/4/26 10:59
 */
public class WxPayStateResult {
    private int code;
    private String msg;

    public WxPayStateResult() {
    }

    public WxPayStateResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
