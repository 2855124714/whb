package com.fjsy.architecture.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VisibilityStateFrameView extends FrameLayout {
    public VisibilityStateFrameView(@NonNull Context context) {
        super(context);
    }

    public VisibilityStateFrameView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VisibilityStateFrameView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VisibilityStateFrameView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            if (visibilityStateCallBack != null)
                visibilityStateCallBack.visibleState();
        } else if (visibility == GONE) {
            if (visibilityStateCallBack != null)
                visibilityStateCallBack.goneState();
        }
    }

    private VisibilityStateCallBack visibilityStateCallBack;

    public void setVisibilityStateCallBack(VisibilityStateCallBack visibilityStateCallBack) {
        this.visibilityStateCallBack = visibilityStateCallBack;
    }

    public interface VisibilityStateCallBack {
        public void visibleState();

        public void goneState();
    }

}
