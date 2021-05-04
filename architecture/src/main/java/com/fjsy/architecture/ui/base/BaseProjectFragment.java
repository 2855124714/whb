package com.fjsy.architecture.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fjsy.architecture.data.response.bean.DataListener;
import com.fjsy.architecture.data.response.bean.StatusInfo;
import com.fjsy.architecture.global.data.bean.UserManager;
import com.fjsy.architecture.global.route.base.BaseActivityPath;
import com.fjsy.architecture.ui.page.BaseFragment;
import com.fjsy.architecture.event.ClanEvent;
import com.fjsy.architecture.utils.EventUtils;
import com.fjsy.architecture.utils.Logger;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import org.greenrobot.eventbus.Subscribe;

public abstract class BaseProjectFragment extends BaseFragment implements DataListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ARouter.getInstance().inject(this);
        init();
        subscribe();
    }

    public void init() {
        EventUtils.register(this);
    }

    @Override
    public void dataStart() {

    }

    @Override
    public void dataStop() {

    }

    @Override
    public void dataOther(StatusInfo statusInfo) {
        if (statusInfo.isUnlogin()) {
//            logout();
        } else if (statusInfo.isHint2User()) {
//            promptFailure(statusInfo);
//            ARouter.getInstance().build(BaseActivityPath.BaseLogin)
//                    .navigation();
        } else {
            Logger.e(statusInfo);
        }
    }

    protected void logout() {
        UserManager.getInstance().logout();
    }

    public void subscribe() {

    }

    @Subscribe
    public void onHandlerEvent(ClanEvent clanEvent) {

    }

    @Override
    public void onDestroy() {
        EventUtils.unregister(this);
        super.onDestroy();
    }

    private LoadingPopupView loading;

    protected void showLoading() {
        if (loading == null)
            loading = new XPopup.Builder(getContext()).asLoading();
        if (!loading.isShow()) {
            loading.show();
        }
    }

    protected void showLoading(String msg) {
        if (loading == null)
            loading = new XPopup.Builder(getContext()).asLoading();
        if (!loading.isShow()) {
            loading.show();
            loading.setTitle(msg);
        }
    }

    protected void showLoading(String msg, boolean cancleable) {
        if (loading == null)
            loading = new XPopup.Builder(getContext()).asLoading();
        if (!loading.isShow()) {
            loading.show();
            loading.setTitle(msg);
            loading.dialog.setCancelable(cancleable);
            loading.dialog.setCanceledOnTouchOutside(cancleable);
        }
    }

    protected void hideLoading() {
        if (loading!=null) {
            if (loading.isShow() || loading.isShown()) {
                loading.dismiss();
                loading.setTitle("");
                if(null!=loading.dialog) {
                    loading.dialog.setCancelable(true);
                    loading.dialog.setCanceledOnTouchOutside(true);
                }
            }
        }
    }


}
