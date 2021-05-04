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
        //Arouter初始化
        if (BuildConfig.DEBUG) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
//        ARouter.init(this);
        //OKHttp初始化
        initOkHttpUtils();
        //腾讯X5WebView初始化
        initX5WebView();
        //GSY播放器初始化设置
//        PlayerFactory.setPlayManager(IjkPlayerManager.class);//ijk模式
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);//Exo模式
        GSYVideoManager.instance().setPlayerInitSuccessListener(new IPlayerInitSuccessListener() {
            ///播放器初始化成果回调
            @Override
            public void onPlayerInitSuccess(IMediaPlayer player, GSYModel model) {
                if (player instanceof IjkExo2MediaPlayer) {
                    ((IjkExo2MediaPlayer) player).setTrackSelector(new DefaultTrackSelector(BaseApp.this));
                    ((IjkExo2MediaPlayer) player).setLoadControl(new DefaultLoadControl());
                }
            }
        });

        LocationInfoManage.getInstance().requestSingleFreshLocation();

        //友盟初始化设置
//        UMConfigure.init(this,Constants.UmAPPKEY
//                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");
        // 微信设置
        PlatformConfig.setWeixin(Constants.WxAppID, Constants.WxAppSecret);
        PlatformConfig.setWXFileProvider(AppUtils.getAppPackageName() + ".fileprovider");

        //初始化bugly
//        CrashReport.initCrashReport(getApplicationContext(), "ca47d9b158", false);
    }

    /**
     * 初始化OkhttpUtils
     */
    public void initOkHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TJ_Clan"))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 实现注册Activity生命周期回调
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
        // 在调用TBS初始化、创建WebView之前进行如下配置
        QbSdk.setDownloadWithoutWifi(true);
        HashMap<String, Object> map = new HashMap<>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    //static 代码段可以防止内存泄露
    static {
        //刷新控件初始化设置
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.main, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context);
            }
        });
    }


    /**
     * toast提示
     *
     * @param text 提示内容
     */
    public static void toast(String text) {
        toast(text, Gravity.CENTER);
    }


    /**
     * toast提示
     *
     * @param resId 提示内容资源ID
     */
    public static void toast(int resId) {
        toast(resId, Gravity.CENTER);
    }

    /**
     * toast提示
     *
     * @param resId   提示内容资源ID
     * @param gravity 显示位置
     */
    public static void toast(int resId, int gravity) {
        Toast toast = Toast.makeText(INSTANCE, resId, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    /**
     * toast提示
     *
     * @param text    提示内容
     * @param gravity 显示位置
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
