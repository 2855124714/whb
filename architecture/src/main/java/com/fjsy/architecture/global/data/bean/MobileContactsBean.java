package com.fjsy.architecture.global.data.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

public class MobileContactsBean extends BaseObservable implements Serializable {
    public String mobile;
    public String note;
    public String nickname;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
