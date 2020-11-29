package com.mobile.acaziarecruitment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public abstract class BaseFragment<T extends ViewDataBinding, VM extends ViewModel> extends Fragment {

    protected T binding;
    protected VM viewModel;

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        initView();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    protected abstract VM getViewModel();

    protected void updateData() {

    }
}