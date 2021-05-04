package com.fjsy.architecture.global.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.ItemPayBinding;
import com.fjsy.architecture.global.data.bean.ItemPayTypeBean;

public class ItemPayTypeShortAdapter extends BaseQuickAdapter<ItemPayTypeBean, BaseDataBindingHolder<ItemPayBinding>> {

    public ItemPayTypeShortAdapter() {
        super(R.layout.item_pay);
    }

    public ObservableField<ItemPayTypeBean> selectBean = new ObservableField<>();

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemPayBinding> itemPayBindingBaseDataBindingHolder, ItemPayTypeBean bean) {
        ItemPayBinding binding = itemPayBindingBaseDataBindingHolder.getDataBinding();
        binding.setItem(bean);
        selectBean.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                bean.select = bean == selectBean.get();
                notifyItemChanged(itemPayBindingBaseDataBindingHolder.getAdapterPosition());
            }
        });
//        binding.getRoot().setOnClickListener((v) -> {
//            selectBean.set(binding.getItem());
//        });
    }

}
