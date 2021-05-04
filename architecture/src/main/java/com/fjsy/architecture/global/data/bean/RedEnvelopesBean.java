package com.fjsy.architecture.global.data.bean;

import androidx.room.Entity;

import java.io.Serializable;

public class RedEnvelopesBean implements Serializable {

    //红包类型的type
    public static final int RedEnvelopes = 0x81;
    public static final int CondolenceRedEnvelopes = 0x82;

    private int type;

    private String money_amount;

    private String blessing;

    private String user_id;

    private String red_envelopes_id;

    public RedEnvelopesBean(int type, String money_amount, String blessing, String user_id, String red_envelopes_id) {
        this.type = type;
        this.money_amount = money_amount;
        this.blessing = blessing;
        this.user_id = user_id;
        this.red_envelopes_id = red_envelopes_id;
    }

    public RedEnvelopesBean() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMoney_amount() {
        return money_amount;
    }

    public void setMoney_amount(String money_amount) {
        this.money_amount = money_amount;
    }

    public String getBlessing() {
        return blessing;
    }

    public void setBlessing(String blessing) {
        this.blessing = blessing;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRed_envelopes_id() {
        return red_envelopes_id;
    }

    public void setRed_envelopes_id(String red_envelopes_id) {
        this.red_envelopes_id = red_envelopes_id;
    }
}
