package com.fjsy.architecture.ui.xpopup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;

import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.PopupDetailOperationBinding;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.lxj.xpopup.core.BottomPopupView;

public class DetailOperationPopupView extends BottomPopupView {

    public DetailOperationPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_detail_operation;
    }

    private final ObservableBoolean isSelf = new ObservableBoolean(false);
    private final ObservableBoolean isCollect = new ObservableBoolean(false);

    @Override
    protected void onCreate() {
        super.onCreate();
        PopupDetailOperationBinding binding;
        binding = DataBindingUtil.bind(getPopupImplView());
        if (binding != null) {
            binding.setClickProxy(new ClickProxyImp());
            binding.setIsSelf(isSelf);
            binding.setIsCollect(isCollect);
        }

    }

    public DetailOperationPopupView setIsSelf(Boolean isSelf) {
        this.isSelf.set(isSelf);
        return this;
    }

    public DetailOperationPopupView setIsCollect(Boolean isCollect) {
        this.isCollect.set(isCollect);
        return this;
    }

    public class ClickProxyImp extends ClickProxy {
        public void setAsPrivacy() {
            dismiss();
            if (detailOperationListener != null)
                detailOperationListener.setAsPrivacy();
        }

        public void sendToFriends() {
            dismiss();
            if (detailOperationListener != null)
                detailOperationListener.sendToFriends();
        }

        public void collect() {
            dismiss();
            if (detailOperationListener != null)
                detailOperationListener.collect();
        }

        public void report() {
            dismiss();
            if (detailOperationListener != null)
                detailOperationListener.report();
        }

        public void del() {
            dismiss();
            if (detailOperationListener != null)
                detailOperationListener.del();
        }

        public void cancel() {
            if (detailOperationListener != null)
                detailOperationListener.cancel();
            dismiss();
        }
    }

    private DetailOperationListener detailOperationListener;

    public DetailOperationPopupView setDetailOperationListener(DetailOperationListener detailOperationListener) {
        this.detailOperationListener = detailOperationListener;
        return this;
    }

    public interface DetailOperationListener {
        public void setAsPrivacy();

        public void sendToFriends();

        public void collect();

        public void report();

        public void del();

        public void cancel();
    }

}
