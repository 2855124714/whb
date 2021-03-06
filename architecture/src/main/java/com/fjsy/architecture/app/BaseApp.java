package com.fjsy.architecture.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bytedance.boost_multidex.BoostMultiDex;
import com.fjsy.architecture.BuildConfig;
import com.fjsy.architecture.R;
import com.fjsy.architecture.global.data.bean.UserManager;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.global.location.LocationInfoManage;
import com.fjsy.architecture.global.location.bean.LocationInfoBean;
import com.fjsy.architecture.ui.manager.IApplicationDelegate;
import com.fjsy.architecture.utils.ClassUtils;
import com.fjsy.architecture.utils.Utils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.kunminx.architecture.BaseApplication;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.model.GSYModel;
import com.shuyu.gsyvideoplayer.player.IPlayerInitSuccessListener;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.jessyan.autosize.AutoSize;
import okhttp3.OkHttpClient;
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.IjkExo2MediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

import static com.blankj.utilcode.util.ThreadUtils.runOnUiThread;



public class BaseApp extends BaseApplication {

    private List<IApplicationDelegate> mAppDelegateList;

    public static BaseApp INSTANCE;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BoostMultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Utils.init(this);
        //AndroidAutoSize
        AutoSize.checkAndInit(this);
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, Constants.ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
        //Arouter?????????
        if (BuildConfig.DEBUG) {
            //??????InstantRun?????????????????????ARouter.init????????????openDebug
            ARouter.openLog();     // ????????????
            ARouter.openDebug();   // ??????????????????(?????????InstantRun?????????????????????????????????????????????????????????????????????,?????????????????????)
        }
//        ARouter.init(this);
        //OKHttp?????????
        initOkHttpUtils();
        //??????X5WebView?????????
        initX5WebView();
        //GSY????????????????????????
//        PlayerFactory.setPlayManager(IjkPlayerManager.class);//ijk??????
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);//Exo??????
        GSYVideoManager.instance().setPlayerInitSuccessListener(new IPlayerInitSuccessListener() {
            ///??????????????????????????????
            @Override
            public void onPlayerInitSuccess(IMediaPlayer player, GSYModel model) {
                if (player instanceof IjkExo2MediaPlayer) {
                    ((IjkExo2MediaPlayer) player).setTrackSelector(new DefaultTrackSelector(BaseApp.this));
                    ((IjkExo2MediaPlayer) player).setLoadControl(new DefaultLoadControl());
                }
            }
        });

        LocationInfoManage.getInstance().requestSingleFreshLocation();

        //?????????????????????
//        UMConfigure.init(this,Constants.UmAPPKEY
//                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");
        // ????????????
        PlatformConfig.setWeixin(Constants.WxAppID, Constants.WxAppSecret);
        PlatformConfig.setWXFileProvider(AppUtils.getAppPackageName() + ".fileprovider");

        //?????????bugly
//        CrashReport.initCrashReport(getApplicationContext(), "ca47d9b158", false);
    }

    /**
     * ?????????OkhttpUtils
     */
    public void initOkHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TJ_Clan"))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //????????????
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * ????????????Activity??????????????????
     */
    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    private void initX5WebView() {
        // ?????????TBS??????????????????WebView????????????????????????
        QbSdk.setDownloadWithoutWifi(true);
        HashMap<String, Object> map = new HashMap<>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5????????????????????????????????????true??????x5?????????????????????????????????x5??????????????????????????????????????????????????????
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5?????????????????????
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    //static ?????????????????????????????????
    static {
        //???????????????????????????
        //???????????????Header?????????
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.main, android.R.color.white);//????????????????????????
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("????????? %s"));//???????????????Header???????????? ???????????????Header
            }
        });
        //???????????????Footer?????????
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //???????????????Footer???????????? BallPulseFooter
                return new ClassicsFooter(context);
            }
        });
    }


    /**
     * toast??????
     *
     * @param text ????????????
     */
    public static void toast(String text) {
        toast(text, Gravity.CENTER);
    }


    /**
     * toast??????
     *
     * @param resId ??????????????????ID
     */
    public static void toast(int resId) {
        toast(resId, Gravity.CENTER);
    }

    /**
     * toast??????
     *
     * @param resId   ??????????????????ID
     * @param gravity ????????????
     */
    public static void toast(int resId, int gravity) {
        Toast toast = Toast.makeText(INSTANCE, resId, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    /**
     * toast??????
     *
     * @param text    ????????????
     * @param gravity ????????????
     */
    public static void toast(String text, int gravity) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast toast = Toast.makeText(INSTANCE, text, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

}
