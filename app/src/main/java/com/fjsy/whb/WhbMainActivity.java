package com.fjsy.whb;

import android.Manifest;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.global.location.LocationInfoManage;
import com.fjsy.architecture.ui.base.BaseProjectActivity;
import com.fjsy.whb.databinding.ActivityMainWhbBinding;
import com.fjsy.whb.ui.chat.ChatFragment;
import com.fjsy.whb.ui.main.HomeFragment;
import com.fjsy.whb.ui.mine.MineFragment;
import com.fjsy.whb.ui.shoppingcart.ShoppingCartFragment;
import com.fjsy.whb.ui.video.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


public class WhbMainActivity extends BaseProjectActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private MainViewModel mViewModel;
    private boolean mBackKeyPressed = false;
    private static SparseArray<Fragment> fragmentList = new SparseArray<>();
    private MainFragmentAdapter mAdapter;
    private ActivityMainWhbBinding mBinding;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main_whb, BR.vm, mViewModel);
    }

    @Override
    public void init() {
        super.init();
        initPermission();
        initNavigationView();
        initViewPager();
    }

    private void initNavigationView() {
        mBinding = (ActivityMainWhbBinding) getBinding();
        BottomNavigationView navView = mBinding.navView;
        navView.setOnNavigationItemSelectedListener(this);
        navView.setItemIconTintList(null);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        List<Integer> ids = new ArrayList<>();
        ids.add(R.id.navigation_home);
        ids.add(R.id.navigation_video);
        ids.add(R.id.navigation_chat);
        ids.add(R.id.navigation_shopping_cart);
        ids.add(R.id.navigation_mine);
        clearToast(navView, ids);
    }

    private void initViewPager() {
        fragmentList.clear();
        fragmentList.put(0, HomeFragment.newInstance());
        fragmentList.put(1, VideoFragment.newInstance());
        fragmentList.put(2, ChatFragment.newInstance());
        fragmentList.put(3, ShoppingCartFragment.newInstance());
        fragmentList.put(4, MineFragment.newInstance());
        mAdapter = new MainFragmentAdapter(this, fragmentList);
        ViewPager2 containerFragment = mBinding.containerFragment;
        containerFragment.setAdapter(mAdapter);
        containerFragment.setSaveEnabled(false);
        containerFragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BottomNavigationView navView = mBinding.navView;
                if (position == 0) {
                    navView.setSelectedItemId(R.id.navigation_home);
                } else if (position == 1) {
                    navView.setSelectedItemId(R.id.navigation_video);
                } else if (position == 2) {
                    navView.setSelectedItemId(R.id.navigation_chat);
                } else if (position == 3) {
                    navView.setSelectedItemId(R.id.navigation_shopping_cart);
                } else if (position == 4) {
                    navView.setSelectedItemId(R.id.navigation_mine);
                }
            }
        });
    }

    private void initPermission() {
        XXPermissions.with(this)
                //定位
                .permission(Permission.ACCESS_BACKGROUND_LOCATION)
                .permission(Permission.ACCESS_FINE_LOCATION)
                .permission(Permission.ACCESS_COARSE_LOCATION)
                .permission(Permission.ACCESS_MEDIA_LOCATION)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {

                        } else {
//                            ToastUtils.showShort("获取部分权限成功，但部分权限未正常授予");
                        }
                        LocationInfoManage.getInstance().requestLocation();
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            //如果只有后台定位权限被拒绝，则不响应
                            if (permissions != null && permissions.size() == 1
                                    && permissions.contains(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                                return;
                            }
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            ToastUtils.showShort("被永久拒绝授权，请手动授予权限");
                            XXPermissions.startPermissionActivity(WhbMainActivity.this, permissions);
                        } else {
                            ToastUtils.showShort("获取权限失败");
                        }
                    }
                });
    }


    /**
     * * 清除长按时的toast
     *
     * @param ids  与配置文件中对应的所有id
     */
    private void clearToast(BottomNavigationView bottomNavigationView, List<Integer> ids) {
        ViewGroup bottomNavigationMenuView = (ViewGroup) bottomNavigationView.getChildAt(0);
        //遍历子View,重写长按点击事件
        for (int position = 0; position < ids.size(); position++) {
            bottomNavigationMenuView.getChildAt(position).findViewById(ids.get(position)).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        ViewPager2 containerFragment = mBinding.containerFragment;
        if (itemId == R.id.navigation_home) {
            containerFragment.setCurrentItem(0, false);
            return true;
        } else if (itemId == R.id.navigation_video) {
            containerFragment.setCurrentItem(1, false);
            return true;
        } else if (itemId == R.id.navigation_chat) {
            containerFragment.setCurrentItem(2, false);
            return true;
        } else if (itemId == R.id.navigation_shopping_cart) {
            containerFragment.setCurrentItem(3, false);
            return true;
        } else if (itemId == R.id.navigation_mine) {
            containerFragment.setCurrentItem(4, false);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtils.showShort(getString(R.string.press_the_return_key_again_to_exit_the_application));
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                // @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            super.onBackPressed();
            ActivityUtils.finishAllActivities();
            AppUtils.exitApp();
        }
    }

}