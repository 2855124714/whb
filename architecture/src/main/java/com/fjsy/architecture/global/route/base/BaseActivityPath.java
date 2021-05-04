package com.fjsy.architecture.global.route.base;

import androidx.lifecycle.MutableLiveData;

public class BaseActivityPath {
    public static final String BaseLogin = "/base/login";
    public static final String MapMarket = "/map/marker";
    public static final String BaseWeb = "/base/web";
    public static final String ScanZxing = "/zxing/scan";
    public static final String BaseCamera = "/base/clancamera";
    public static final String BaseCheckAndClip = "/base/checkandclip";
    public static final String BaseCountryArea="/base/ui/login";
    public static class Params {
        public static final String latitude = "latitude";
        public static final String longitude = "longitude";
        public static final String Address = "address";
        public static final String Type = "type";
        public static final String TypeAgreement = "agreement";
        public static final String TypePrivacy = "privacy";
        public static final String TypeInstructions = "instructions";
        public static final String Id = "id";
    }
}