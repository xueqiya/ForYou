package com.yiya.foryou;

import androidx.appcompat.app.AppCompatActivity;
import yanzhikai.textpath.SyncTextPathView;
import yanzhikai.textpath.painter.FireworksPainter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yiya.foryou.app.App;
import com.yiya.foryou.ui.MainActivity;
import com.yiya.foryou.ui.login.LoginActivity;
import com.yiya.foryou.utils.AppConstants;
import com.yiya.foryou.utils.SPUtil;

public class SplashActivity extends AppCompatActivity {
    // 创建Handler对象，处理接收的消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    goHome();
                    break;
            }
        }
    };
    private SyncTextPathView atpvAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        atpvAs.setPathPainter(new FireworksPainter());

        atpvAs.startAnimation(0, 1);

        handler.sendEmptyMessageDelayed(1000, 3000);

    }

    private void initView() {
        atpvAs = findViewById(R.id.atpv_as);
    }

    private void goHome() {
        boolean b = (boolean) SPUtil.get(App.getInstance(), AppConstants.KEY_LOGIN, false);
        Intent intent = new Intent();
        if (b) {
            intent.setClass(this, MainActivity.class);
        } else {
            intent.setClass(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(1000);
        }
    }
}
