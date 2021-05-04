package com.fjsy.architecture.utils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.fjsy.architecture.global.data.constants.ConstantsSPKey;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class ConvertConstants {
    public static String getCountryAreCodeMobile(String mobile, String countryAreaCode) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(countryAreaCode);
        buffer.append(mobile);
        return buffer.toString();
    }

    public static String getChatOrConversationShowName(String id) {
        String friendsRemark = SPUtils.getInstance().getString(ConstantsSPKey.FriendRemark);
        if (!StringUtils.isEmpty(friendsRemark)) {
            HashMap<String, String> remakMaps = GsonUtils.fromJson(friendsRemark,
                    new TypeToken<HashMap<String, String>>() {
                    }.getType());
            if (null != remakMaps) {
                return StringUtils.isEmpty(remakMaps.get(id)) ? "" : remakMaps.get(id);
            }
        }
        return "";
    }

    public static HashMap<String, String> getIdRemarkMap() {
        String friendsRemark = SPUtils.getInstance().getString(ConstantsSPKey.FriendRemark);
        if (!StringUtils.isEmpty(friendsRemark)) {
            HashMap<String, String> remakMaps = GsonUtils.fromJson(friendsRemark,
                    new TypeToken<HashMap<String, String>>() {
                    }.getType());
            return null == remakMaps ? new HashMap<>() : remakMaps;
        }
        return new HashMap<>();
    }

}
