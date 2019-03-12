package com.yiya.qq.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yiya.qq.R;
import com.yiya.qq.base.BaseActivity;
import com.yiya.qq.databinding.ActivityRegisterBinding;
import com.yiya.qq.utils.T;
import com.yiya.qq.viewmodel.RegisterViewModel;

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
        viewModel.register().observe(this, loginBean -> {
            if (loginBean != null) {
                hideProgress();
                T.showShort(this, "注册成功！");
                this.finish();
            }
        });
    }
}
