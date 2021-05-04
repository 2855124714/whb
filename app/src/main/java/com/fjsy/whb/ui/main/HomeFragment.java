package com.fjsy.whb.ui.main;

import com.fjsy.architecture.ui.base.BaseProjectFragment;
import com.fjsy.whb.BR;
import com.fjsy.whb.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class HomeFragment extends BaseProjectFragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(HomeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm,mViewModel);
    }

}