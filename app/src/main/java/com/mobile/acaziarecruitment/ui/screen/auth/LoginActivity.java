package com.mobile.acaziarecruitment.ui.screen.auth;

import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.BaseActivity;
import com.mobile.acaziarecruitment.data.model.User;
import com.mobile.acaziarecruitment.databinding.ActivityLoginBinding;
import com.mobile.acaziarecruitment.ui.screen.main.MainActivity;
import com.mobile.acaziarecruitment.utils.EmptyUtils;
import com.mobile.acaziarecruitment.utils.SPUtils;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private LoginViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.btnLogin.setOnClickListener(view -> viewModel.validationLogin(binding.inputAccount
                .getText().toString(), binding.inputPassword.getText().toString()));
    }

    @Override
    public void initData() {
        viewModel.validError.observe(this, error -> {
            if (EmptyUtils.isNotEmpty(error)) {
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.userValid.observe(this, user -> {
            if (user != null) {
                SPUtils.put(LoginActivity.this, User.class.getName(), user);
                openActivity(MainActivity.class, true);
                Toast.makeText(LoginActivity.this, getString(R.string.label_login_success), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
