package com.fjsy.architecture.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StatusBarHeightView extends View {

    public StatusBarHeightView(Context context) {
        super(context);
    }

    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,getStatusBarHeight(getContext()));
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
