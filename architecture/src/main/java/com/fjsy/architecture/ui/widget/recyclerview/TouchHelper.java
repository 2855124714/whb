package com.fjsy.architecture.ui.widget.recyclerview;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 小米Xylitol
 * @email xiaomi987@hotmail.com
 * @desc RecyclerView拖动帮助类
 * @date 2018-05-25 11:18
 */
public class TouchHelper extends ItemTouchHelper {

    private TouchCallback callback;

    public TouchHelper(TouchCallback callback) {
        super(callback);
        this.callback = callback;
    }


    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        super.startDrag(viewHolder);
    }

    public void setEnableDrag(boolean enableDrag) {
        callback.setEnableDrag(enableDrag);
    }

    public void setEnableSwipe(boolean enableSwipe) {
        callback.setEnableSwipe(enableSwipe);
    }
}
