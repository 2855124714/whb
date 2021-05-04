package com.fjsy.architecture.ui.widget;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class CommonFragmentStateAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> mFragments;

    public CommonFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> mFragments) {
        super(fragmentActivity);
        this.mFragments = mFragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments != null ? mFragments.size() : 0;
    }
}
