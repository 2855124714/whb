package com.fjsy.architecture.global.data;

import androidx.databinding.ObservableField;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.data.bean.AboutKefuBean;
import com.fjsy.architecture.global.data.bean.AboutVersionBean;
import com.fjsy.architecture.global.data.bean.ArticleDetailBean;
import com.fjsy.architecture.global.data.bean.MapGeoCoderBean;
import com.fjsy.architecture.global.data.bean.PayAddBean;
import com.fjsy.architecture.global.data.bean.PayChannelLoadBean;
import com.fjsy.architecture.global.data.bean.PayOptionLoadBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface GlobalBaseService {

    //发送短信
    @FormUrlEncoded
    @POST("api/login/sendsms.html")
    Observable<ArrayBean> sendsms(@FieldMap HashMap<String, Object> params);

    //短信验证
    @FormUrlEncoded
    @POST("api/login/checksms.html")
    Observable<ArrayBean> checksms(@FieldMap HashMap<String, Object> params);

    //用户协议
    @GET("api/about/article.html")
    Observable<ArticleDetailBean> aboutArticle(@QueryMap HashMap<String, Object> params);

    //版本检查
    @FormUrlEncoded
    @POST("api/about/version.html")
    Observable<AboutVersionBean> aboutVersion(@FieldMap HashMap<String, Object> params);

    //下载文件
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    //获取客服id
    @FormUrlEncoded
    @POST("api/about/kefu.html")
    Observable<AboutKefuBean> aboutKefu(@FieldMap HashMap<String, Object> params);

    //支付通道
    @FormUrlEncoded
    @POST("api/pay.channel/load.html")
    Observable<PayChannelLoadBean> payChannelLoad(@FieldMap HashMap<String, Object> params);

    //支付项
    @FormUrlEncoded
    @POST("api/pay.option/load.html")
    Observable<PayOptionLoadBean> payOptionLoad(@FieldMap HashMap<String, Object> params);

    @FormUrlEncoded
    @POST("api/pay/add.html")
    Observable<PayAddBean> payAdd(@FieldMap HashMap<String, Object> params);

    @FormUrlEncoded
    @POST("api/map/geocoder.html")
    Observable<MapGeoCoderBean> mapGeoCoder(@FieldMap HashMap<String, Object> params);
}
