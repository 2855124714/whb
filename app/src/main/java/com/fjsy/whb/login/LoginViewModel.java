package com.fjsy.whb.login;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.fjsy.architecture.data.response.bean.BaseViewModel;
import com.fjsy.architecture.global.data.constants.Constants;

public class LoginViewModel extends BaseViewModel {
    public ObservableBoolean isAgree = new ObservableBoolean(false);
    public MutableLiveData<LoginNav> loginNav = new MutableLiveData<>(LoginNav.PASSWORD_LOGIN);
    public MutableLiveData<Integer> mRightStatus = new MutableLiveData(0);
    public MutableLiveData<Integer> sendMsgCountDown = new MutableLiveData(Constants.defaultTime);

    public enum LoginNav {
        PASSWORD_LOGIN(0),
        SMS_LOGIN(1),
        SET_PASSWORD(2);
//        FORGET_THE_PASSWORD(1),
//        SET_A_NEW_PASSWORD(2),
//        MOBILE_NUMBER_LOGIN(3),
//        REGISTER(4),
//        SET_PASSWORD(5);
        private int type;
        LoginNav(int type) {
            this.type = type;
        }
    }
}
