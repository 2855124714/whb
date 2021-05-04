package com.fjsy.architecture.global.data;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.data.bean.TencentDistrictBean;
import com.fjsy.architecture.global.data.bean.TencentPlaceSearchBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface TencentService {

    //获取省市区列表
    //本接口用于获取全部省市区三级行政区划
    @GET("district/v1/list")
    Observable<TencentDistrictBean> list(@QueryMap HashMap<String, Object> params);

    //获取下级行政区划
    //获取指定行政区划的子级行政区划
    @GET("district/v1/getchildren")
    Observable<TencentDistrictBean> getchildren(@QueryMap HashMap<String, Object> params);

    //周边搜索（圆形范围）：boundary=nearby(lat,lng<中心坐标>,radius<半径/米>)
    @GET("place/v1/search")
    Observable<TencentPlaceSearchBean> placeSearch(@QueryMap HashMap<String, Object> params);


}
