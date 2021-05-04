package com.fjsy.whb;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainFragmentAdapter extends FragmentStateAdapter {

    private SparseArray<Fragment> mFragments;

    public MainFragmentAdapter(@NonNull FragmentActivity fragmentActivity, SparseArray<Fragment> mFragments) {
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
