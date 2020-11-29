package com.mobile.acaziarecruitment.base.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.mobile.acaziarecruitment.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseArrayAdapter<T, V extends ViewDataBinding> extends ArrayAdapter<T> {

    protected V binding;
    protected List<T> dataList = new ArrayList<>();

    public BaseArrayAdapter(Context context) {
        super(context, 0);

    }

    protected abstract int layoutItemId();

    protected abstract void bindData(V binding, T t);

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutItemId(), parent, false);
        if (dataList.size() > 0) {
            bindData(binding, dataList.get(position));
        }
        return binding.getRoot();
    }

    public void setDataList(List<T> items) {
        if (EmptyUtils.isEmpty(items)) return;
        dataList.clear();
        if (items != null) {
            dataList.addAll(items);
        }
        addAll(dataList);
        notifyDataSetChanged();
    }

    public T getDataItem(int position) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }
        return dataList.get(position);
    }
}
