package com.yiya.qq.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yiya.qq.R;
import com.yiya.qq.base.BaseActivity;
import com.yiya.qq.databinding.ActivityDetailBinding;
import com.yiya.qq.viewmodel.DetailViewModel;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        viewModel.getNoteDetail(id).observe(this, noteBean -> {
            showContentView();
            if (noteBean != null) {
                bindingView.editText.setText(noteBean.getDetails());
            }
        });

    }

    public void cancel(View v) {

    }

    public void confirm(View v) {

    }
}
