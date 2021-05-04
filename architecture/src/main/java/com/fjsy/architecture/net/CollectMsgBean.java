package com.fjsy.architecture.net;

import java.io.Serializable;

/**
 * Created  2021/3/11 17:50
 */
public class CollectMsgBean implements Serializable {
    public int MsgSize;
    public String MsgName;
    public String MsgUrl;
    public String extra;

    public CollectMsgBean(int msgSize, String msgName, String msgUrl,String MsgExtra) {
        MsgSize = msgSize;
        MsgName = msgName;
        MsgUrl = msgUrl;
        extra=MsgExtra;
    }
}
