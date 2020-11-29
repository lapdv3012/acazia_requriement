package com.mobile.acaziarecruitment.ui.screen.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.BaseActivity;
import com.mobile.acaziarecruitment.base.BaseFragment;
import com.mobile.acaziarecruitment.data.constant.PositionTab;
import com.mobile.acaziarecruitment.data.local.ResultLocal;
import com.mobile.acaziarecruitment.data.model.Result;
import com.mobile.acaziarecruitment.databinding.ActivityMainBinding;
import com.mobile.acaziarecruitment.ui.screen.auth.LoginActivity;
import com.mobile.acaziarecruitment.ui.screen.favorites.FavoriteFragment;
import com.mobile.acaziarecruitment.ui.screen.home.HomeFragment;
import com.mobile.acaziarecruitment.ui.screen.main.adapter.MainPageAdapter;
import com.mobile.acaziarecruitment.utils.Constant;
import com.mobile.acaziarecruitment.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final int ACTIVITY_NUM = 1;
    private MainPageAdapter mainPageAdapter;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onResultUser(Result result) {
        if (result != null) {
            badeCount++;
        } else {
            badeCount = 0;
        }
        initQBadgeView(badeCount, binding.topNavViewBar.getBottomNavigationItemView(PositionTab.FAVORITES));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setupTopNavigationView();
        initViewPager();
        initFragment();
    }

    @SuppressLint("NonConstantResourceId")
    private void setupTopNavigationView() {
        BottomNavigationViewEx tvEx = findViewById(R.id.topNavViewBar);
        tvEx.setIconSize(24, 24);
        MenuItem menuItem = tvEx.getMenu().getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
        tvEx.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.favorites:
                    if (qBadgeView != null) {
                        badeCount = 0;
                        qBadgeView.setBadgeNumber(badeCount);
                        qBadgeView.hide(true);
                    }
                    binding.viewpager.setCurrentItem(PositionTab.FAVORITES, false);
                    break;
                case R.id.action_main:
                    binding.viewpager.setCurrentItem(PositionTab.HOME, false);
                    break;
                case R.id.action_logout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(getString(R.string.label_message_logout))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.label_yes), (dialog, id) -> logout())
                            .setNegativeButton(getString(R.string.label_cancel), (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                    break;
            }

            item.setChecked(true);
            return false;
        });
    }

    private void logout(){
        binding.loading.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.loading.setVisibility(View.GONE);
                SPUtils.removeAllKey(MainActivity.this);
                ResultLocal.removeAll(MainActivity.this);
                openActivity(LoginActivity.class);
                Toast.makeText(MainActivity.this, getString(R.string.label_logout_success), Toast.LENGTH_SHORT).show();
                finish();
            }
        }, 500L);
    }

    private void initViewPager() {
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
        binding.viewpager.setOffscreenPageLimit(1);
        binding.viewpager.setAdapter(mainPageAdapter);
        binding.topNavViewBar.setupWithViewPager(binding.viewpager);
    }

    private void initFragment() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new FavoriteFragment());
        fragments.add(new HomeFragment());
        mainPageAdapter.setData(fragments);
        binding.viewpager.setCurrentItem(PositionTab.HOME);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (PositionTab.FAVORITES == position) {
                    FavoriteFragment fragment = (FavoriteFragment) mainPageAdapter.instantiateItem(binding.viewpager, position);
                    if (fragment != null){
                        fragment.requestUpdate();

                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }
}