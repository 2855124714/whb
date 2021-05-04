package com.fjsy.architecture.global.data.response;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.global.data.TencentService;
import com.fjsy.architecture.global.data.bean.TencentDistrictBean;
import com.fjsy.architecture.global.data.bean.TencentPlaceSearchBean;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.net.ResourceNetwork;

import java.util.HashMap;

import io.reactivex.Observable;

public class TencentDataRepository {

    private volatile static TencentDataRepository INSTANCE;
    private final TencentService TencentService;

    public static synchronized TencentDataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (TencentDataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TencentDataRepository();
                }
            }
        }
        return INSTANCE;
    }

    private TencentDataRepository() {
        TencentService = new ResourceNetwork(Constants.tencentBaseUrl).createService(TencentService.class);
    }

    //获取所有省市区信息
    public Observable<TencentDistrictBean> list(HashMap<String, Object> params) {
        return TencentService.list(params);
    }

    //获取指定行政区省市区信息
    public Observable<TencentDistrictBean> getchildren(HashMap<String, Object> params) {
        return TencentService.getchildren(params);
    }

    //周边搜索（圆形范围）
    public Observable<TencentPlaceSearchBean> placeSearch(HashMap<String, Object> params) {
        return TencentService.placeSearch(params);
    }


}
