package com.fjsy.whb.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fjsy.architecture.ui.base.BaseProjectFragment;
import com.fjsy.whb.BR;
import com.fjsy.whb.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class VideoFragment extends BaseProjectFragment {

    private VideoViewModel mViewModel;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    protected void initViewModel() {
         mViewModel = getFragmentScopeViewModel(VideoViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_video, BR.vm,mViewModel);
    }

}