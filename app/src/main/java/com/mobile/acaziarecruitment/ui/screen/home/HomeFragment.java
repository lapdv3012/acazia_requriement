package com.mobile.acaziarecruitment.ui.screen.home;

import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.BaseFragment;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.databinding.FragmentHomeBinding;
import com.mobile.acaziarecruitment.ui.screen.home.adapter.UserCardAdapter;

import org.greenrobot.eventbus.EventBus;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private UserCardAdapter userCardAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        setUserCardAdapter();
        viewModel.isLoading.observe(this, loading -> binding.loading
                .setVisibility(loading ? View.VISIBLE : View.GONE));
        viewModel.error.observe(this, error -> binding.viewEmpty
                .container.setVisibility(error ? View.VISIBLE : View.GONE));
        binding.viewEmpty.tryAgain.setOnClickListener(view -> viewModel.getRandomUser());
    }

    public void setUserCardAdapter() {
        userCardAdapter = new UserCardAdapter(getActivity());
        binding.frame.setAdapter(userCardAdapter);
        binding.frame.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                userCardAdapter.removeFirst(0);
            }

            @Override
            public void onLeftCardExit(Object o) {
                viewModel.getRandomUser();
            }

            @Override
            public void onRightCardExit(Object object) {
                if (object instanceof Result) {
                    Result result = (Result) object;
                    viewModel.saveFavoriteUser(result);
                    EventBus.getDefault().postSticky(result);
                }
                viewModel.getRandomUser();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                userCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float v) {

            }
        });
        viewModel.resultLiveData.observe(this, results -> userCardAdapter.setDataList(results));
    }

    @Override
    protected void initData() {
        viewModel.getRandomUser();
    }

    @Override
    protected HomeViewModel getViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }
}
