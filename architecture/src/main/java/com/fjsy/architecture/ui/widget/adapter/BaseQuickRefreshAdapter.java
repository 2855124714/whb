package com.fjsy.architecture.ui.widget.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class BaseQuickRefreshAdapter<T, VH extends BaseDataBindingHolder> extends BaseQuickAdapter<T, VH> implements OnRefreshLoadMoreListener {

    //刷新控件
    private RefreshLayout refreshLayout;

    //初始化页面
    private final ObservableField<Integer> initPage = new ObservableField<>(1);
    //分页页面
    private final ObservableField<Integer> page = new ObservableField<>(initPage.get());
    //分页加载数据数量
    private final ObservableField<Integer> limit = new ObservableField<>(10);

    public boolean isInitPage() {
        return page.get().equals(initPage.get());
    }

    public BaseQuickRefreshAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BaseQuickRefreshAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        setRefreshLayout(refreshLayout);
        if (onLoadListener != null) {
            onLoadListener.loadMore(getCurrentPage(), getLimit());
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        setRefreshLayout(refreshLayout);
        initPage();
        if (onLoadListener != null) {
            onLoadListener.refreshListener(getCurrentPage(), getLimit());
        }
    }

    private void setRefreshLayout(RefreshLayout refreshLayout) {
        if (this.refreshLayout == null && refreshLayout != null)
            this.refreshLayout = refreshLayout;
    }

    public void loadData(List<T> data) {
        if (data != null) {
            if (isInitPage()) {
                setNewInstance(data);
            } else {
                addData(data);
            }
            if (data.size() > 0)
                addPage();
            finishRefresh(data.size() == 0);
        } else {
            finishRefresh();
        }
    }

    //结束刷新
    public void finishRefresh() {
        finishRefresh(false);
    }

    /**
     * 结束刷新
     *
     * @param loadMoreOver 加载更多是否显示无数据
     */
    public void finishRefresh(boolean loadMoreOver) {
        if (refreshLayout != null) {
            if (refreshLayout.isRefreshing()) {
                refreshLayout.finishRefresh();
            }
            if (refreshLayout.isLoading()) {
                if (loadMoreOver) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        }
    }

    public int getCurrentPage() {
        return page.get();
    }

    public int initPage() {
        page.set(initPage.get());
        return page.get();
    }

    public void addPage() {
        page.set(getCurrentPage() + 1);
    }

    public void setLimit(int limit) {
        this.limit.set(limit);
    }

    public int getLimit() {
        return limit.get();
    }

    private OnLoadListener onLoadListener;

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public interface OnLoadListener {
        void refreshListener(int page, int limit);

        void loadMore(int page, int limit);
    }

}
