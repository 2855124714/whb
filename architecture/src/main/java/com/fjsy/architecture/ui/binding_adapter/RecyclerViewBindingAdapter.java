package com.fjsy.architecture.ui.binding_adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fjsy.architecture.R;
import com.fjsy.architecture.ui.widget.recyclerview.TouchHelper;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"rvLayoutManager", "hasFixedSize"}, requireAll = false)
    public static void RecyclerViewLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager rvLayoutManager, boolean hasFixedSize) {
        if (rvLayoutManager != null)
            recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setHasFixedSize(hasFixedSize);
    }

    @BindingAdapter(value = {"adapter"}, requireAll = false)
    public static void RecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (adapter != null){
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter(value = {"swapAdapter","removeAndRecycleExistingViews"}, requireAll = false)
    public static void RecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter swapAdapter,boolean removeAndRecycleExistingViews) {
        if (swapAdapter != null){
            recyclerView.swapAdapter(swapAdapter,removeAndRecycleExistingViews);
        }
    }

    @BindingAdapter(value = {"addItemDecoration", "addItemDecorationIndex"}, requireAll = false)
    public static void addItemDecoration(RecyclerView recyclerView, RecyclerView.ItemDecoration addItemDecoration, int addItemDecorationIndex) {
        if (addItemDecorationIndex != 0 && addItemDecoration != null) {
            recyclerView.addItemDecoration(addItemDecoration, addItemDecorationIndex);
        } else {
            if (addItemDecoration != null)
                recyclerView.addItemDecoration(addItemDecoration);
        }
    }

    @BindingAdapter(value = {"removeItemDecoration", "removeItemDecorationIndex"}, requireAll = false)
    public static void removeItemDecoration(RecyclerView recyclerView, RecyclerView.ItemDecoration removeItemDecoration, int removeItemDecorationIndex) {
        if (removeItemDecorationIndex != 0) {
            recyclerView.removeItemDecorationAt(removeItemDecorationIndex);
        } else {
            if (removeItemDecoration != null)
                recyclerView.removeItemDecoration(removeItemDecoration);
        }
    }

    @BindingAdapter(value = {"attachToTouchHelper"}, requireAll = false)
    public static void attachToRecyclerView(RecyclerView recyclerView, TouchHelper touchHelper) {
        if (touchHelper != null)
            touchHelper.attachToRecyclerView(recyclerView);
    }

}
