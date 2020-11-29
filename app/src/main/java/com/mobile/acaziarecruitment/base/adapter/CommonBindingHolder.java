package com.mobile.acaziarecruitment.base.adapter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CommonBindingHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public V binding;

    public CommonBindingHolder(V binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public V getBinding() {
        return binding;
    }
}