package com.fjsy.architecture.global.wx;

import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.utils.Utils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXAPI {

    private static IWXAPI api;


    public static IWXAPI getWxApi(){
        if (api==null){
            api = WXAPIFactory.createWXAPI(Utils.getApp(), Constants.WxAppID);
            api.registerApp(Constants.WxAppID);
        }
        return api;
    }



}
