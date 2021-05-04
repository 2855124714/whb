package com.fjsy.plugin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/6/15
 *     desc  : 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 * </pre>
 */

object Versions {
    val ShortcutBadger = "1.1.22@aar"
    val retrofit = "2.9.0"
    val appcompat = "1.2.0"
    val material = "1.2.1"
    val coreKtx = "1.3.2"
    val constraintlayout = "2.0.4"
    val coordinatorlayout = "1.1.0"
    val flexbox = "2.0.1"
    val percentlayout = "1.0.0"
    val navigation = "2.3.2"
    val paging = "3.0.0-alpha01"
    val timber = "4.7.1"
    val kotlin = "1.4.20"
    val koin = "2.1.5"
    val work = "2.2.0"
    val room = "2.2.6"
    val cardview = "1.0.0"
    val recyclerview = "1.1.0"
    val fragment = "1.2.1"
    val legacy = "1.0.0"
    val anko = "0.10.8"

    val junit = "4.+"
    val junitExt = "1.1.2"
    val espressoCore = "3.3.0"
    val jDatabinding = "1.0.1"

    val lifecycle = "2.2.0"

    val gson = "2.8.6"
    val arch = "2.0.0"

    val okhttp = "4.9.0"
    val logging = "4.9.0"

    val boost_multidex = "1.0.1"
    val eventbus = "3.0.0"
    val startup = "1.0.0"
    val arouter = "1.5.1"

    val glide = "4.11.0"
    val glide_transformations = "4.3.0"

    val swiperecyclerview = "1.3.2"
    val swipebacklayout = "1.3.0"
    val bga_badgeview = "1.1.8"
    val bga_baseadapter = "2.0.0@aar"
    val qrcode = "1.3.7"
    val xpopup = "2.2.23"
    val gsy_player = "8.1.2"
    val x5webview = "43967"
    val youth_banner = "2.1.0"
    val picture_selector = "2.6.0"
    val permission = "9.6"
    val timelineview = "1.1.5"
    val util = "1.30.5"
    val immersionbar = "3.0.0"
    val refresh = "2.0.1"
    val tinypinyin = "3.0.0"
    val brv_adapter = "3.0.4"
    val joda_time = "2.10.9"
    val autosize = "1.2.1"

    val rxjava2 = "2.2.20"
    val rx_android = "2.1.1"

    val dataBinding = "4.1.1"
    val okhttputils = "2.6.2"
    val ExpandableTextView = "v1.6.1-x"
    val badgeview = "1.1.3"
    val video_trimmer = "1.5.2"
    val video_player_manager = "0.2.0"

    val TencentLocationSdk = "7.2.6"
    val tencent_map_vector = "4.3.4"
    val wechat = "6.6.5"

    val camerax_view = "1.0.0-alpha19"
    val camerax_version = "1.0.0-beta12"
    val camera_core_version = "1.0.0-beta03"
    val experimental_version = "1.1.0-alpha01"
    val localbroadcastmanager = "1.0.0"
    val androidx_exifinterface_version = "1.3.1"
    val tuikit_version = "5.1.2"

    val um_common = "9.3.3"
    val um_asms = "1.1.4"
    val um_share = "7.1.4"

    val rxffmpeg = "4.9.0-lite"
    val file = "1.2.6"
    val vectordrawable = "1.1.0"
}

object AndroidX {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val coordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorlayout}"
    val flexbox =
            "com.google.android:flexbox:${Versions.flexbox}"
    val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    val vectordrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectordrawable}"

    val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.navigation}"
    val navigation_fragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navigation_ui = "androidx.navigation:navigation-ui:${Versions.navigation}"

    val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    val workTesting = "androidx.work:work-testing:${Versions.work}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    val percentlayout = "androidx.percentlayout:percentlayout:${Versions.percentlayout}"

    val exifinterface = "androidx.exifinterface:exifinterface:${Versions.androidx_exifinterface_version}"
    val localbroadcastmanager = "androidx.localbroadcastmanager:localbroadcastmanager:${Versions.localbroadcastmanager}"
    val annotation_experimental = "androidx.annotation:annotation-experimental:${Versions.experimental_version}"
}

object Room {
    val runtime = "androidx.room:room-runtime:${Versions.room}"
    val compiler = "androidx.room:room-compiler:${Versions.room}"
    val ktx = "androidx.room:room-ktx:${Versions.room}"
    val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    val testing = "androidx.room:room-testing:${Versions.room}"
}

