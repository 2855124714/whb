package com.fjsy.architecture.global.data.bean;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.fjsy.architecture.app.BaseApp;
import com.fjsy.architecture.global.data.constants.ConstantsDbKey;
import com.fjsy.architecture.net.cache.CacheManager;

public class UserManager {

    private volatile static UserManager INSTANCE;

    private UserBean mUser;
    private MutableLiveData<UserBean> userLiveData = new MutableLiveData<>();

    public static synchronized UserManager getInstance() {
        if (INSTANCE == null) {
            synchronized (UserManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserManager();
                }
            }
        }
        return INSTANCE;
    }

    private UserManager() {
        UserBean cache = (UserBean) CacheManager.getCache(ConstantsDbKey.User);
        if (cache != null) {
            mUser = cache;
            userLiveData.postValue(mUser);
        }
    }

    public MutableLiveData<UserBean> getUserLiveData() {
        return userLiveData;
    }

    public void save(UserBean user) {
        mUser = user;
        userLiveData.postValue(user);
        CacheManager.save(ConstantsDbKey.User, user);

    }

    public boolean isLogin() {
        return mUser != null;
    }

    public String getToken() {
        return mUser != null && !TextUtils.isEmpty(mUser.token)
                ? mUser.token : "";
    }

    public UserBean getUser() {
        return isLogin() ? mUser : null;
    }

    public void logout() {
        CacheManager.delete(ConstantsDbKey.User, mUser);
        userLiveData.postValue(null);
        mUser = null;
    }
}
