package com.yiya.qq.app;

import android.app.Application;

import com.yiya.qq.api.NetWorkManager;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/13	10:34
 * description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init();
    }
}
