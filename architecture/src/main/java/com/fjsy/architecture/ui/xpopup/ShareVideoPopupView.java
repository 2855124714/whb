package com.fjsy.architecture.ui.xpopup;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.LayoutWatermarkBinding;
import com.fjsy.architecture.databinding.PopupShareVideoBinding;
import com.fjsy.architecture.event.ClanEvent;
import com.fjsy.architecture.global.data.constants.Constants;
import com.fjsy.architecture.global.download.DownLoadHelper;
import com.fjsy.architecture.global.download.DownloadListener;
import com.fjsy.architecture.global.event.GlobalEventAction;
import com.fjsy.architecture.ui.base.ClickProxy;
import com.fjsy.architecture.ui.service.DownloadService;
import com.fjsy.architecture.utils.DownloadFileUtils;
import com.fjsy.architecture.utils.EventUtils;
import com.fjsy.architecture.utils.FileUtils;
import com.lxj.xpopup.core.BottomPopupView;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.Objects;

public class ShareVideoPopupView extends BottomPopupView {

    public ShareVideoPopupView(@NonNull Context context) {
        super(context);
    }

    private ObservableField<String> downloadUrl = new ObservableField<>("");
    private ObservableField<String> userId = new ObservableField<>("");
    private ObservableField<String> downloadProgress = new ObservableField<>("");
    private ObservableField<Boolean> downloadFinish = new ObservableField<>(false);

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_video;
    }

    private PopupShareVideoBinding binding;

    @Override
    protected void onCreate() {
        super.onCreate();
        EventUtils.register(this);
        binding = DataBindingUtil.bind(getPopupImplView());
        if (binding != null) {
            binding.setClickProxy(new ClickProxyImp());
            binding.setIsFinish(false);
            binding.setDownloadProgress(getContext().getString(R.string.start_download));
            downloadProgress.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    binding.setDownloadProgress(downloadProgress.get());
                    binding.executePendingBindings();
                }
            });
            downloadFinish.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    if (downloadFinish.get()) {
                        binding.setIsFinish(true);
                    }
                }
            });

            if (!TextUtils.isEmpty(downloadUrl.get())) {
                File file = new File(Constants.getVideoSavePath() + File.separator + userId.get() + (downloadUrl.get().substring(downloadUrl.get().lastIndexOf("/") + 1)));
                if (!DownloadService.urlList.contains(downloadUrl.get()) || !file.exists()) {
                    DownloadService.startDownloadService(getContext(), downloadUrl.get(), "",userId.get());
                } else {
                    binding.setIsFinish(true);
                }
            } else {
                ToastUtils.showShort(getContext().getString(R.string.an_error_occurred));
                dismiss();
            }
            binding.executePendingBindings();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventUtils.unregister(this);
    }

    @Subscribe
    public void handlerEvent(ClanEvent clanEvent) {
        if (clanEvent.clanEventAction == GlobalEventAction.Download) {
            String url = (String) clanEvent.data[0];
            if (url.equals(downloadUrl.get())) {
                int downloadState = (int) clanEvent.data[1];
                if (downloadState == DownloadService.DownLoadStateDownloading) {
                    downloadProgress.set(getContext().getString(R.string.download_progress_is, "") + (String) clanEvent.data[2] + "%");
                } else if (downloadState == DownloadService.DownLoadStateFinish) {
                    downloadProgress.set(getContext().getString(R.string.download_complete));
                    downloadFinish.set(true);
                } else if (downloadState == DownloadService.DownLoadStateFail) {
                    downloadProgress.set(getContext().getString(R.string.download_failed));
                }
            }
        }
    }

    public ShareVideoPopupView setDownloadUrl(String url) {
        downloadUrl.set(url);
        return this;
    }

    public ShareVideoPopupView setUserId(String url) {
        userId.set(url);
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }

    public class ClickProxyImp extends ClickProxy {
        public void close() {
            dismiss();
        }

        public void openWeChat() {
            if (AppUtils.isAppInstalled(Constants.WeChatPkgName)) {
                AppUtils.launchApp(Constants.WeChatPkgName);
            } else {
                ToastUtils.showShort(R.string.please_install_wechat_first);
            }
        }
    }

}
