package com.fjsy.architecture.global.data.request;

import android.text.TextUtils;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.data.bean.TencentDistrictBean;
import com.fjsy.architecture.global.data.bean.TencentPlaceSearchBean;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.global.data.response.TencentDataRepository;
import com.fjsy.architecture.global.location.LocationInfoManage;

import java.util.HashMap;

import io.reactivex.Observable;

public class TencentRequest {


    /**
     * 行政区划
     * <p>
     * 获取所有省市区
     */
    public Observable<TencentDistrictBean> list() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.TencentLocationKey);
        return TencentDataRepository.getInstance().list(params);
    }

    /**
     * 行政区划
     *
     * @param id 父级行政区划ID（adcode），
     *           缺省时返回一级行政区划，也就是省级
     */
    public Observable<TencentDistrictBean> getchildren(String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.TencentLocationKey);
        if (!TextUtils.isEmpty(id))
            params.put("id", id);
        return TencentDataRepository.getInstance().getchildren(params);
    }

    /**
     * 周边搜索（圆形范围）
     */
    public Observable<TencentPlaceSearchBean> placeSearch(int page_index,int page_size, String keyword) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.TencentLocationKey);
        params.put("orderby", "_distance");
        params.put("page_index", page_index);
        params.put("page_size", page_size);
        if (LocationInfoManage.getInstance().locationInfoLiveData.getValue()!=null)
        params.put("boundary", "nearby(" + LocationInfoManage.getInstance().locationInfoLiveData.getValue().latitude +"," + LocationInfoManage.getInstance().locationInfoLiveData.getValue().longitude + ",1000)");
        params.put("keyword",!TextUtils.isEmpty(keyword)?keyword
                : (LocationInfoManage.getInstance().tencentLocationLiveData.getValue()!=null?LocationInfoManage.getInstance().tencentLocationLiveData.getValue().getAddress():""));
        return TencentDataRepository.getInstance().placeSearch(params);
    }


}