object Fragment {
    val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Rx {
    val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
}

object Kt {
    val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object DataBinding {
    val compiler = "com.android.databinding:compiler:${Versions.dataBinding}"
}

object Koin {
    val core = "org.koin:koin-core:${Versions.koin}"
    val androidCore = "org.koin:koin-android:${Versions.koin}"
    val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val androidScope = "org.koin:koin-android-scope:$${Versions.koin}"
}

object Anko {
    val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}

object Retrofit {
    val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
}

object Depend {
    val junit = "junit:junit:${Versions.junit}"
    val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val jDatabinding = "com.hi-dhl:jdatabinding:${Versions.jDatabinding}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val boost_multidex = "com.bytedance.boost_multidex:boost_multidex:${Versions.boost_multidex}"
    val eventbus = "org.greenrobot:eventbus:${Versions.eventbus}"
    val startup = "androidx.startup:startup-runtime:${Versions.startup}"
    val swiperecyclerview = "com.yanzhenjie.recyclerview:x:${Versions.swiperecyclerview}"
    val swipebacklayout = "me.imid.swipebacklayout.lib:library:${Versions.swipebacklayout}"
    val xpopup = "com.lxj:xpopup:${Versions.xpopup}"
    val x5webview = "com.tencent.tbs.tbssdk:sdk:${Versions.x5webview}"
    val youth_banner = "com.youth.banner:banner:${Versions.youth_banner}"
    val picture_selector = "com.github.LuckSiege.PictureSelector:picture_library:v${Versions.picture_selector}"
    val permission = "com.hjq:xxpermissions:${Versions.permission}"
    val timelineview = "com.github.vipulasri:timelineview:${Versions.timelineview}"
    val util = "com.blankj:utilcodex:${Versions.util}"
    val immersionbar = "com.gyf.immersionbar:immersionbar:${Versions.immersionbar}"
    val tinypinyin = "com.github.promeg:tinypinyin:${Versions.tinypinyin}"
    val brv_adapter = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.brv_adapter}"
    val joda_time = "joda-time:joda-time:${Versions.joda_time}"
    val autosize = "me.jessyan:autosize:${Versions.autosize}"
    val okhttputils = "com.zhy:okhttputils:${Versions.okhttputils}"
    val ExpandableTextView = "com.github.MZCretin:ExpandableTextView:${Versions.ExpandableTextView}"
    val badgeview = "q.rorbin:badgeview:${Versions.badgeview}"
    val video_trimmer = "com.github.a914-gowtham:Android-video-trimmer:${Versions.video_trimmer}"
    val video_player_manager = "com.github.danylovolokh:video-player-manager:${Versions.video_player_manager}"
    val list_visibility_utils = "com.github.danylovolokh:list-visibility-utils:${Versions.video_player_manager}"

    //极速版 (预计占用 4M 左右空间)，主要移除了一些不常用的编解码器
    val RxFFmpeg = "com.github.microshow:RxFFmpeg:${Versions.rxffmpeg}"

    val ShortcutBadger = "me.leolin:ShortcutBadger:${Versions.ShortcutBadger}";
}

object Lifecycle {
    val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    val livedata = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
}

object Arch {
    val room_runtime = "android.arch.persistence.room:runtime:${Versions.arch}"
    val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.arch}"
}

object Okhttp {
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}";
    val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.logging}"
}

object ARouter {
    val api = "com.alibaba:arouter-api:${Versions.arouter}"
    val compiler = "com.alibaba:arouter-compiler:${Versions.arouter}"
}

object Glide {
    val runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val transformations = "jp.wasabeef:glide-transformations:${Versions.glide_transformations}"
}

object BGA {
    val badgeview_api = "cn.bingoogolapple:bga-badgeview-api:${Versions.bga_badgeview}"
    val badgeview_compiler = "cn.bingoogolapple:bga-badgeview-compiler:${Versions.bga_badgeview}"
    val qrcode = "cn.bingoogolapple:bga-qrcode-zxing:${Versions.qrcode}"
    val baseadapter = "cn.bingoogolapple:bga-baseadapter:${Versions.bga_baseadapter}"
}

object GSY {
    val player = "com.shuyu:GSYVideoPlayer:${Versions.gsy_player}"
}

object Refresh {
    val kernel = "com.scwang.smart:refresh-layout-kernel:${Versions.refresh}"      //核心必须依赖
    val header_classics = "com.scwang.smart:refresh-header-classics:${Versions.refresh}"    //经典刷新头
    val radar = "com.scwang.smart:refresh-header-radar:${Versions.refresh}"       //雷达刷新头
    val falsify = "com.scwang.smart:refresh-header-falsify:${Versions.refresh}"     //虚拟刷新头
    val material = "com.scwang.smart:refresh-header-material:${Versions.refresh}"    //谷歌刷新头
    val two_level = "com.scwang.smart:refresh-header-two-level:${Versions.refresh}"   //二级刷新头
    val ball = "com.scwang.smart:refresh-footer-ball:${Versions.refresh}"        //球脉冲加载
    val footer_classics = "com.scwang.smart:refresh-footer-classics:${Versions.refresh}"    //经典加载
}

object Tencent {
    val TencentLocationSdk = "com.tencent.map.geolocation:TencentLocationSdk-openplatform:${Versions.TencentLocationSdk}"
    val tencent_map_vector = "com.tencent.map:tencent-map-vector-sdk:${Versions.tencent_map_vector}"

    val wechat = "com.tencent.mm.opensdk:wechat-sdk-android-without-mta:${Versions.wechat}"//微信官方SDK依赖库

    //其中latest.release指代最新Bugly SDK版本号，也可以指定明确的版本号，例如2.1.9
    val bugly_crashreport = "com.tencent.bugly:crashreport:latest.release"

    //其中latest.release指代最新Bugly NDK版本号，也可以指定明确的版本号，例如3.0
    val bugly_nativecrashreport = "com.tencent.bugly:nativecrashreport:latest.release"
}

object Camera {
    val lifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax_version}"
    val view = "androidx.camera:camera-view:${Versions.camerax_view}"
    val core = "androidx.camera:camera-core:${Versions.camera_core_version}"
    val camera2 = "androidx.camera:camera-camera2:${Versions.camerax_version}"
}

object Tuikit {
    val tuikit = "com.tencent.imsdk:tuikit:${Versions.tuikit_version}"
    val tuikit_live = "com.tencent.imsdk:tuikit-live:${Versions.tuikit_version}"
}

object Umeng {
    val common = "com.umeng.umsdk:common:${Versions.um_common}"
    val asms = "com.umeng.umsdk:asms:${Versions.um_asms}"
    val share_core = "com.umeng.umsdk:share-core:${Versions.um_share}"
    val share_board = "com.umeng.umsdk:share-board:${Versions.um_share}"
    val share_wx = "com.umeng.umsdk:share-wx:${Versions.um_share}"
}

/**
 * 文件夹选择器
 */
object FileSelector {
    val file = "com.github.zp:z_file:${Versions.file}"

}