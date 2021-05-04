package com.fjsy.architecture.ui.xpopup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fjsy.architecture.R;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.lxj.xpopup.core.BottomPopupView;

public class ChooseImagePopupView extends BottomPopupView {
    public ChooseImagePopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_choose_image;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        ClickProxyImp clickProxyImp = new ClickProxyImp();
        findViewById(R.id.tv_take_picture).setOnClickListener((v) -> clickProxyImp.take_picture());
        findViewById(R.id.tv_select_from_phone_album).setOnClickListener((v) -> clickProxyImp.select_from_phone_album());
        findViewById(R.id.tv_cancel).setOnClickListener((v) -> clickProxyImp.cancel());
    }

    private ChooseImageEvent mChooseImageEvent;

    public ChooseImagePopupView setChooseImageEvent(ChooseImageEvent mChooseImageEvent) {
        this.mChooseImageEvent = mChooseImageEvent;
        return this;
    }

    public interface ChooseImageEvent {
        void take_picture();

        void select_from_phone_album();
    }

    public class ClickProxyImp extends ClickProxy {

        void take_picture() {
            if (mChooseImageEvent != null) {
                mChooseImageEvent.take_picture();
            }
            dismiss();
        }

        void select_from_phone_album() {
            if (mChooseImageEvent != null) {
                mChooseImageEvent.select_from_phone_album();
            }
            dismiss();
        }

        void cancel() {
            dismiss();
        }

    }
}
