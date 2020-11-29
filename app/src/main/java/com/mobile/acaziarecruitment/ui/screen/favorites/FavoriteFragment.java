package com.mobile.acaziarecruitment.ui.screen.favorites;

import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.BaseFragment;
import com.mobile.acaziarecruitment.databinding.FragmentFavoriteBinding;
import com.mobile.acaziarecruitment.utils.EmptyUtils;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, FavoriteViewModel> {
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void initView() {
        binding.viewEmpty.tryAgain.setOnClickListener(view -> viewModel.getResultLocal());
        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.getResultLocal());
        initAdapter();
    }

    private void initAdapter() {
        favoriteAdapter = new FavoriteAdapter(getActivity());
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        binding.recyclerview.setAdapter(favoriteAdapter);
    }

    @Override
    protected void initData() {
        viewModel.getResultLocal();
        viewModel.resultLiveData.observe(this, results -> {
            if (EmptyUtils.isNotEmpty(results)) {
                favoriteAdapter.setDataList(results);
            }
            binding.viewEmpty.container.setVisibility(EmptyUtils.isNotEmpty(favoriteAdapter
                    .getDataList()) ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    protected FavoriteViewModel getViewModel() {
        return ViewModelProviders.of(this).get(FavoriteViewModel.class);
    }

    public void requestUpdate() {
        viewModel.getResultLocal();
    }
}
