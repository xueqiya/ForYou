package com.yiya.foryou.ui.login;

import android.os.Bundle;
import android.view.View;

import com.yiya.foryou.R;
import com.yiya.foryou.base.BaseActivity;
import com.yiya.foryou.databinding.ActivityRegisterBinding;
import com.yiya.foryou.utils.T;
import com.yiya.foryou.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showContentView();
        bindingView.setViewModel(viewModel);
        setTitle("用户注册");
    }

    @Override
    public void initView() {

    }

    public void register(View v) {
        showProgress();
        viewModel.register().observe(this, result -> {
            hideProgress();
            if (result != null) {
                T.showShort(this, "注册成功，请登录");
                this.finish();
            }
        });
    }
}
