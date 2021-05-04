package com.fjsy.architecture.ui.xpopup;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.ItemSetAsPrivacyBinding;
import com.fjsy.architecture.global.data.bean.MomentSecretBean;

import org.jetbrains.annotations.NotNull;

public class SetAsPrivacyAdapter extends BaseQuickAdapter<MomentSecretBean, BaseDataBindingHolder<ItemSetAsPrivacyBinding>> {

    public SetAsPrivacyAdapter() {
        super(R.layout.item_set_as_privacy);
    }

    public ObservableField<MomentSecretBean> selectBean = new ObservableField<>();

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemSetAsPrivacyBinding> itemSetAsPrivacyBindingBaseDataBindingHolder, MomentSecretBean momentSecretBean) {
        ItemSetAsPrivacyBinding binding = itemSetAsPrivacyBindingBaseDataBindingHolder.getDataBinding();
        binding.setItem(momentSecretBean);

        selectBean.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                momentSecretBean.select = momentSecretBean == selectBean.get();
                notifyItemChanged(itemSetAsPrivacyBindingBaseDataBindingHolder.getAdapterPosition());
            }
        });

    }

    public void setDefaultSelectBean(MomentSecretBean momentSecretBean){
        selectBean.set(momentSecretBean);
    }
}
