package com.fjsy.architecture.global.location;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.Utils;
import com.fjsy.architecture.global.location.bean.LocationInfoBean;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

public class LocationInfoManage {

    private volatile static LocationInfoManage INSTANCE;
    private TencentLocationManager mLocationManager;
    public MutableLiveData<TencentLocation> tencentLocationLiveData = new MutableLiveData<>();
    public MutableLiveData<LocationInfoBean> locationInfoLiveData = new MutableLiveData<>(new LocationInfoBean());

    public static synchronized LocationInfoManage getInstance() {
        if (INSTANCE == null) {
            synchronized (LocationInfoManage.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationInfoManage();
                }
            }
        }
        return INSTANCE;
    }

    private LocationInfoManage() {
        mLocationManager = TencentLocationManager.getInstance(Utils.getApp());
    }

    public void requestSingleFreshLocation() {
        mLocationManager.requestSingleFreshLocation(null, tencentLocationListener, Looper.getMainLooper());
    }

    public void requestSingleFreshLocation(TencentLocationListener locationListener) {
        mLocationManager.requestSingleFreshLocation(null, locationListener, Looper.getMainLooper());
    }

    public void requestLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        //用户可以自定义定位间隔，时间单位为毫秒，不得小于1000毫秒:
        request.setInterval(5 * 60 * 1000);
        //设置请求级别
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
        //是否允许使用GPS
        request.setAllowGPS(true);
        //是否需要获取传感器方向
        request.setAllowDirection(true);
        //是否需要开启室内定位
        request.setIndoorLocationMode(true);
        mLocationManager.requestLocationUpdates(request, tencentLocationListener, Looper.getMainLooper());
    }

    TencentLocationListener tencentLocationListener = new TencentLocationListener() {
        @Override
        public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
            handlerLocationData(tencentLocation);
        }

        @Override
        public void onStatusUpdate(String s, int i, String s1) {

        }
    };

    public void handlerLocationData(TencentLocation tencentLocation) {
        tencentLocationLiveData.setValue(tencentLocation);
        LocationInfoBean bean = new LocationInfoBean();
        bean.longitude = tencentLocation.getLongitude();
        bean.latitude = tencentLocation.getLatitude();
        bean.province = !TextUtils.isEmpty(tencentLocation.getProvince())?tencentLocation.getProvince():"";
        bean.city = !TextUtils.isEmpty(tencentLocation.getCity())?tencentLocation.getCity():"";
        bean.county = !TextUtils.isEmpty(tencentLocation.getDistrict())?tencentLocation.getDistrict():"";

        if (!TextUtils.isEmpty(tencentLocation.getCityCode())){
            bean.province_id = !TextUtils.isEmpty(tencentLocation.getCityCode())?tencentLocation.getCityCode().substring(0,2) + "0000":"";
            if (tencentLocation.getCityCode().startsWith("90", 2)){
                bean.city_id = !TextUtils.isEmpty(tencentLocation.getCityCode())?tencentLocation.getCityCode():"";
            } else {
                bean.city_id = !TextUtils.isEmpty(tencentLocation.getCityCode())?tencentLocation.getCityCode().substring(0,4)+"00":"";
            }
            bean.county_id = !TextUtils.isEmpty(tencentLocation.getCityCode())?tencentLocation.getCityCode():"";
        }
        bean.address = (!TextUtils.isEmpty(tencentLocation.getTown()) && !"Unknown".equals(tencentLocation.getTown()) ? tencentLocation.getTown() : "")
                + (!TextUtils.isEmpty(tencentLocation.getVillage()) && !"Unknown".equals(tencentLocation.getVillage()) ? tencentLocation.getVillage() : "")
                + (!TextUtils.isEmpty(tencentLocation.getStreet()) && !"Unknown".equals(tencentLocation.getStreet()) ? tencentLocation.getStreet() : "")
                + (!TextUtils.isEmpty(tencentLocation.getStreetNo()) && !"Unknown".equals(tencentLocation.getStreetNo()) ? tencentLocation.getStreetNo() : "")
//                    + (!TextUtils.isEmpty(tencentLocation.getAddress()) && !"Unknown".equals(tencentLocation.getAddress()) ? tencentLocation.getAddress() : "")
        ;


        locationInfoLiveData.setValue(bean);
    }

}
