package com.fjsy.architecture.ui.binding_adapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fjsy.architecture.ui.widget.recyclerview.TouchHelper;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.touch.OnItemStateChangedListener;

import java.util.Collections;

public class SwipeRecyclerViewBindingAdapter {

    @BindingAdapter(value = {"rvLayoutManager", "hasFixedSize"}, requireAll = false)
    public static void RecyclerViewLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager rvLayoutManager, boolean hasFixedSize) {
        if (rvLayoutManager != null)
            recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setHasFixedSize(hasFixedSize);
    }

    @BindingAdapter(value = {"onItemMoveListener","canItemMove","onItemStateChangedListener"}, requireAll = false)
    public static void onItemMoveListener(SwipeRecyclerView swipeRecyclerView, OnItemMoveListener onItemMoveListener, Boolean canItemMove, OnItemStateChangedListener onItemStateChangedListener){
        swipeRecyclerView.setLongPressDragEnabled(canItemMove);
        if (onItemMoveListener!=null){
            swipeRecyclerView.setOnItemMoveListener(onItemMoveListener);
        }
        if (onItemStateChangedListener!=null){
            swipeRecyclerView.setOnItemStateChangedListener(onItemStateChangedListener);
        }
    }

}
