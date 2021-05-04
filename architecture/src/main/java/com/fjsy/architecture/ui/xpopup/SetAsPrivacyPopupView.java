package com.fjsy.architecture.ui.xpopup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.PopupSetAsPrivacyBinding;
import com.fjsy.architecture.global.data.bean.MomentSecretBean;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.fjsy.architecture.ui.widget.RecyclerViewDivider;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.ArrayList;
import java.util.List;

public class SetAsPrivacyPopupView extends BottomPopupView {

    public SetAsPrivacyPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_set_as_privacy;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        PopupSetAsPrivacyBinding binding = (PopupSetAsPrivacyBinding) DataBindingUtil.bind(getPopupImplView());
        if (binding != null) {
            ClickProxyImp clickProxy = new ClickProxyImp();
            List<MomentSecretBean> momentSecretBeanList = new ArrayList<>();
            momentSecretBeanList.add(new MomentSecretBean("1", "公开", "所有朋友可见", false));
            momentSecretBeanList.add(new MomentSecretBean("2", "仅亲友可见", "仅自己的亲友可见", false));
            momentSecretBeanList.add(new MomentSecretBean("0", "私密", "仅自己可见", false));
            SetAsPrivacyAdapter adapter = new SetAsPrivacyAdapter();
            adapter.setNewInstance(momentSecretBeanList);
            binding.setAdapter(adapter);
            binding.setItemDecoration(RecyclerViewDivider.builder().height(1).width(0).color(R.color.c_E5E5E5).build());
            adapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
                clickProxy.set(adapter.getItem(position).secret);
            });
        }

    }

    @Override
    protected void doAfterShow() {
        super.doAfterShow();

    }

    public class ClickProxyImp extends ClickProxy {
        public void set(String id) {
            dismiss();
            if (callBack != null) {
                callBack.set(id);
            }
        }

    }

    private CallBack callBack;

    public SetAsPrivacyPopupView setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        public void set(String id);
    }
}
