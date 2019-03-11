package com.yiya.qq.ui;

import android.os.Bundle;
import android.view.View;

import com.yiya.qq.R;
import com.yiya.qq.base.BaseActivity;
import com.yiya.qq.databinding.ActivityLoginBinding;
import com.yiya.qq.viewmodel.LoginViewModel;

import androidx.appcompat.app.ActionBar;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showContentView();
        bindingView.setViewModel(viewModel);
        setTitle("登陆");
    }

    @Override
    public void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void login(View v) {
        viewModel.login().observe(this, loginBean -> {
            if (loginBean != null) {
                startIntent(MainActivity.class, null);
            }
        });
    }

    public void register(View v) {
        startIntent(RegisterActivity.class, null);
        viewModel.login().observe(this, loginBean -> {
            if (loginBean != null) {
                startIntent(MainActivity.class, null);
            }
        });
    }
}

