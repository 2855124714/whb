package com.fjsy.architecture.ui.binding_adapter;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

public class SmartRefreshLayoutBindingAdapter {

    @BindingAdapter(value = {"onRefreshListener", "onLoadMoreListener"}, requireAll = false)
    public static void SmartRefreshLayoutListener(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener, OnLoadMoreListener onLoadMoreListener) {
        if (onRefreshListener != null) {
            smartRefreshLayout.setOnRefreshListener(onRefreshListener);
        }
        if (onLoadMoreListener != null) {
            smartRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);
        }
    }

    @BindingAdapter(value = {"onRefreshLoadMoreListener"}, requireAll = false)
    public static void SmartRefreshLayoutListener(SmartRefreshLayout smartRefreshLayout, OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        if (onRefreshLoadMoreListener != null)
            smartRefreshLayout.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
    }

    @BindingAdapter(value = {"disEnableRefresh", "disEnableLoadMore"}, requireAll = false)
    public static void SmartRefreshLayoutEnable(SmartRefreshLayout smartRefreshLayout, boolean disEnableRefresh, boolean disEnableLoadMore) {
        smartRefreshLayout.setEnableRefresh(!disEnableRefresh);
        smartRefreshLayout.setEnableLoadMore(!disEnableLoadMore);
    }

}
