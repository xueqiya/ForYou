package com.yiya.foryou.ui.login;

import android.os.Bundle;
import android.view.View;

import com.yiya.foryou.R;
import com.yiya.foryou.app.App;
import com.yiya.foryou.base.BaseActivity;
import com.yiya.foryou.databinding.ActivityLoginBinding;
import com.yiya.foryou.ui.MainActivity;
import com.yiya.foryou.utils.AppConstants;
import com.yiya.foryou.utils.SPUtil;
import com.yiya.foryou.viewmodel.LoginViewModel;

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
        showProgress();
        viewModel.login().observe(this, loginBean -> {
            hideProgress();
            if (loginBean != null) {
                hideProgress();
                SPUtil.put(App.getInstance(), AppConstants.KEY_LOGIN, true);
                SPUtil.put(App.getInstance(), AppConstants.KEY_UID, viewModel.uid.get());
                SPUtil.put(App.getInstance(), AppConstants.KEY_PWD, viewModel.pwd.get());
                startIntent(MainActivity.class, null);
            }
        });
    }

    public void register(View v) {
        startIntent(RegisterActivity.class, null);
    }
}

