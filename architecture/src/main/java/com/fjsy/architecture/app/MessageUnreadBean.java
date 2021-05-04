package com.fjsy.architecture.app;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

public class MessageUnreadBean extends BaseObservable implements Serializable {
    public String badge;
    public String content;
    public String time;

    public MessageUnreadBean(String badge, String content, String time) {
        this.badge = badge;
        this.content = content;
        this.time = time;
    }

    public MessageUnreadBean() {
    }

    public String getBadge() {
        notifyChange();
        return badge;
    }

    public String getContent() {
        notifyChange();
        return content;
    }

    public String getTime() {
        notifyChange();
        return time;
    }
}
