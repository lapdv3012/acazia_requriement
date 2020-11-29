package com.mobile.acaziarecruitment.ui.screen.splash;

import android.os.Handler;
import android.os.Looper;

import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.BaseActivity;
import com.mobile.acaziarecruitment.data.model.Login;
import com.mobile.acaziarecruitment.data.model.User;
import com.mobile.acaziarecruitment.databinding.ActivitySplashBinding;
import com.mobile.acaziarecruitment.ui.screen.auth.LoginActivity;
import com.mobile.acaziarecruitment.ui.screen.main.MainActivity;
import com.mobile.acaziarecruitment.utils.SPUtils;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private Handler handler;
    private static final long DURATION = 1000L;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, DURATION);
    }

    private Runnable runnable = () -> {
        User user = SPUtils.get(SplashActivity.this, User.class.getName(), User.class);
        openActivity(user != null ? MainActivity.class : LoginActivity.class);
        finish();
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
