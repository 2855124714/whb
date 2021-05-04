package com.fjsy.whb.ui.shoppingcart;

import androidx.fragment.app.Fragment;

import com.chad.library.BR;
import com.fjsy.architecture.ui.base.BaseProjectFragment;
import com.fjsy.whb.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;


public class ShoppingCartFragment extends BaseProjectFragment {
    private ShoppingCartViewModel mViewModel;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    protected void initViewModel() {
        mViewModel=getFragmentScopeViewModel(ShoppingCartViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_shopping_cart, BR.vm,mViewModel);
    }
}