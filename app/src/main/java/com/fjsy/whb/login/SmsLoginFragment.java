package com.fjsy.whb.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.data.response.bean.ArrayBean;
import com.fjsy.architecture.data.response.bean.DataObserver;
import com.fjsy.architecture.data.response.bean.StatusInfo;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.global.viewmodel.SmsCodeViewModel;
import com.fjsy.architecture.ui.base.BaseProjectFragment;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.fjsy.architecture.ui.widget.Clickable;
import com.fjsy.whb.BR;
import com.fjsy.whb.R;
import com.fjsy.whb.WhbMainActivity;
import com.fjsy.whb.databinding.FragmentPswLoginBinding;
import com.fjsy.whb.databinding.FragmentSmsLoginBinding;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class SmsLoginFragment extends BaseProjectFragment {

    private LoginViewModel mViewModel;
    private ClickProxyImp clickEvent=new ClickProxyImp();
    private SmsCodeViewModel mSmsCodeViewModel;
    private FragmentSmsLoginBinding mBinding;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LoginViewModel.class);
        mSmsCodeViewModel = getFragmentScopeViewModel(SmsCodeViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_sms_login, BR.vm,mViewModel)
                .addBindingParam(BR.clickEvent,clickEvent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding =(FragmentSmsLoginBinding) getBinding();
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
            mViewModel.mRightStatus.setValue(1);
        },200);
    }

    @Override
    public void subscribe() {
        super.subscribe();
        mViewModel.sendMsgCountDown.observe(this, integer -> {
            if (integer < Constants.defaultTime && integer >= 0) {
                ThreadUtils.getMainHandler().postDelayed(() -> {
                    mViewModel.sendMsgCountDown.setValue(integer - 1);
                }, 1000);
            } else if (integer < 0) {
                mViewModel.sendMsgCountDown.setValue(Constants.defaultTime);
            }
        });
        mSmsCodeViewModel.sendSmsLiveData.observe(this, new DataObserver<ArrayBean>(this) {
            @Override
            protected void dataResult(StatusInfo statusInfo, ArrayBean bean) {
                if (statusInfo.isSuccessful()) {
                    mViewModel.sendMsgCountDown.setValue(mViewModel.sendMsgCountDown.getValue() - 1);
                } else {
                    ToastUtils.showShort(statusInfo.statusMessage);
                }
            }
        });
    }

    public class ClickProxyImp extends ClickProxy{
        public void sendSmsCode(){

        }
        public void isAgreeCheck(){
            mViewModel.isAgree.set(!mViewModel.isAgree.get());

        }
        public void wechatLogin(){

        }
        public void login(){
            SmsLoginFragment.this.startActivity(new Intent(getActivity(), WhbMainActivity.class));

        }
        public void agreementContent(){

        }

    }
}
