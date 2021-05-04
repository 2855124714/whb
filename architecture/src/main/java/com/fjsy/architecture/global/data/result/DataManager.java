package com.fjsy.architecture.global.data.result;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private volatile static DataManager INSTANCE;

    public static synchronized DataManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DataManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataManager();
                }
            }
        }
        return INSTANCE;
    }

    public static void releaseData() {
        INSTANCE = null;
    }

    public ObservableField<List<Object>> objectListData = new ObservableField<>(new ArrayList<>());

    public ObservableField<Object> objectData = new ObservableField<>();



}
