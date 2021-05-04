package com.fjsy.architecture.global.data.request;

import androidx.annotation.StringDef;

import com.fjsy.architecture.global.data.bean.AboutKefuBean;
import com.fjsy.architecture.global.data.bean.AboutVersionBean;
import com.fjsy.architecture.global.data.bean.ArticleDetailBean;
import com.fjsy.architecture.global.data.bean.MapGeoCoderBean;
import com.fjsy.architecture.global.data.bean.PayAddBean;
import com.fjsy.architecture.global.data.bean.PayChannelLoadBean;
import com.fjsy.architecture.global.data.bean.PayOptionLoadBean;
import com.fjsy.architecture.global.data.bean.UserManager;
import com.fjsy.architecture.global.data.response.GlobalBaseRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

import io.reactivex.Observable;

public class GlobalBaseRequest {

    //数据类型，own：自己的
    public static final String ArticleTypeAgreement = "agreement";
    public static final String ArticleTypePrivacy = "privacy";
    public static final String ArticleTypeInstructions = "instructions";

    // 定义适用于参数的注解，限定取值范围
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @StringDef({ArticleTypeAgreement, ArticleTypePrivacy})
    public @interface ArticleType {
    }

    //////////////////////////////////////支付
    // 充值
    public static final String PayTypeRecharge = "recharge";

    // 定义适用于参数的注解，限定取值范围
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @StringDef({PayTypeRecharge})
    public @interface PayType {
    }

    /**
     * 行政区划
     * <p>
     * 获取所有省市区
     */
    public Observable<ArticleDetailBean> aboutArticle(@ArticleType String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        return GlobalBaseRepository.getInstance().aboutArticle(params);
    }

    /**
     * 检查版本
     */
    public Observable<AboutVersionBean> aboutVersion() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "android");
        return GlobalBaseRepository.getInstance().aboutVersion(params);
    }

    /**
     * 客服
     */
    public Observable<AboutKefuBean> aboutKefu() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", UserManager.getInstance().getToken());
        return GlobalBaseRepository.getInstance().aboutKefu(params);
    }

    /**
     * 支付通道
     */
    public Observable<PayChannelLoadBean> payChannelLoad() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", UserManager.getInstance().getToken());
        return GlobalBaseRepository.getInstance().payChannelLoad(params);
    }

    /**
     * 支付项
     */
    public Observable<PayOptionLoadBean> payOptionLoad() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", UserManager.getInstance().getToken());
        return GlobalBaseRepository.getInstance().payOptionLoad(params);
    }

    /**
     * 支付提交
     */
    public Observable<PayAddBean> payAdd(@PayType String option_name, String amount, String channel_id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", UserManager.getInstance().getToken());
        params.put("option_name", option_name);
        params.put("amount", amount);
        params.put("channel_id", channel_id);
        return GlobalBaseRepository.getInstance().payAdd(params);
    }

    public Observable<MapGeoCoderBean> mapGeoCoder(String address) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("address", address);
        return GlobalBaseRepository.getInstance().mapGeoCoder(params);
    }

}
