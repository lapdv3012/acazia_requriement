package com.mobile.acaziarecruitment.ui.screen.home.adapter;

import android.content.Context;

import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.adapter.BaseArrayAdapter;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.databinding.ItemUserCardBinding;
import com.mobile.acaziarecruitment.utils.EmptyUtils;
import com.mobile.acaziarecruitment.utils.PicassoUtils;

public class UserCardAdapter extends BaseArrayAdapter<Result, ItemUserCardBinding> {

    public UserCardAdapter(Context context) {
        super(context);
    }

    @Override
    protected int layoutItemId() {
        return R.layout.item_user_card;
    }

    @Override
    protected void bindData(ItemUserCardBinding binding, Result result) {
        PicassoUtils.loadImage(getContext(), result.picture.large, binding.image);
        binding.name.setText(result.name != null ? result.name.title + ". " + result.name.first : getContext().getString(R.string.label_name_test));
        binding.address.setText(EmptyUtils.isNotEmpty(result.location.city) ? result.location.city : getContext().getString(R.string.label_name_test));
    }

    public void removeFirst(int position) {
        Result result = getDataItem(position);
        if (result != null) {
            remove(result);
            notifyDataSetChanged();
        }
    }
}
