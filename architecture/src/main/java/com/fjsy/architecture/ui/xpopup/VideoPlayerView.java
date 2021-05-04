package com.fjsy.architecture.ui.xpopup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.LogUtils;
import com.fjsy.architecture.R;
import com.fjsy.architecture.databinding.PopupVideoPlayerBinding;
import com.lxj.xpopup.impl.FullScreenPopupView;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

public class VideoPlayerView extends FullScreenPopupView {

    public VideoPlayerView(@NonNull Context context, String videoUrl) {
        super(context);
        LogUtils.eTag("videoUrl",videoUrl);
        this.videoUrl.set(videoUrl);
    }

    public VideoPlayerView(@NonNull Context context) {
        super(context);
    }

    public ObservableField<String> videoUrl = new ObservableField<>("");

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_video_player;
    }

    private PopupVideoPlayerBinding binding;

    @Override
    protected void onCreate() {
        super.onCreate();
        binding = (PopupVideoPlayerBinding) DataBindingUtil.bind(getPopupImplView());
        if (binding != null) {
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
            binding.playerView.setUp(videoUrl.get(), true, "");
            binding.playerView.setLooping(true);
            binding.playerView.getBackButton().setVisibility(GONE);
            binding.dismiss.setOnClickListener(v -> dismiss());
            binding.playerView.getBackButton().setOnClickListener(v -> dismiss());
            binding.playerView.getFullscreenButton().setVisibility(GONE);
            videoUrl.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    binding.playerView.setUp(videoUrl.get(), false, "");
                }
            });
            isPlayer.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    if (isPlayer.get()) {
                        binding.playerView.onVideoPause();
                    } else {
                        binding.playerView.onVideoResume();
                    }
                }
            });
        }
    }

    @Override
    protected void doAfterShow() {
        super.doAfterShow();
        if (binding != null)
            binding.playerView.startPlayLogic();
    }

    @Override
    protected void doAfterDismiss() {
        super.doAfterDismiss();
        if (binding != null)
            binding.playerView.onVideoPause();
    }

    ObservableBoolean isPlayer = new ObservableBoolean();

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            if (isPlayer != null && isPlayer.get()) {
                isPlayer.set(false);
            }
        } else if (visibility == GONE) {
            if (binding != null) {
                if (binding.playerView.isInPlayingState()) {
                    isPlayer.set(true);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding.playerView.onVideoReset();
            binding.playerView.release();
        }
    }

    public VideoPlayerView setVideoUrl(String url) {
        videoUrl.set(url);
        return this;
    }

}
