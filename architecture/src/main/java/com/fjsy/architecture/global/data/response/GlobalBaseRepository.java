package com.fjsy.architecture.global.data.response;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.data.GlobalBaseService;
import com.fjsy.architecture.global.data.bean.AboutKefuBean;
import com.fjsy.architecture.global.data.bean.AboutVersionBean;
import com.fjsy.architecture.global.data.bean.ArticleDetailBean;
import com.fjsy.architecture.global.data.bean.MapGeoCoderBean;
import com.fjsy.architecture.global.data.bean.PayAddBean;
import com.fjsy.architecture.global.data.bean.PayChannelLoadBean;
import com.fjsy.architecture.global.data.bean.PayOptionLoadBean;
import com.fjsy.architecture.net.ResourceNetwork;

import java.util.HashMap;

import io.reactivex.Observable;

public class GlobalBaseRepository {

    private volatile static GlobalBaseRepository INSTANCE;
    private final GlobalBaseService globalBaseService;

    public static synchronized GlobalBaseRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (GlobalBaseRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GlobalBaseRepository();
                }
            }
        }
        return INSTANCE;
    }

    private GlobalBaseRepository() {
        globalBaseService = new ResourceNetwork().createService(GlobalBaseService.class);
    }

    //发送验证码
    public Observable<ArrayBean> sendsms(HashMap<String, Object> params) {
        return globalBaseService.sendsms(params);
    }

    //校验验证码
    public Observable<ArrayBean> checksms(HashMap<String, Object> params) {
        return globalBaseService.checksms(params);
    }

    //隐私政策与用户协议
    public Observable<ArticleDetailBean> aboutArticle(HashMap<String, Object> params) {
        return globalBaseService.aboutArticle(params);
    }

    //检查更新
    public Observable<AboutVersionBean> aboutVersion(HashMap<String, Object> params) {
        return globalBaseService.aboutVersion(params);
    }

    //客服
    public Observable<AboutKefuBean> aboutKefu(HashMap<String, Object> params) {
        return globalBaseService.aboutKefu(params);
    }

    //支付通道
    public Observable<PayChannelLoadBean> payChannelLoad(HashMap<String, Object> params) {
        return globalBaseService.payChannelLoad(params);
    }

    //支付项
    public Observable<PayOptionLoadBean> payOptionLoad(HashMap<String, Object> params) {
        return globalBaseService.payOptionLoad(params);
    }

    //支付提交
    public Observable<PayAddBean> payAdd(HashMap<String, Object> params) {
        return globalBaseService.payAdd(params);
    }

    //地址转换为经纬度
    public Observable<MapGeoCoderBean> mapGeoCoder(HashMap<String, Object> params) {
        return globalBaseService.mapGeoCoder(params);
    }

}
