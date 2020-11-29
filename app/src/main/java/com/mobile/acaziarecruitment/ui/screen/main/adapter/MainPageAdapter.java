package com.mobile.acaziarecruitment.ui.screen.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobile.acaziarecruitment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPageAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setData(List<BaseFragment> data) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0) {
            return fragments.size();
        }
        return 0;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}