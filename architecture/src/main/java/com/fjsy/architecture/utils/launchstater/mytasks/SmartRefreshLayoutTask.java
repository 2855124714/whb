package com.fjsy.architecture.utils.launchstater.mytasks;


import android.graphics.Color;

import com.fjsy.architecture.R;
import com.fjsy.architecture.utils.launchstater.task.Task;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/**
 * Created by leo
 * on 2020/4/29.
 *
 */
public class SmartRefreshLayoutTask extends Task {
    @Override
    public void run() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((mContext, layout) -> {
            return new ClassicsHeader(mContext).setPrimaryColorId(Color.WHITE).setAccentColorId(R.color.main);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((mContext, layout) -> {
            return new ClassicsFooter(mContext).setPrimaryColorId(Color.WHITE).setAccentColorId(R.color.main);
        });
    }
}
