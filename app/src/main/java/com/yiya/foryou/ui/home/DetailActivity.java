package com.yiya.foryou.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yiya.foryou.R;
import com.yiya.foryou.app.App;
import com.yiya.foryou.base.BaseActivity;
import com.yiya.foryou.databinding.ActivityDetailBinding;
import com.yiya.foryou.utils.DateUtils;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.T;
import com.yiya.foryou.viewmodel.DetailViewModel;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> {

    private int id;
    private String nowDate;
    private static final int UPTIMECODE = 1000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPTIMECODE:
                    nowDate = DateUtils.nowData();
                    L.d("当前时间" + nowDate);
                    bindingView.time.setText(nowDate);
                    handler.sendEmptyMessageDelayed(UPTIMECODE, 1000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindingView.setViewModel(viewModel);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        setTime();

        viewModel.getNoteDetail(id).observe(this, noteBean -> {
            showContentView();
            if (noteBean != null) {
                viewModel.title.set(noteBean.getTitle());
                viewModel.details.set(noteBean.getDetails());
            }
        });
        viewModel.updateData.observe(this, successBean -> {
            if (successBean != null) {
                finish();
                T.showShort(App.getInstance(), "更新成功");
            }
        });
    }

    public void update(View v) {
        showProgress();
        viewModel.update(id, nowDate);
    }

    private void setTime() {
        handler.sendEmptyMessageAtTime(UPTIMECODE, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(UPTIMECODE);
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
