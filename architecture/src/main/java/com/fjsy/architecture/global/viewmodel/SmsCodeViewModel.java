package com.fjsy.architecture.global.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.data.response.bean.BaseViewModel;
import com.fjsy.architecture.data.response.bean.DataDisposable;
import com.fjsy.architecture.data.response.bean.ModelLiveData;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.global.data.request.SmsRequest;
import com.fjsy.architecture.utils.ConvertConstants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SmsCodeViewModel extends BaseViewModel {


    public MutableLiveData<Integer> sendMsgCountDown = new MutableLiveData<>(Constants.defaultTime);

    private final SmsRequest mSmsRequest = new SmsRequest();

    public ModelLiveData<ArrayBean> sendSmsLiveData = new ModelLiveData<>();
    public ModelLiveData<ArrayBean> checkSmsLiveData = new ModelLiveData<>();

    //国家/区域号
    public MutableLiveData<String> countryAreaCode=new MutableLiveData<>("86");

    /**
     * 发送短信
     *
     * @param mobile 手机号
     */
    public void sendSms(String mobile) {
        DataDisposable<ArrayBean> disposable = mSmsRequest
                .sendsms(ConvertConstants.getCountryAreCodeMobile(mobile,countryAreaCode.getValue()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(sendSmsLiveData.dispose());
        registerDisposable(disposable);
    }

    /**
     * 短信验证
     *
     * @param mobile  手机号
     * @param smscode 短信验证码
     */
    public void checkSms(String mobile, String smscode) {
        DataDisposable<ArrayBean> disposable = mSmsRequest.checksms(ConvertConstants.getCountryAreCodeMobile(mobile,countryAreaCode.getValue()), smscode).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(checkSmsLiveData.dispose());
        registerDisposable(disposable);
    }




}
