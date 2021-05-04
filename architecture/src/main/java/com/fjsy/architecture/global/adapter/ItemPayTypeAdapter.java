package com.fjsy.architecture.global.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.ItemPayLongBinding;
import com.fjsy.architecture.global.data.bean.ItemPayTypeBean;
import com.fjsy.architecture.global.data.bean.PayChannelLoadBean;

public class ItemPayTypeAdapter extends BaseQuickAdapter<PayChannelLoadBean.DataBean, BaseDataBindingHolder<ItemPayLongBinding>> {

    public ItemPayTypeAdapter() {
        super(R.layout.item_pay_long);
    }

    public ObservableField<PayChannelLoadBean.DataBean> selectBean = new ObservableField<>();

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemPayLongBinding> itemPayBindingBaseDataBindingHolder, PayChannelLoadBean.DataBean bean) {
        ItemPayLongBinding binding = itemPayBindingBaseDataBindingHolder.getDataBinding();
        binding.setItem(bean);

        selectBean.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                bean.select = selectBean.get() == bean;
                notifyItemChanged(itemPayBindingBaseDataBindingHolder.getAdapterPosition());
            }
        });

        binding.getRoot().setOnClickListener((v) -> {
            selectBean.set(bean);
        });
    }

}
