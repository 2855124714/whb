package com.fjsy.architecture.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.GsonUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.data.response.bean.DataListener;
import com.fjsy.architecture.data.response.bean.StatusInfo;
import com.fjsy.architecture.global.data.bean.UserManager;
import com.fjsy.architecture.global.route.base.BaseActivityPath;
import com.fjsy.architecture.ui.page.BaseActivity;
import com.fjsy.architecture.event.ClanEvent;
import com.fjsy.architecture.ui.widget.ToolbarAction;
import com.fjsy.architecture.utils.EventUtils;
import com.fjsy.architecture.utils.Logger;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import org.greenrobot.eventbus.Subscribe;

public abstract class BaseProjectActivity extends BaseActivity implements DataListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
//        ARouter.getInstance().inject(this);
        init();
        subscribe();

    }

    @Override
    protected void onDestroy() {
        EventUtils.unregister(this);
        super.onDestroy();
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
//            ARouter.getInstance().build(BaseActivityPath.BaseLogin)
//                    .navigation();
        } else {
            Logger.e(statusInfo);
        }
    }

    protected void logout() {
        UserManager.getInstance().logout();
    }

    public void subscribe(){

    }

    public ToolbarAction createBack() {
        ToolbarAction leftAction = ToolbarAction.createBack(this, R.mipmap.ic_back_black);
        return leftAction;
    }

    public ToolbarAction createBack(boolean isWhite) {
        ToolbarAction leftAction = ToolbarAction.createBack(this, isWhite?(R.mipmap.ic_back_white):(R.mipmap.ic_back_black));
        return leftAction;
    }

    public ToolbarAction createBack(int ids) {
        ToolbarAction leftAction = ToolbarAction.createBack(this, ids);
        return leftAction;
    }

    @Subscribe
    public void handlerEvent(ClanEvent clanEvent) {

    }

    public void back(View view) {
        finish();
    }


    private LoadingPopupView loading;

    protected void showLoading() {
        if (loading == null)
            loading = new XPopup.Builder(this).asLoading();
        if (!loading.isShow()) {
            loading.show();
        }
    }

    protected void showLoading(String msg) {
        if (loading == null)
            loading = new XPopup.Builder(this).asLoading();
        if (!loading.isShow()) {
            loading.show();
            loading.setTitle(msg);
        }
    }

    protected void showLoading(String msg, boolean cancleable) {
        if (loading == null)
            loading = new XPopup.Builder(this).asLoading();
        if (!loading.isShow()) {
            loading.show();
            loading.setTitle(msg);
            loading.dialog.setCancelable(cancleable);
            loading.dialog.setCanceledOnTouchOutside(cancleable);
        }
    }

    protected void hideLoading() {
        if (loading!=null)
            if (loading.isShow() || loading.isShown()) {
                loading.dismiss();
                loading.setTitle("");
                loading.dialog.setCancelable(true);
                loading.dialog.setCanceledOnTouchOutside(true);
            }
    }


}
