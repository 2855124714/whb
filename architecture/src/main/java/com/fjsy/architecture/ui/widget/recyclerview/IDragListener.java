package com.fjsy.architecture.ui.widget.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 小米Xylitol
 * @email xiaomi987@hotmail.com
 * @desc 开启拖动排序
 * @date 2018-05-25 13:37
 */
public interface IDragListener {
    void startDrag(RecyclerView.ViewHolder holder);
}
