package com.mobile.acaziarecruitment.ui.screen.favorites;

import android.content.Context;

import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.adapter.BaseRecyclerBindingAdapter;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.databinding.ItemUserFavoriteBinding;
import com.mobile.acaziarecruitment.utils.EmptyUtils;
import com.mobile.acaziarecruitment.utils.PicassoUtils;

public class FavoriteAdapter extends BaseRecyclerBindingAdapter<Result, ItemUserFavoriteBinding> {

    public FavoriteAdapter(Context context) {
        super(context);
    }

    @Override
    protected int layoutItemId() {
        return R.layout.item_user_favorite;
    }

    @Override
    protected void bindData(ItemUserFavoriteBinding binding, Result result, int position) {
        PicassoUtils.loadImage(context, result.picture.large, binding.image);
        binding.name.setText(EmptyUtils.isNotEmpty(result.name.title) ? result.name.title : context.getString(R.string.label_name_test));
        binding.address.setText(EmptyUtils.isNotEmpty(result.location.city) ? result.location.city : context.getString(R.string.label_name_test));
    }
}
