package com.mobile.acaziarecruitment.base.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.acaziarecruitment.base.BaseActivity;
import com.mobile.acaziarecruitment.base.callback.OnClickItemRecyclerView;
import com.mobile.acaziarecruitment.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerBindingAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private V binding;
    protected boolean isMoreLoading = true;

    public void addItemMore(List<T> lst) {
        try {
            int sizeInit = getDataList().size();
            getDataList().addAll(lst);
            notifyItemRangeChanged(sizeInit, getDataList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum RecyclerViewType {
        VIEW_TYPE_ITEM(0),
        VIEW_TYPE_LOADING(1);

        int value;

        RecyclerViewType(int va) {
            value = va;
        }

        public int value() {
            return value;
        }
    }

    public Context context;
    protected boolean isMoreData;
    private List<T> dataList = new ArrayList<>();
    protected OnClickItemRecyclerView<T> onClickItemRecyclerViewListener;

    public BaseActivity getBaseActivity() {
        if (context instanceof BaseActivity) {
            return (BaseActivity) context;
        }
        return null;
    }

    public BaseRecyclerBindingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CommonBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(getInflater(), layoutItemId(), parent, false);
        return new CommonBindingHolder(binding);
    }

    protected int layoutItemId() {
        return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CommonBindingHolder) {
            CommonBindingHolder<?> commonHolder = (CommonBindingHolder<?>) holder;
            if (dataList != null && dataList.size() > position && dataList.get(position) != null) {
                bindData((V) commonHolder.binding, dataList.get(position), position);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItemRecyclerViewListener != null) {
                        if (dataList.size() > 0 && position != -1) {
                            onClickItemRecyclerViewListener.onItemClick(dataList.get(position), position);

                        }
                    }
                }
            });

        }
    }

    protected abstract void bindData(V binding, T item, int position);

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == (getItemCount() - 1) && isMoreData())
                ? RecyclerViewType.VIEW_TYPE_LOADING.value() :
                RecyclerViewType.VIEW_TYPE_ITEM.value();
    }

    public boolean isMoreData() {
        return isMoreData;
    }

    public void setMoreData(boolean isMoreData) {
        this.isMoreData = isMoreData;
        notifyDataSetChanged();
    }


    protected LayoutInflater getInflater() {
        return LayoutInflater.from(context);
    }

    public void setDataList(List<T> items) {
        if (EmptyUtils.isEmpty(items)) return;
        dataList.clear();
        if (items != null) {
            dataList.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void addItemsAtFront(List<T> items) {
        if (EmptyUtils.isEmpty(items)) {
            return;
        }
        int index = dataList.size();
        dataList.addAll(items);
        notifyItemRangeInserted(index, dataList.size());

    }

    public void addItemsAtFront(final List<T> items, final Handler handler) {
        if (EmptyUtils.isEmpty(items)) {
            return;
        }
        Thread thread = new Thread(() -> {
            int index = dataList.size();
            dataList.addAll(items);
            notifyItemRangeInserted(index, dataList.size());
        });
        thread.start();
        System.out.println("addItemsAtFront" + thread.isAlive());
    }

    public void addItems(List<T> items, boolean isRefresh) {
        if (items == null) {
            return;
        }

        if (isRefresh && dataList != null) dataList.clear();
        dataList.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        if (item == null) {
            return;
        }
        dataList.add(item);
        notifyDataSetChanged();
    }

    public void addItem(T item, int position) {
        if (item == null) {
            return;
        }
        dataList.add(position, item);
        notifyDataSetChanged();
    }

    public void deleteItem(T item) {
        if (item == null) {
            return;
        }
        dataList.remove(item);
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        if (dataList != null) {
            return dataList;
        } else {
            return new ArrayList<>();
        }
    }

    public T getDataItem(int position) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }
        return dataList.get(position);
    }

    public void setOnClickItemRecyclerViewListener(OnClickItemRecyclerView<T> mOnClickItemRecyclerViewListener) {
        this.onClickItemRecyclerViewListener = mOnClickItemRecyclerViewListener;
    }
}
