package com.fjsy.whb.login;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.ui.base.BaseProjectActivity;
import com.fjsy.architecture.ui.widget.ToolbarAction;
import com.fjsy.whb.BR;
import com.fjsy.whb.R;
import com.fjsy.whb.databinding.ActivityLoginBinding;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseProjectActivity {

    private LoginViewModel mViewModel;
    private NavController navController;
    private boolean mBackKeyPressed = false;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LoginViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_login, com.fjsy.whb.BR.vm, mViewModel)
                .addBindingParam(BR.leftAction, ToolbarAction.createIcon(DrawableCompat.wrap(getResources().getDrawable(R.mipmap.ic_back_black)))
                        .setListener(view -> {
                            onBackPressed();
                        }));

    }

    @Override
    public void init() {
        super.init();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fcv);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
    }

    @Override
    public void subscribe() {
        super.subscribe();
        mViewModel.loginNav.observe(this, loginNav -> {
            switch (loginNav){
                case PASSWORD_LOGIN:
                    break;
                case SMS_LOGIN:
                    break;
                case SET_PASSWORD:
                    break;
            }

        });
        mViewModel.mRightStatus.observe(this, integer -> {
            if(0==integer){
                getBinding().setVariable(BR.rightAction, ToolbarAction.createText(getString(R.string.mobile_number_login), getResources().getColor(R.color.c_222222)).setListener((view) -> {
                    navController.navigate(R.id.smscode_login);
                    mViewModel.loginNav.setValue(LoginViewModel.LoginNav.SMS_LOGIN);
                }));
            }else if(1==integer){
                getBinding().setVariable(BR.rightAction, ToolbarAction.createText(getString(R.string.password_login), getResources().getColor(R.color.c_222222)).setListener((view) -> {
                    navController.navigate(R.id.password_login);
                    mViewModel.loginNav.setValue(LoginViewModel.LoginNav.PASSWORD_LOGIN);
                }));
            }else{
                getBinding().setVariable(BR.rightAction, null);
            }

        });
    }

    @Override
    public void onBackPressed() {
        int currentFragmentId = -1;
        if (navController != null && navController.getCurrentBackStackEntry().getDestination() != null) {
            currentFragmentId = navController.getCurrentBackStackEntry().getDestination().getId();
        }
        if (!mBackKeyPressed && (currentFragmentId == R.id.passwordLoginFragment || currentFragmentId == R.id.smscodeLoginFragment)) {
            ToastUtils.showShort(getString(R.string.press_the_return_key_again_to_exit_the_application));
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                // @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            if (mBackKeyPressed) {
                ActivityUtils.finishAllActivities();
                AppUtils.exitApp();
            } else {
                super.onBackPressed();
            }
        }
    }
}