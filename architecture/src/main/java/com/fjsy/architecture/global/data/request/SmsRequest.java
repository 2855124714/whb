package com.fjsy.architecture.global.data.request;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.data.response.GlobalBaseRepository;

import java.util.HashMap;

import io.reactivex.Observable;

public class SmsRequest {


    /**
     * 发送短信
     *
     * @param mobile 手机号
     */
    public Observable<ArrayBean> sendsms(String mobile) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        return GlobalBaseRepository.getInstance().sendsms(params);
    }

    /**
     * 短信验证
     *
     * @param mobile  手机号
     * @param smscode 短信验证码
     */
    public Observable<ArrayBean> checksms(String mobile, String smscode) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("smscode", smscode);
        return GlobalBaseRepository.getInstance().checksms(params);
    }



}
