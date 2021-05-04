package com.fjsy.whb.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.fjsy.architecture.ui.base.BaseProjectFragment;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.fjsy.architecture.ui.widget.Clickable;
import com.fjsy.whb.BR;
import com.fjsy.whb.R;
import com.fjsy.whb.WhbMainActivity;
import com.fjsy.whb.databinding.FragmentPswLoginBinding;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class PasswordLoginFragment extends BaseProjectFragment {

    private LoginViewModel mViewModel;
    private ClickProxyImp clickEvent = new ClickProxyImp();
    private FragmentPswLoginBinding mBinding;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_psw_login, BR.vm, mViewModel)
                .addBindingParam(BR.clickEvent, clickEvent);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding =(FragmentPswLoginBinding) getBinding();
        SpannableString spanStr = new SpannableString(mBinding.linearWechat.rbAgree.getText().toString());
        int agreeIndex = mBinding.linearWechat.rbAgree.getText().toString().indexOf(StringUtils.getString(R.string.whb_agreement));
        ForegroundColorSpan agreeColorSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.c_2E85FF));
        Clickable agreeClickSpan = new Clickable(v -> clickEvent.agreementContent());
        spanStr.setSpan(agreeColorSpan, agreeIndex, agreeIndex + 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(agreeClickSpan, agreeIndex, agreeIndex + 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mBinding.linearWechat.rbAgree.setText(spanStr);
    }


    @Override
    public void onResume() {
        super.onResume();
        ThreadUtils.getMainHandler().postDelayed(()->{
            mViewModel.mRightStatus.setValue(0);
        },200);
    }

    public class ClickProxyImp extends ClickProxy {
        public void login() {
            PasswordLoginFragment.this.startActivity(new Intent(getActivity(), WhbMainActivity.class));
        }

        public void wechatLogin() {

        }

        public void isAgreeCheck() {
            mViewModel.isAgree.set(!mViewModel.isAgree.get());
        }

        public void agreementContent() {

        }

    }
}
