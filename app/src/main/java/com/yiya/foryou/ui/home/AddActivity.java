package com.yiya.foryou.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yiya.foryou.R;
import com.yiya.foryou.app.App;
import com.yiya.foryou.base.BaseActivity;
import com.yiya.foryou.databinding.ActivityAddBinding;
import com.yiya.foryou.utils.AppConstants;
import com.yiya.foryou.utils.DateUtils;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.SPUtil;
import com.yiya.foryou.utils.T;
import com.yiya.foryou.viewmodel.AddViewModel;

public class AddActivity extends BaseActivity<ActivityAddBinding, AddViewModel> {
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
        setContentView(R.layout.activity_add);
        showContentView();
        bindingView.setViewModel(viewModel);
    }

    @Override
    public void initView() {
        setTime();

        viewModel.Adddata.observe(this, result -> {
            hideProgress();
            if (result != null) {
                finish();
                T.showShort(App.getInstance(), "添加成功");
            } else {

            }
        });
    }

    private void setTime() {
        handler.sendEmptyMessageAtTime(UPTIMECODE, 1000);
    }

    public void add(View v) {
        String uid = (String) SPUtil.get(App.getInstance(), AppConstants.KEY_UID, "");
        showProgress();
        viewModel.add(uid, nowDate);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler.removeMessages(UPTIMECODE);
            handler = null;
        }
    }
}
