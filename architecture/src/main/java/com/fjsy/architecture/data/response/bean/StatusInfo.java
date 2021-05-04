package com.fjsy.architecture.data.response.bean;

import androidx.room.Ignore;

import java.io.Serializable;

public class StatusInfo implements Serializable {

    public static final int STATUS_SUCCESS = 0;
    public static final int NOT_TOKEN = 1001;
    public static final int OTHER_MESSAGE = 2;
    public static final int Error = 1;

    public int statusCode;

    public String statusMessage;

    public StatusInfo() {

    }

    @Ignore
    public StatusInfo(int statusCode) {
        this.statusCode = statusCode;
    }

    @Ignore
    public StatusInfo(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public boolean isSuccessful() {
        return statusCode == STATUS_SUCCESS;
    }

    public boolean isOther() {
        return statusCode != STATUS_SUCCESS;
    }

    public boolean isUnlogin() {
        return statusCode == NOT_TOKEN;
    }

    public boolean isHint2User() {
        return statusCode == OTHER_MESSAGE;
    }

    @Override
    public String toString() {
        return "StatusInfo{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
