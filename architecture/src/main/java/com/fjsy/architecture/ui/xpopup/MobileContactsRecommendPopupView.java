package com.fjsy.architecture.ui.xpopup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;

import com.fjsy.architecture.BR;
import com.fjsy.architecture.R;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.lxj.xpopup.core.CenterPopupView;

public class MobileContactsRecommendPopupView extends CenterPopupView {

    public MobileContactsRecommendPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_mobile_contacts_recommend;
    }

    public ObservableField<String> friendName = new ObservableField<>("");

    @Override
    protected void onCreate() {
        super.onCreate();
        ViewDataBinding binding = DataBindingUtil.bind(getPopupImplView());
        if (binding != null) {
            binding.setVariable(BR.clickProxy, new ClickProxyImp());
            binding.setVariable(BR.friendName,friendName.get());
            friendName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    binding.setVariable(BR.friendName,friendName.get());
                    binding.executePendingBindings();
                }
            });
            binding.executePendingBindings();
        }
    }

    public MobileContactsRecommendPopupView setFriendName(String friendName) {
        this.friendName.set(friendName);
        return this;
    }

    public class ClickProxyImp extends ClickProxy {

        public void cancel() {
            dismiss();
        }

        public void viewNow() {
            if (callBack!=null)
                callBack.viewNow();
            dismiss();
        }

    }

    private CallBack callBack;

    public MobileContactsRecommendPopupView setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        public void viewNow();
    }
}
