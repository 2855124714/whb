package com.fjsy.architecture.global.data.bean;

import com.fjsy.architecture.data.response.bean.BaseBean;

import java.util.List;

public class ReportIndexBean extends BaseBean {

    public CnfBean cnf;

    public static class CnfBean {
        public List<String> words;
    }
}
