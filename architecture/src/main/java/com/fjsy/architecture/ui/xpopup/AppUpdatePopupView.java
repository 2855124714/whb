package com.fjsy.architecture.ui.xpopup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.PopupAppUpdateBinding;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.lxj.xpopup.core.CenterPopupView;

public class AppUpdatePopupView extends CenterPopupView {

    public AppUpdatePopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_app_update;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        PopupAppUpdateBinding binding = DataBindingUtil.bind(getPopupImplView());
        if (binding!=null){
            binding.setClickProxy(new ClickProxyImp());
            binding.executePendingBindings();
        }
    }

    private CallBack callBack;

    public AppUpdatePopupView setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        public void update();
    }

    public class ClickProxyImp extends ClickProxy {
        public void cancel() {
            dismiss();
        }

        public void update() {
            if (callBack!=null){
                callBack.update();
            }
            dismiss();
        }
    }
}
