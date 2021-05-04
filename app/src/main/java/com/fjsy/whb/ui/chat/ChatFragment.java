package com.fjsy.whb.ui.chat;

import com.fjsy.architecture.ui.base.BaseProjectFragment;
import com.fjsy.whb.BR;
import com.fjsy.whb.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class ChatFragment extends BaseProjectFragment {

    private ChatViewModel mViewModel;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ChatViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_chat, BR.vm, mViewModel);
    }

}